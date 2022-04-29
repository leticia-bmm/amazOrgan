package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import amazOrgan.ifaces.ReceptorManager;

public class JDBCReceptorManager implements ReceptorManager{

	@Override
	public void addReceptor(ReceptorManager r) {
			try {
				String sql = "INSERT INTO receptor (name, phone, email, cardNumber) VALUES (?,?,?,?)";
				PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				prep.setString(1, o.getName());
				prep.setInt(2, o.getPhone());
				prep.setString(3, o.getEmail());
				prep.setInt(4, o.getCardNumber());
				prep.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void updateAlive(Integer DNI) {
		// TODO Auto-generated method stub
		
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
