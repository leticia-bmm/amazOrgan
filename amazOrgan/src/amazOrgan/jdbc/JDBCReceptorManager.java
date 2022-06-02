package amazOrgan.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import amazOrgan.ifaces.ReceptorManager;
import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Location;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Receptor;
import amazOrgan.pojos.Request;
import amazOrgan.pojos.Type_organ;
import amazOrgan.jdbc.JDBCAntigenManager;

public class JDBCReceptorManager implements ReceptorManager {

	private JDBCManager manager;

	public JDBCReceptorManager(JDBCManager m) {
		this.manager = m;
	}
	
	

	//this method works
	@Override
	public void addReceptor(Receptor r) {
		try {
			String sql = "INSERT INTO receptor (dni, dob, status, blood_type, alive, urgency, id_antigen, id_antibody, id_location, id_request) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getDni());
			prep.setDate(2, Date.valueOf(r.getDob()));
			prep.setString(3, r.getStatus());
			prep.setString(4, r.getBlood_type());
			prep.setBoolean(5, r.getAlive());
			prep.setInt(6, r.getUrgency());
			
			//inserting the antigen into the database
			Antigen antigen = r.getAntigen();
			String sql1 = "INSERT INTO antigen (a, b, c, dp, dq, dr) VALUES (?,?,?,?,?,?)";
			PreparedStatement prep1 = manager.getConnection().prepareStatement(sql1);
			prep1.setBoolean(1, antigen.isA());
			prep1.setBoolean(2, antigen.isB());
			prep1.setBoolean(3, antigen.isC());
			prep1.setBoolean(4, antigen.isDp());
			prep1.setBoolean(5, antigen.isDq());
			prep1.setBoolean(6, antigen.isDr());
			prep1.executeUpdate();
			ResultSet rs = prep1.getGeneratedKeys();
			Integer id_antibody = rs.getInt(1);
			
			//inserting the antibody into the database
			Antibody antibody = r.getAntibody();
			sql1 = "INSERT INTO antibody (class_I, class_II) VALUES (?,?)";
			prep1 = manager.getConnection().prepareStatement(sql1);
			prep1.setBoolean(1, antibody.isClass_I());
			prep1.setBoolean(2, antibody.isClass_II());
			prep1.executeUpdate();
			rs = prep1.getGeneratedKeys();
			Integer id_antigen = rs.getInt(1);
			
			//inserting the location into the database
			Location location = r.getLocation();
			sql1 = "INSERT INTO location (latitude, longitude) VALUES (?, ?)";
			prep1 = manager.getConnection().prepareStatement(sql1);
			prep1.setFloat(1, location.getLatitude());
			prep1.setFloat(2, location.getLongitude());
			prep1.executeUpdate();
			rs = prep1.getGeneratedKeys();
			Integer id_location = rs.getInt(1);
			
			//inserting request into the database
			Request request = r.getRequest();
			sql1 = "INSERT INTO request (id_type_organ, received, size_organ) VALUES (?,?,?)";
			//ya está creado en la database, siempre tiene el mismo id
			prep1 = manager.getConnection().prepareStatement(sql1);
		    prep1.setInt(1, request.getType_organ().getId());
			prep1.setBoolean(2, request.isReceived());
			prep1.setFloat(3, request.getSize());
			prep1.executeUpdate();
			rs = prep1.getGeneratedKeys();
			Integer id_request = rs.getInt(1);
			
			prep.setInt(7, id_antibody);
			prep.setInt(8, id_antigen);
			prep.setInt(9, id_location);
			prep.setInt(10, id_request);
			prep.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// inUpdate receptor, we are giving the NEW receptor to the function in
	// JDBC, that means that we have to create the function change the
	// data of the receptor in JAVA
	// TODO write the java function for the JDBCs
	@Override
	public void updateReceptor(Receptor r) {
		//this method works
		try {
			String sql = "UPDATE receptor SET alive=?, status =?, urgency=? WHERE dni=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, r.getAlive());
			prep.setString(2, r.getStatus());
			prep.setInt(3, r.getUrgency());
			prep.setInt(4, r.getDni());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//this method works
	@Override
	public Receptor getReceptor(Integer dni) {
		Receptor r = null;
		Antigen antigen = null;
		Antibody antibody = null;
		Location location = null;
		Request request = null;
		Organ organ_donated = null;
		Donor donor = null;
		Type_organ type_organ = null;
		// List <Doctor> doctors = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM receptor AS r1 " 
			        + "LEFT JOIN antigen AS ag1 ON r1.id_antigen = ag1.id "
					+ "LEFT JOIN antibody AS ab1 ON r1.id_antibody = ab1.id "
					+ "LEFT JOIN location AS l1 ON r1.id_location = l1.id "
					+ "LEFT JOIN request AS re1 ON r1.id_request = re1.id "
					+ "LEFT JOIN type_of_organ AS ty1 ON re1.id_type_organ = ty1.id "
					+ "LEFT JOIN organ AS o1 ON re1.id_organ = o1.id " 
					+ "WHERE r1.dni=" + dni;
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Date dob = rs.getDate("dob");
				LocalDate dob_java = dob.toLocalDate();
				String status = rs.getString("status");
				String blood_type = rs.getString("blood_type");
				Boolean alive = rs.getBoolean("alive");
				Integer urgency = rs.getInt("urgency");
				
				// getting the info of the antigen
				Integer id_antigen = rs.getInt("id_antigen");
				Boolean a = rs.getBoolean("a");
				Boolean b = rs.getBoolean("b");
				Boolean c = rs.getBoolean("c");
				Boolean dp = rs.getBoolean("dp");
				Boolean dq = rs.getBoolean("dq");
				Boolean dr = rs.getBoolean("dr");
				antigen = new Antigen(id_antigen, a, b, c, dp, dq, dr);
				
				// getting the info of the antibody
				Integer id_antibody = rs.getInt("id_antibody");
				Boolean class_I = rs.getBoolean("class_I");
				Boolean class_II = rs.getBoolean("class_II");
				antibody = new Antibody(id_antibody, class_I, class_II);
				
				// getting the info of the location
				Integer id_location = rs.getInt("id_location");
				Float latitude = rs.getFloat("latitude");
				Float longitude = rs.getFloat("longitude");
				location = new Location(id_location, latitude, longitude);
				
				// getting the info of the request
				// first the simple attributes
				Boolean received = rs.getBoolean("received");
				Float size_request = rs.getFloat(28);
				// now the foreign keys
				// getting the type of organ
				Integer id_type_organ = rs.getInt(29);
				String name = rs.getString("name");
				Integer lifespan = rs.getInt("lifespan");
				type_organ = new Type_organ(id_type_organ, name, lifespan);
				// getting the info of the organ IF THERE IS ONE
				Integer organ_id = rs.getInt("id_organ");
				
				//the id_organ cant be read as a null, it returns 0
				if (organ_id != 0) {
					// we are going to reuse the type of organ since it is the same
					Float size_organ = rs.getFloat(34);
					Boolean available = rs.getBoolean("available");
					Integer donor_dni = rs.getInt("donor_dni");
					donor = new Donor(donor_dni);
					organ_donated = new Organ(organ_id, type_organ, size_organ, available, donor);
				}
				Integer id_request = rs.getInt("id_request");
				request = new Request(id_request, type_organ, size_request, received, organ_donated);

				r = new Receptor(dni, dob_java, status, blood_type, alive, urgency, antigen, antibody, location, request);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void unassignDoctor(Receptor r, Doctor d) {
		//this method works
		try {
			String sql = "DELETE FROM examines WHERE receptor_id=? AND medical_id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, r.getDni());
			p.setInt(2, d.getMedical_id());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void assignDoctor(Receptor r, Doctor d) {
		//this method works
		try {
			String sql = "INSERT INTO examines (receptor_id, medical_id) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, r.getDni());
			p.setInt(2, d.getMedical_id());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// list of receptors 
	@Override
	public List<Receptor> listMyReceptors(Integer medical_id) {
		List<Receptor> receptors = new LinkedList<Receptor>();
		Receptor r = new Receptor();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT r1.dni, r1.status, r1.alive, r1.urgency FROM examines AS e1 "
					+ "LEFT JOIN receptor AS r1 ON e1.receptor_id = r1.dni "
					+ "WHERE e1.medical_id = " + medical_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer receptor_id = rs.getInt("receptor_id");
				String status = rs.getString("status");
				Boolean alive = rs.getBoolean("alive");
				Integer urgency = rs.getInt("urgency");
				r = new Receptor(receptor_id, status, urgency, alive);
				receptors.add(r);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return receptors;
	}
	


	@Override
	public List<Receptor> showReceptorsByBloodType(String bloodtype) {
		//shows dni, status, bloodtype, urgency and the name of the organ
		Receptor r = null;
		Request request = null;
		Type_organ type_organ = null;
		List<Receptor> receptors = new LinkedList();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT r1.dni, ty1.name, r1.urgency, r1.status FROM receptor AS r1 "
					+ "LEFT JOIN request AS re1 ON r1.id_request = re1.id "
					+ "LEFT JOIN type_of_organ AS ty1 ON re1.id_type_organ = ty1.id "
					+ "WHERE blood_type = ? "
					+ "AND r1.alive = TRUE";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer dni = rs.getInt("dni");
				String organ_name = rs.getString("name");
				Integer urgency = rs.getInt("urgency");
				String status = rs.getString("status");
				type_organ = new Type_organ(organ_name);
				request = new Request(type_organ);
				r = new Receptor(dni, status, urgency, request);
				receptors.add(r);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receptors;
	}

	@Override
	public List<Receptor> showReceptorsByUrgency() {
		//shows dni, status, urgency and the name of the organ
		List<Receptor> receptors = new LinkedList();
		Receptor r = null;
		Request request = null;
		Type_organ type_organ = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT r1.dni, ty1.name, r1.urgency, r1.status FROM receptor AS r1 "
					+ "LEFT JOIN request AS re1 ON r1.id_request = re1.id "
					+ "LEFT JOIN type_of_organ AS ty1 ON re1.id_type_organ = ty1.id "
					+ "WHERE r1.alive = TRUE "
					+ "ORDER BY r1.urgency DESC";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer dni = rs.getInt("dni");
				String organ_name = rs.getString("name");
				Integer urgency = rs.getInt("urgency");
				String status = rs.getString("status");
				type_organ = new Type_organ(organ_name);
				request = new Request(type_organ);
				r = new Receptor(dni, status, urgency, request);
				receptors.add(r);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receptors;
	}
	

	public Receptor matchWithReceptor(Donor d) {
		Receptor r = null;		
		try {
			String sql = "SELECT * FROM receptor AS r1 "
					+ "JOIN request AS req1 ON r1.id_request = req1.id "
					+ "JOIN type_of_organ AS ty1 ON req1.id_type_organ = ty1.id "
					+ "JOIN antigen AS ag1 ON r1.id_antigen = ag1.id "
					+ "JOIN antibody AS ab1 ON r1.id_antibody = ab1.id "
					+ "JOIN location AS l1 ON r1.id_location = l1.id "
					+ "WHERE req1.received = ? "
					+ "AND r1.alive = ? "
					+ "AND ag1.a = ? "
					+ "AND ag1.b = ? "
					+ "AND ag1.dq = ? "
					+ "AND ab1.class_I = ? "
					+ "AND ab1.class_II = ? "
					+ "AND r1.blood_type = ? "
					+ "AND ty1.id = ? ";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, false);
			prep.setBoolean(2, true);
			prep.setBoolean(3, d.getAntigen().isA());
			prep.setBoolean(4, d.getAntigen().isB());
			prep.setBoolean(5, d.getAntigen().isDq());
			prep.setBoolean(6, d.getAntibody().isClass_I());
			prep.setBoolean(7, d.getAntibody().isClass_II());
			prep.setString(8, d.getBloodType());
			//what should we recive here if there is no organ, we have a list no a single organ
			prep.setInt(8, d.get);
			ResultSet rs = prep.executeQuery();
			
			//checking if the is actually a match
			if(rs.next()) {
				Integer organ_id = rs.getInt(1);
				try {
					String sql1 = "UPDATE request SET received=?, organ_id=? WHERE id=?";
					PreparedStatement p = manager.getConnection().prepareStatement(sql1);
					p.setBoolean(1, true);
					p.setInt(2, organ_id);
					p.setInt(3, r.getRequest().getId());
					p.executeUpdate();
					
					sql1 = "UPDATE organ SET available = ? WHERE id = ?";
					p = manager.getConnection().prepareStatement(sql1);
					p.setBoolean(1, false);
					p.setInt(2, organ_id);
					p.executeUpdate();
					
					sql1 = "UPDATE receptor SET status = ? WHERE dni = ?";
					p = manager.getConnection().prepareStatement(sql1);
					p.setString(1, "Operating");
					p.setInt(2, r.getDni());
					p.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				r = getReceptor(rs.getInt("dni"));
			}else {
				System.out.println("There is no match");
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return d;
	}
	

	

}
