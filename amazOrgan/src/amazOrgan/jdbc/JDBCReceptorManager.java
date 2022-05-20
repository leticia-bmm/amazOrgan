package amazOrgan.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	@Override
	public void addReceptor(Receptor r) {
		try {
			String sql = "INSERT INTO receptor (dni, dob, status, blood_type, alive, urgency, id_antigen, id_antibody, id_location, id_request) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getDni());
			prep.setDate(2, r.getDob());
			prep.setString(3, r.getStatus());
			prep.setString(4, r.getBlood_type());
			prep.setBoolean(5, r.getAlive());
			prep.setInt(6, r.getUrgency());
			prep.setInt(7, r.getAntigen().getId());
			prep.setInt(8, r.getAntibody().getID());
			prep.setInt(9, r.getLocation().getId());
			prep.setInt(10, r.getRequest().getId());
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
		try {
			String sql = "UPDATE receptor " + "SET alive = ? " + "status = ? " + "urgency = ? " + "WHERE dni = ?";
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
			String sql = "SELECT * FROM receptor AS r1 " + "JOIN antigen AS ag1 ON r1.id_antigen = ag1.id "
					+ "JOIN antibody AS ab1 ON r1.id_antibody = ab1.id "
					+ "JOIN location AS l1 ON r1.id_location = l1.id "
					+ "JOIN request AS re1 ON r1.id_request = re1.id "
					+ "JOIN type_of_organ AS ty1 ON re1.id_type_organ = ty1.id " 
					+ "WHERE r1.dni = ?" + dni;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Date dob = rs.getDate("dob");
				String status = rs.getString("status");
				String blood_type = rs.getString("blood_type");
				Boolean alive = rs.getBoolean("alive");
				Integer urgency = rs.getInt("urgency");
				// getting the info of the antigen
				Boolean a = rs.getBoolean("a");
				Boolean b = rs.getBoolean("b");
				Boolean c = rs.getBoolean("c");
				Boolean dp = rs.getBoolean("dp");
				Boolean dq = rs.getBoolean("dq");
				Boolean dr = rs.getBoolean("dr");
				antigen = new Antigen(a, b, c, dp, dq, dr);
				// getting the info of the antibody
				Boolean class_I = rs.getBoolean("class_I");
				Boolean class_II = rs.getBoolean("class_II");
				antibody = new Antibody(class_I, class_II);
				// getting the info of the location
				Float latitude = rs.getFloat("latitude");
				Float longitude = rs.getFloat("longitude");
				location = new Location(latitude, longitude);
				// getting the info of the request
				// first the simple attributes
				Boolean received = rs.getBoolean("received");
				Float size_request = rs.getFloat(28);
				// now the foreign keys
				// getting the type of organ
				String name = rs.getString("name");
				Integer lifespan = rs.getInt("lifespan");
				type_organ = new Type_organ(name, lifespan);
				// getting the info of the organ IF THERE IS ONE
				Integer organ_id = rs.getInt("organ_id");
				if (organ_id != null) {
					// we are going to reuse the type of organ since it is the same
					Float size_organ = rs.getFloat(34);
					Boolean available = rs.getBoolean("available");
					Integer donor_dni = rs.getInt("donor_dni");
					donor = new Donor(donor_dni);
					organ_donated = new Organ();
				}
				request = new Request(type_organ, size_request, received, organ_donated);

				r = new Receptor(dni, dob, status, blood_type, alive, urgency, antigen, antibody, location, request);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void assignDoctor(Receptor r, Doctor d) {
		try {
			String sql = "INSERT INTO examines (receptor_id, medical_id) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, r.getDni());
			p.setInt(2, d.getmedical_id());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unassignDoctor(Receptor r, Doctor d) {
		try {
			String sql = "DELETE FROM examines WHERE receptor_id=? AND medical_id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, r.getDni());
			p.setInt(2, d.getmedical_id());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Receptor> showReceptorsByBloodType(String bloodtype) {
		List<Receptor> receptors = new LinkedList();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM receptor WHERE blood_type = ?" + bloodtype;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer dni = rs.getInt("dni");
				Date dob = rs.getDate("dob");
				String status = rs.getString("status");
				String blood_type = rs.getString("blood_type");
				Integer urgency = rs.getInt("urgency");
				Integer antigen_id = rs.getInt("id_antigen");
				Boolean alive = rs.getBoolean("alive");
				Antigen antigen = JDBCAntigenManager.getAntigen(antigen_id);
				Integer antibody_id = rs.getInt("id_antibody");
				Antibody antibody = JDBCAntibodyManager.getAntibody(antibody_id);
				Integer location_id = rs.getInt("id_location");
				Location location = JDBCLocationManager.getLocation(location_id);
				Integer request_id = rs.getInt("id_request");
				Request request = JDBCRequestManager.getRequest(request_id);
				Receptor r = new Receptor(dni, dob, status, blood_type, urgency, antigen, antibody, location, requestalive);
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
		List<Receptor> receptors = new LinkedList();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM receptor ORDER BY urgency ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer dni = rs.getInt("dni");
				Date dob = rs.getDate("dob");
				String status = rs.getString("status");
				String blood_type = rs.getString("blood_type");
				Integer urgency = rs.getInt("urgency");
				Integer antigen_id = rs.getInt("id_antigen");
				Boolean alive = rs.getBoolean("alive");
				Antigen antigen = JDBCAntigenManager.getAntigen(antigen_id);
				Integer antibody_id = rs.getInt("id_antibody");
				Antibody antibody = JDBCAntibodyManager.getAntibody(antibody_id);
				Integer location_id = rs.getInt("id_location");
				Location location = JDBCLocationManager.getLocation(location_id);
				Integer request_id = rs.getInt("id_request");
				Request request = JDBCRequestManager.getRequest(request_id);
				Receptor r = new Receptor(dni, dob, status, blood_type, urgency, antigen, antibody, location, request,
						alive);
				receptors.add(r);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receptors;
	}

}
