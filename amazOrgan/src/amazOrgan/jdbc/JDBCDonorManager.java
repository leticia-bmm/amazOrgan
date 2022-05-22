package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import amazOrgan.ifaces.DonorManager;
import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Location;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Type_organ;

public class JDBCDonorManager implements DonorManager {

	private JDBCManager manager;

	public JDBCDonorManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	//TODO
	//this method works if the medical id stays
	//necesito al menos un medical id para que se me junten el donor y el doctor
	public void addDonor(Donor d, Integer medical_id) {
		try {
			System.out.println(d);
			String sql = "INSERT INTO donor (dni, dob, blood_type, alive, id_antigen, id_antibody, id_location, id_doctor_charge) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			prep.setInt(1, d.getdni());
			prep.setDate(2, d.getdob());
			prep.setString(3, d.getBloodType());
			prep.setBoolean(4, d.isAlive());
			
			//inserting the antigen into the database
			Antigen antigen = d.getAntigen();
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
			Antibody antibody = d.getAntibody();
			sql1 = "INSERT INTO antibody (class_I, class_II) VALUES (?,?)";
			prep1 = manager.getConnection().prepareStatement(sql1);
			prep1.setBoolean(1, antibody.isClass_I());
			prep1.setBoolean(2, antibody.isClass_II());
			prep1.executeUpdate();
			rs = prep1.getGeneratedKeys();
			Integer id_antigen = rs.getInt(1);
			
			//inserting the location into the database
			Location location = d.getLocation();
			sql1 = "INSERT INTO location (latitude, longitude) VALUES (?, ?)";
			prep1 = manager.getConnection().prepareStatement(sql1);
			prep1.setFloat(1, location.getLatitude());
			prep1.setFloat(2, location.getLongitude());
			prep1.executeUpdate();
			rs = prep1.getGeneratedKeys();
			Integer id_location = rs.getInt(1);
			
			prep.setInt(5, id_antibody);
			prep.setInt(6, id_antigen);
			prep.setInt(7, id_location);
			prep.setInt(8, medical_id);
			prep.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	//this method works
	public List<Donor> listAllDonors() {
		// this methods returns a list of donors that are dead and whose organs are available
		// they only have:
		// a DNI, blood_type and a List <Organ>, each of one has a Type_organ

		List<Donor> deadDonors = new LinkedList<Donor>();
		List<Organ> organs = new LinkedList<Organ>();

		try {
			Statement stmt = manager.getConnection().createStatement();

			// this query returns the DNI of the donors that are dead and whose organs are
			// available
			String sql = "SELECT d1.dni, d1.blood_type FROM donor AS d1 "
					+ "JOIN organ AS o1 ON d1.dni = o1.donor_dni "
					+ "WHERE d1.alive = 0 AND o1.available = 1 " 
					+ "GROUP BY d1.dni";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Integer dni = rs.getInt("dni");
				System.out.println(dni);
				String bloodType = rs.getString("blood_type");
				System.out.println(bloodType);
				
				// this query returns the name of the organs
				String sql2 = "SELECT ty1.name FROM organ AS o1 "
						+ "JOIN type_of_organ AS ty1 ON ty1.id = o1.id_type_organ " 
						+ "WHERE o1.donor_dni = ?";
				
				PreparedStatement prep = manager.getConnection().prepareStatement(sql2);
				prep.setInt(1, dni);
				ResultSet rs2 = prep.executeQuery();
				while (rs2.next()) {
					String typeOrgan = rs2.getString("name");
					Type_organ t = new Type_organ(typeOrgan);
					Organ o = new Organ(t);
					organs.add(o);
				}

				Donor d = new Donor(dni, bloodType, organs);
				System.out.println(d);
				deadDonors.add(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deadDonors;
	}

	
	
	@Override
	//this method works
	public Donor getDonor(Integer dni) {
		
		Donor donor = null;
		Antigen antigen = null;
		Antibody antibody = null;
		Location location = null;
		Doctor doctor_charge = null;
		Organ organ = null;
		List<Organ> organs = null;

		try {
			
			// this query returns all the information from the donor except the organs
			String sql = "SELECT * FROM donor AS d1 " 
					+ "JOIN antigen AS ag1 ON d1.id_antigen = ag1.id "
					+ "JOIN antibody AS ab1 ON d1.id_antibody = ab1.id "
					+ "JOIN location AS l1 ON d1.id_location = l1.id "
					+ "JOIN doctor AS doc1 ON d1.id_doctor_charge = doc1.medical_id " 
					+ "WHERE d1.dni = ?";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, dni);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				Date dob = rs.getDate("dob");
				String bloodType = rs.getString("blood_type");
				Boolean alive = rs.getBoolean("alive");

				// get the antigen
				Integer id_antigen = rs.getInt("id_antigen");
				Boolean a = rs.getBoolean("a");
				Boolean b = rs.getBoolean("b");
				Boolean c = rs.getBoolean("c");
				Boolean dp = rs.getBoolean("dp");
				Boolean dq = rs.getBoolean("dq");
				Boolean dr = rs.getBoolean("dr");
				antigen = new Antigen(id_antigen, a, b, c, dp, dq, dr);

				// get the antibody
				Integer id_antibody = rs.getInt("id_antibody");
				boolean class_I = rs.getBoolean("class_I");
				boolean class_II = rs.getBoolean("class_II");
				antibody = new Antibody(id_antibody, class_I, class_II);

				// get the location
				Integer id_location = rs.getInt("id_location");
				Float latitude = rs.getFloat("latitude");
				Float longitude = rs.getFloat("longitude");
				location = new Location(id_location, latitude, longitude);

				// get the doctor
				Integer id_doctor_charge = rs.getInt("id_doctor_charge");
				Integer phone = rs.getInt("phone_number");
				String name = rs.getString("name");
				doctor_charge = new Doctor(id_doctor_charge, phone, name);

				donor = new Donor(dni, dob, alive, bloodType, antigen, antibody, location, doctor_charge);
			}
			 

			// this query returns all the info from the organs
			sql = "SELECT * FROM  organ AS o1 " 
					+ "JOIN type_of_organ AS ty1 ON o1.id_type_organ = ty1.id "
					+ "WHERE o1.donor_dni = ?";

			prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, dni);

			while (rs.next()) {

				Integer id = rs.getInt("id");

				// we get the type of organ
				Integer id_type_organ = rs.getInt("id_type_organ");
				String name = rs.getString("name");
				Integer lifespan = rs.getInt("lifespan");
				Type_organ t = new Type_organ(id_type_organ, name, lifespan);

				// we get the organ
				Float size = rs.getFloat("size_organ");
				Boolean available = rs.getBoolean("available");
				
				
				organ = new Organ(id, t, size, available, donor);
				organs.add(organ);
			}
			
			donor.setOrgans(organs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return donor;
	}

	@Override
	//this method receives the donor that has to be updated
	// and also the doctor that is in charge of him, who in fact is the doctor that is updating this information
	
	public void updateDonor(Donor d, int medicalId) {
		
		try {
			//the donor inserted has a dni, a bloodtype and the organs
			// we have to update the rest of the info which has an id but its c
			String sql = "UPDATE donor SET blood_type = ?, alive = FALSE, id_doctor_charge = ? WHERE dni = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getBloodType());
			
			//Update the doctor in charge (it had the id 0 and name unassigned)
			prep.setInt(2, medicalId);
			
			prep.setInt(3, d.getdni());
			
			//update the antigen
			int idAntigen = d.getAntigen().getId();
			sql = "UPDATE antigen SET a  = ?, b = ?, c = ?, dp = ?, dq = ?, dr = ? WHERE id = ?";
			prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, d.getAntigen().isA());
			prep.setBoolean(2, d.getAntigen().isB());
			prep.setBoolean(3, d.getAntigen().isC());
			prep.setBoolean(4, d.getAntigen().isDp());
			prep.setBoolean(5, d.getAntigen().isDq());
			prep.setBoolean(6, d.getAntigen().isDr());
			prep.setInt(7, idAntigen);
			
			//Update the antibody
			int idAntibody = d.getAntibody().getID();
			sql = "UPDATE antibody SET class_I = ?, class_II = ? WHERE id = ?";
			prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, d.getAntibody().isClass_I());
			prep.setBoolean(2, d.getAntibody().isClass_II());
			prep.setInt(3, idAntibody);
			
			//Update the location
			int idLocation = d.getLocation().getId();
			sql = "UPDATE location SET latitude = ?, longitude = ? WHERE id = ?";
			prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, d.getLocation().getLatitude());
			prep.setFloat(2, d.getLocation().getLongitude());
			prep.setInt(3, idLocation);		
			
			prep.executeUpdate();
			System.out.println("donor updated");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public List<Donor> listMyDonors(Integer medical_id){
		List<Donor> donors = new LinkedList<Donor>();
		Donor d = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT dni, alive FROM donor WHERE id_doctor_charge=" + medical_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer donor_id = rs.getInt("donor_id");
				Boolean alive = rs.getBoolean("alive");
				d = new Donor(donor_id, alive);
				donors.add(d);
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return donors;
		
	}

	@Override
	public void deleteDonor(Integer dni) {
		try {
			String sql = "DELETE FROM donor WHERE DNI=?";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, dni);
			prep.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ?????????????????????????
	@Override
	public List<Donor> showDonorsByBloodType(String bloodType) {
		// TODO
				List<Donor> donors = new LinkedList<Donor>();
				try {
					String sql = "SELECT dni, alive FROM donor WHERE blood_type = ?" ;
					PreparedStatement prep = manager.getConnection().prepareStatement(sql);
					prep.setString(1, bloodType);
					ResultSet rs = prep.executeQuery();
					while (rs.next()) {
						Integer dni = rs.getInt("dni");
						Boolean alive = rs.getBoolean("alive");
						

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				return donors;
	}

}
