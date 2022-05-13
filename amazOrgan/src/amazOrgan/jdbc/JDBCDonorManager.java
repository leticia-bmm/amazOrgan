package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import amazOrgan.ifaces.DonorManager;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Donor;
/*addDonor (d: Donor): void 

deleteDonor(DNI: int): void 

getDonor( DNI: int): void  

updateAlive (DNI: int): void 

showDonorsByBloodType (bloodtype: text): void 
*/
import amazOrgan.pojos.Organ;

public class JDBCDonorManager implements DonorManager {

	private JDBCManager manager;

	public JDBCDonorManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addDonor(Donor d) {
		try {
			String sql = "INSERT INTO donor (dni, dob, blood_type, alive, id_antigen, id_antibody, id_location, id_doctor_charge, id_organ) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getdni());
			prep.setDate(2, d.getdob());
			prep.setString(3, d.getBloodType());
			prep.setBoolean(4, d.isAlive());
			prep.setInt(5, d.getAntigen().getId());
			prep.setInt(6, d.getAntibody().getID());
			prep.setInt(7, d.getLocation().getId());
			prep.setInt(8, d.getDoctor_charge().getmedical_id());

			// every Donor has a List <Organ> organsList <Organ> organs
			for (Organ o : d.getOrgans()) {
				JDBCOrganManager.addOrgan(o);
			}

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

	@Override
	public void getDonor(Integer DNI) {
		Donor d = null;
		try {
			String sql = "SELECT * FROM donor WHERE DNI = ? ";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, DNI);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Date dob = rs.getDate("dob");
				String bloodType = rs.getString("blood_type");
				boolean alive = rs.getBoolean("alive");
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
	public void updateAlive(Donor d) {
		try {
			String sql = "UPDATE donor " + "SET alive = ? " + "WHERE dni = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, d.isAlive());
			prep.setInt(2, d.getdni());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Donor> showDonorsByBloodType(String bloodType) {
		// TODO Auto-generated method stub
		return null;
	}

}
