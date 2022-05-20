package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
			String sql = "INSERT INTO donor (dni, dob, blood_type, alive, id_antigen, id_antibody, id_location, id_doctor_charge) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getdni());
			prep.setDate(2, d.getdob());
			prep.setString(3, d.getBloodType());
			prep.setBoolean(4, d.isAlive());
			prep.setInt(5, d.getAntigen().getId());
			prep.setInt(6, d.getAntibody().getID());
			prep.setInt(7, d.getLocation().getId());
			prep.setInt(8, d.getDoctor_charge().getmedical_id());
			prep.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Donor> listAllDonors() {

		// this methods returns a list of donors that are alive and whose organs are
		// available
		// they only have:
		// a DNI, blood_type and a List <Organ>, each of one has a Type_organ

		List<Donor> aliveDonors = new LinkedList<Donor>();

		try {
			Statement stmt = manager.getConnection().createStatement();

			// this query returns the DNI of the donors that are alive and whose organs are
			// available
			String sql = "SELECT d1.dni, d1.blood_type FROM donor AS d1\r\n"
					+ "JOIN organ AS o1 ON d1.dni = o1.donor_dni\r\n"
					+ "WHERE d1.alive = FALSE AND o1.available = TRUE \r\n" + "GROUP BY d1.dni";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Integer DNI = rs.getInt("dni");
				String bloodType = rs.getString("blood_type");

				List<Organ> organs = new LinkedList<Organ>();
				// this query returns the name of the organs
				String sql2 = "SELECT ty1.name FROM organ AS o1 \r\n"
						+ "JOIN type_of_organ AS ty1 ON ty1.id = o1.id_type_organ\r\n" + "WHERE o1.donor_dni = ?";
				PreparedStatement prep = manager.getConnection().prepareStatement(sql2);
				prep.setInt(1, DNI);

				ResultSet rs2 = prep.executeQuery();
				while (rs.next()) {
					String typeOrgan = rs.getString("name");
					Type_organ t = new Type_organ(typeOrgan);
					Organ o = new Organ(t);
					organs.add(o);
				}

				Donor d = new Donor(DNI, bloodType, organs);
				aliveDonors.add(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return aliveDonors;
	}

	
	
	@Override
	public Donor getDonor(Integer DNI) {
		
		Donor donor = null;
		Antigen antigen = null;
		Antibody antibody = null;
		Location location = null;
		Doctor doctor_charge = null;
		Organ organ = null;
		List<Organ> organs = null;

		try {
			// TODO query join
			// this query returns all the information from the donor except the organs
			String sql = "SELECT * FROM donor AS d1 \r\n" + "JOIN antigen AS ag1 ON d1.id_antigen = ag1.id \r\n"
					+ "JOIN antibody AS ab1 ON d1.id_antibody = ab1.id \r\n"
					+ "JOIN location AS l1 ON d1.id_location = l1.id\r\n"
					+ "JOIN doctor AS doc1 ON d1.id_doctor_charge = doc1.medical_id\r\n" + "WHERE d1.dni = 1";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, DNI);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				Date dob = rs.getDate("dob");
				String bloodType = rs.getString("blood_type");
				boolean alive = rs.getBoolean("alive");
				// TODO

				// get the antigen
				Integer id_antigen = rs.getInt("id_antigen");
				boolean a = rs.getBoolean("a");
				boolean b = rs.getBoolean("b");
				boolean c = rs.getBoolean("c");
				boolean dp = rs.getBoolean("dp");
				boolean dq = rs.getBoolean("dq");
				boolean dr = rs.getBoolean("dr");
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

			}

			// this query returns all the info from the organs
			sql = "SELECT * FROM  organ AS o1 \r\n" + "JOIN type_of_organ AS ty1 ON o1.id_type_organ = ty1.id\r\n"
					+ "WHERE o1.donor_dni = ?";

			prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, DNI);

			while (rs.next()) {

				Integer id = rs.getInt("id");

				// we get the type of organ
				Integer id_type_organ = rs.getInt("id_type_organ");
				String name = rs.getString("name");
				Integer lifespan = rs.getInt("lifespan");
				Type_organ t = new Type_organ(id_type_organ, name, lifespan);

				// we get the organ
				Float size = rs.getFloat("size_organ");
				boolean available = rs.getBoolean("available");
				// ??????? id del donor
				o = new Organ(id, t, size, available);
				organs.add(o);
			}
			donor = new Donor(DNI, dob, bloodType, alive, antigen, antibody, location, doctor_charge);

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
			prep.setInt(8, d.getDoctor_charge().getmedical_id());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			String sql = "SELECT * FROM donor WHERE blood_type = \"?\"";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, bloodType);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer dni = rs.getInt("dni");
				Date dob = rs.getDate("dob");
				// ????????????????????
				String bloodType = rs.getString("blood_type");
				boolean alive = rs.getBoolean("alive");
				// TODO
				Integer antigen_id = rs.getInt("id_antigen");
				Antigen ag = JDBCAntigenManager.getAntigen(antigen_id);
				Integer antibody_id = rs.getInt("id_antibody");
				Antibody ab = JDBCAntibodyManager.getAntibody(antibody_id);
				Integer id_doctor_charge = rs.getInt("id_doctor_charge");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
