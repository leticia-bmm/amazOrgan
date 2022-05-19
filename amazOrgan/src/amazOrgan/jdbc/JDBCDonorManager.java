package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import amazOrgan.ifaces.DonorManager;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Type_organ;

/*addDonor (d: Donor): void 

deleteDonor(DNI: int): void 

getDonor( DNI: int): void  

updateAlive (DNI: int): void 

showDonorsByBloodType (bloodtype: text): void 
*/

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
		// TODO

		// this methods returns a list of donors that are alive and whose organs are
		// available
		// they only have:
		// a DNI, blood_type and a List <Organ>, each of one has a Type_organ

		List<Donor> aliveDonors = new LinkedList<Donor>();

		try {
			Statement stmt = manager.getConnection().createStatement();

			// una o dos queries?????????
			// this query returns the DNI of the donors that are alive and whose organs are
			// available
			String sql = "";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer DNI = rs.getInt("dni");
				String bloodType = rs.getString("blood_type");

				List<Organ> organs = new LinkedList<Organ>();
				// this query returns the name of the organs
				String sql2 = "";
				ResultSet rs2 = stmt.executeQuery(sql2);
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
		Donor d = null;
		try {
			// TODO query join
			String sql = "";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, DNI);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Date dob = rs.getDate("dob");
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

		}
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
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
