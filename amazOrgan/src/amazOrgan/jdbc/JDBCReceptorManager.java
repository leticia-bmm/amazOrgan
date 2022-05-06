package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import amazOrgan.ifaces.ReceptorManager;
import amazOrgan.pojos.Receptor;

public class JDBCReceptorManager implements ReceptorManager{
	
	private JDBCManager manager;
	
	public JDBCReceptorManager(JDBCManager m) {
		this.manager = m;
	
	}

	@Override
	public void addReceptor(Receptor r) {
			try {
				String sql = "INSERT INTO receptor (dni, dob, status, blood_type, alive, urgency, antigen, antibody, location, doctor_charge, request) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
				//TODO finish the method
				prep.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void updateAlive(Integer DNI) {
		try {
			String sql = ""
			
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateStatus(Integer DNI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUrgency(Integer DNI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getReceptor(Integer DNI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showReceptorsByBloodType(String bloodtype) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showReceptorsByUrgency() {
		// TODO Auto-generated method stub
		
	}


	
	
	
	

}
