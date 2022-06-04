package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import amazOrgan.pojos.Receptor;
import amazOrgan.pojos.Type_organ;

public class JDBCDonorManager implements DonorManager {

	private JDBCManager manager;

	public JDBCDonorManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	// this method works
	// necesito al menos un medical id para que se me junten el donor y el doctor
	public void addDonor(Donor d) {
		try {
			System.out.println(d);
			String sql = "INSERT INTO donor (dni, dob, blood_type, alive, id_antigen, id_antibody, id_location, id_doctor_charge) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getdni());

			prep.setDate(2, (Date.valueOf(d.getdob())));
			prep.setString(3, d.getBloodType());
			prep.setBoolean(4, d.isAlive());

			// inserting the antigen into the database
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

			// inserting the antibody into the database
			Antibody antibody = d.getAntibody();
			sql1 = "INSERT INTO antibody (class_I, class_II) VALUES (?,?)";
			prep1 = manager.getConnection().prepareStatement(sql1);
			prep1.setBoolean(1, antibody.isClass_I());
			prep1.setBoolean(2, antibody.isClass_II());
			prep1.executeUpdate();
			rs = prep1.getGeneratedKeys();
			Integer id_antigen = rs.getInt(1);

			// inserting the location into the database
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
			prep.setInt(8, d.getDoctor_charge().getMedical_id());
			prep.executeUpdate();

			// now we have to introduce the organs
			List<Organ> organs = d.getOrgans();
			for (Organ o : organs) {
				String sql2 = "INSERT INTO organ (id_type_organ, size_organ, donor_dni, available) VALUES (?, ?, ?, 1)";
				PreparedStatement prep2 = manager.getConnection().prepareStatement(sql2);
				prep2.setInt(1, o.getType_organ().getId());
				prep2.setFloat(2, o.getSize());
				prep2.setInt(3, d.getdni());
				prep2.executeUpdate();
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	// this method works
	public void addAliveDonor(Donor d) {

		// the Donor received only has a dni, dob, is alive and its doctor is unassigned

		try {
			System.out.println(d);
			String sql = "INSERT INTO donor (dni, dob, blood_type, alive, id_antigen, id_antibody, id_location, id_doctor_charge) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getdni());

			prep.setDate(2, (Date.valueOf(d.getdob())));
			prep.setString(3, d.getBloodType());
			prep.setBoolean(4, d.isAlive());

			// inserting the antigen into the database (everything is null)
			Statement stmt = manager.getConnection().createStatement();
			String sql1 = "INSERT INTO antigen (a, b, c, dp, dq, dr) VALUES (null, null, null, null, null, null)";
			stmt.executeUpdate(sql1);
			ResultSet rs = stmt.getGeneratedKeys();
			Integer id_antibody = rs.getInt(1);

			// inserting the antibody into the database
			stmt = manager.getConnection().createStatement();
			sql1 = "INSERT INTO antibody (class_I, class_II) VALUES (null, null)";
			stmt.executeUpdate(sql1);
			rs = stmt.getGeneratedKeys();
			Integer id_antigen = rs.getInt(1);

			// inserting the location into the database
			stmt = manager.getConnection().createStatement();
			sql1 = "INSERT INTO location (latitude, longitude) VALUES (null, null)";
			stmt.executeUpdate(sql1);
			rs = stmt.getGeneratedKeys();
			Integer id_location = rs.getInt(1);

			prep.setInt(5, id_antibody);
			prep.setInt(6, id_antigen);
			prep.setInt(7, id_location);

			// when the donor is registered into the database, the id of its doctor in
			// charge is 0 (unassigned)
			prep.setInt(8, 0);

			prep.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	// this method works
	public List<Donor> listAllDonors() {
		// this methods returns a list of donors that are dead and whose organs are
		// available
		// they only have:
		// a DNI, blood_type and a List <Organ>, each of one has a Type_organ

		List<Donor> deadDonors = new LinkedList<Donor>();

		try {
			Statement stmt = manager.getConnection().createStatement();

			// this query returns the DNI of the donors that are dead and whose organs are
			// available
			String sql = "SELECT d1.dni, d1.blood_type FROM donor AS d1 " + "JOIN organ AS o1 ON d1.dni = o1.donor_dni "
					+ "WHERE d1.alive = 0 AND o1.available = 1 " + "GROUP BY d1.dni";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Integer dni = rs.getInt("dni");
				// System.out.println(dni);
				String bloodType = rs.getString("blood_type");
				// System.out.println(bloodType);

				// this query returns the name of the organs
				String sql2 = "SELECT ty1.name FROM organ AS o1 "
						+ "JOIN type_of_organ AS ty1 ON ty1.id = o1.id_type_organ "
						+ "WHERE o1.donor_dni = ? AND o1.available = 1";

				PreparedStatement prep = manager.getConnection().prepareStatement(sql2);
				prep.setInt(1, dni);
				ResultSet rs2 = prep.executeQuery();
				List<Organ> organs = new LinkedList<Organ>();
				while (rs2.next()) {

					String typeOrgan = rs2.getString("name");
					Type_organ t = new Type_organ(typeOrgan);
					Organ o = new Organ(t);
					organs.add(o);
				}

				Donor d = new Donor(dni, bloodType, organs);
				// System.out.println(d);
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
		List<Organ> organs = new LinkedList<>();

		try {

			// this query returns all the information from the donor except the organs
			String sql = "SELECT * FROM donor AS d1 " + "JOIN antigen AS ag1 ON d1.id_antigen = ag1.id "
					+ "JOIN antibody AS ab1 ON d1.id_antibody = ab1.id "
					+ "JOIN location AS l1 ON d1.id_location = l1.id "
					+ "JOIN doctor AS doc1 ON d1.id_doctor_charge = doc1.medical_id " + "WHERE d1.dni = ?";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, dni);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				Date dob = rs.getDate("dob");
				LocalDate dob_java = dob.toLocalDate();
				String bloodType = rs.getString("blood_type");
				Boolean alive = rs.getBoolean("alive");
				if (alive == false) {
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
					Boolean class_I = rs.getBoolean("class_I");
					Boolean class_II = rs.getBoolean("class_II");
					antibody = new Antibody(id_antibody, class_I, class_II);

					// get the location
					Integer id_location = rs.getInt("id_location");
					Float latitude = rs.getFloat("latitude");
					Float longitude = rs.getFloat("longitude");
					location = new Location(id_location, latitude, longitude);

				} else {
					Integer id_antigen = rs.getInt("id_antigen");
					antigen = new Antigen(id_antigen);
					Integer id_antibody = rs.getInt("id_antibody");
					antibody = new Antibody(id_antibody);
					Integer id_location = rs.getInt("id_location");
					location = new Location(id_location);

				}

				// get the doctor
				// it allways has info in it (even the unassigned one)
				Integer id_doctor_charge = rs.getInt("id_doctor_charge");
				Integer phone = rs.getInt("phone_number");
				String name = rs.getString("name");
				doctor_charge = new Doctor(id_doctor_charge, phone, name);

				donor = new Donor(dni, dob_java, alive, bloodType, antigen, antibody, location, doctor_charge);

				// getting all the organs from the database
				String sqlorgan = "SELECT * FROM organ AS o1 "
						+ "JOIN type_of_organ AS ty1 ON o1.id_type_organ = ty1.id " + "WHERE o1.donor_dni = ?";

				PreparedStatement prep1 = manager.getConnection().prepareStatement(sqlorgan);
				prep1.setInt(1, dni);
				ResultSet rs1 = prep1.executeQuery();

				while (rs1.next()) {
					String nameorgan = null;
					Integer id = null;
					Integer id_type_organ = null;
					Integer lifespan = null;
					Type_organ t = null;
					Float size = null;
					Boolean available = null;

					if (donor.isAlive() == false) {
						id = rs1.getInt(1);

						// we get the type of organ
						id_type_organ = rs1.getInt("id_type_organ");
						nameorgan = rs1.getString("name");
						lifespan = rs1.getInt("lifespan");
						t = new Type_organ(id_type_organ, nameorgan, lifespan);

						// we get the organ
						size = rs1.getFloat("size_organ");
						available = rs1.getBoolean("available");

					} else {
						id = rs1.getInt(1);

						// we get the type of organ
						id_type_organ = rs1.getInt("id_type_organ");
						nameorgan = rs1.getString("name");
						lifespan = rs1.getInt("lifespan");
						t = new Type_organ(id_type_organ, nameorgan, lifespan);

						// we get the organ
						size = null;
						available = false;
					}

					organ = new Organ(id, t, size, available, donor);
					organs.add(organ);
				}
				donor.setOrgans(organs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return donor;
	}

	@Override
	// this method receives the donor that has to be updated
	// and also the doctor that is in charge of him, who in fact is the doctor that
	// is updating this information
	public void updateDonor(Donor d, Integer medicalId) {

		List<Organ> organs = new LinkedList<>();
		try {
			// the donor inserted has a dni, a bloodtype and the organs
			// we have to update the rest of the info which has an id but its c
			String sql = "UPDATE donor SET blood_type = ?, alive = ?, id_doctor_charge = ? WHERE dni = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getBloodType());
			prep.setBoolean(2, false);
			// Update the doctor in charge (it had the id 0 and name unassigned)
			prep.setInt(3, medicalId);
			prep.setInt(4, d.getdni());
			prep.executeUpdate();

			// update the antigen
			Integer idAntigen = d.getAntigen().getId();
			sql = "UPDATE antigen SET a  = ?, b = ?, c = ?, dp = ?, dq = ?, dr = ? WHERE id = ?";
			prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, d.getAntigen().isA());
			prep.setBoolean(2, d.getAntigen().isB());
			prep.setBoolean(3, d.getAntigen().isC());
			prep.setBoolean(4, d.getAntigen().isDp());
			prep.setBoolean(5, d.getAntigen().isDq());
			prep.setBoolean(6, d.getAntigen().isDr());
			prep.setInt(7, idAntigen);
			prep.executeUpdate();

			// Update the antibody
			Integer idAntibody = d.getAntibody().getID();
			sql = "UPDATE antibody SET class_I = ?, class_II = ? WHERE id = ?";
			prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, d.getAntibody().isClass_I());
			prep.setBoolean(2, d.getAntibody().isClass_II());
			prep.setInt(3, idAntibody);
			prep.executeUpdate();

			// Update the location
			Integer idLocation = d.getLocation().getId();
			sql = "UPDATE location SET latitude = ?, longitude = ? WHERE id = ?";
			prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, d.getLocation().getLatitude());
			prep.setFloat(2, d.getLocation().getLongitude());
			prep.setInt(3, idLocation);
			prep.executeUpdate();

			organs = d.getOrgans();

			for (Organ o : organs) {
				String sql1 = "UPDATE organ SET size_organ = ?, available = 1 WHERE id = ?";
				prep = manager.getConnection().prepareStatement(sql1);
				prep.setFloat(1, o.getSize());
				prep.setInt(2, o.getID());
				prep.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// this method works
	@Override
	public List<Donor> listMyDonors(Integer medical_id) {
		List<Donor> donors = new LinkedList<Donor>();
		Donor d = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT dni, alive FROM donor WHERE id_doctor_charge=" + medical_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer donor_id = rs.getInt("dni");
				System.out.println(donor_id);
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

	// this method works
	// but why do we return a donor?
	@Override
	public Donor matchWithDonor(Receptor r) {
		Donor d = null;
		try {
			String sql = "SELECT * FROM organ AS o1 " + "JOIN donor AS d1 ON d1.dni = o1.donor_dni "
					+ "JOIN antigen AS ag1 ON d1.id_antigen = ag1.id "
					+ "JOIN antibody AS ab1 ON d1.id_antibody = ab1.id "
					+ "JOIN location AS l1 ON d1.id_location = l1.id "
					+ "JOIN type_of_organ AS ty1 ON o1.id_type_organ = ty1.id " + "WHERE d1.alive = ? "
					+ "AND ag1.a = ? " + "AND ag1.b = ? " + "AND ag1.dq = ? " + "AND ab1.class_I = ? "
					+ "AND ab1.class_II = ? " + "AND d1.blood_type = ? " + "AND ty1.id = ? " + "AND o1.available = ?";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, false);
			prep.setBoolean(2, r.getAntigen().isA());
			prep.setBoolean(3, r.getAntigen().isB());
			prep.setBoolean(4, r.getAntigen().isDq());
			prep.setBoolean(5, r.getAntibody().isClass_I());
			prep.setBoolean(6, r.getAntibody().isClass_II());
			prep.setString(7, r.getBlood_type());
			prep.setInt(8, r.getRequest().getType_organ().getId());
			prep.setBoolean(9, true);
			ResultSet rs = prep.executeQuery();

			// checking if the is actually a match
			if (rs.next()) {
				Integer organ_id = rs.getInt(1);
				try {
					String sql1 = "UPDATE request SET received= ?, id_organ=? WHERE id=?";
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
				d = getDonor(rs.getInt("dni"));
			} else {
				System.out.println("There is no match");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return d;
	}

}
