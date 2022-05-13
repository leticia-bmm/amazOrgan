package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import amazOrgan.ifaces.DonorManager;
import amazOrgan.pojos.Donor;
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
			String sql = "INSERT INTO donor (dni, dob, bloodType, alive, id_antigen, id_antibody, id_location, id_doctor_charge, id_organ) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getdni());
			prep.setDate(2, d.getdob());
			prep.setString(3, d.getBloodType());
			prep.setBoolean(4, d.isAlive());
			prep.setInt(5,  d.getAntigen().getId());
			prep.setInt(6,d.getAntibody().getID());
			prep.setInt(7,d.getLocation().getId());
			prep.setInt(8, d.getDoctor_charge().getmedical_id());
			//TODO finish many relationship			
			}catch(Exception e) {
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
			// TODO Auto-generated method stub
			
			

			
		}

		@Override
		public void updateAlive(Integer DNI) {
			
			
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Donor> showDonorsByBloodType(String bloodType) {
			// TODO Auto-generated method stub
			return null;
		}

}
