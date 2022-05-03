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
			String sql = "INSERT INTO donor (dni, dob, alive, bloodType, antigen, antibody, location, doctor_charge, organs) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().
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
