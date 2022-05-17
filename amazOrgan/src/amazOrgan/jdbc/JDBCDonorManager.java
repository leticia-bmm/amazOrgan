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
	public List<Donor> showDonorsByBloodType(String bloodType) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateDonor(Donor d) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Donor> listAllDonors() {
		// TODO Auto-generated method stub
		//list all donors is going to return a list of donors 
		// that only have dni, blood type
		// and a list of organs that only have a type of organ
		// create constructors
		//the query will be a join
		return null;
	}

}
