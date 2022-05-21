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
	public void addDonor(Donor d) {
		try {
			System.out.println(d);
			String sql = "INSERT INTO donor (dni, dob, blood_type, alive, id_antigen, id_antibody, id_location, id_doctor_charge) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getdni());
			prep.setDate(2, d.getdob());
			prep.setString(3, d.getBloodType());
			prep.setBoolean(4, d.isAlive());
			prep.setInt(5, d.getAntigen().getId());
			prep.setInt(6, d.getAntibody().getID());
			prep.setInt(7, d.getLocation().getId());
			prep.setInt(8, d.getDoctor_charge().getMedical_id());
			prep.executeUpdate();
			System.out.println("donor added");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
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
					+ "WHERE d1.alive = FALSE AND o1.available = TRUE " 
					+ "GROUP BY d1.dni";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Integer dni = rs.getInt("dni");
				String bloodType = rs.getString("blood_type");
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
				deadDonors.add(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deadDonors;
	}

	
	
	@Override
	public Donor getDonor(Integer dni) {
		
		Donor donor = null;
		Antigen antigen = null;
		Antibody antibody = null;
		Location location = null;
		Doctor doctor_charge = null;
		Organ organ = null;
		List<Organ> organs = new LinkedList();

		try {
			// TODO query join
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
				// ??????? id del donor
				
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
	public void updateDonor(Donor d) {
		// TODO
		try {
			String sql = "UPDATE donor" + " SET =?" + " =?.............";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getdni());
			prep.setDate(2, d.getdob());
			prep.setString(3, d.getBloodType());
			prep.setBoolean(4, d.isAlive());
			prep.setInt(5, d.getAntigen().getId());
			prep.setInt(6, d.getAntibody().getID());
			prep.setInt(7, d.getLocation().getId());
			prep.setInt(8, d.getDoctor_charge().getMedical_id());
			prep.executeUpdate();
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
