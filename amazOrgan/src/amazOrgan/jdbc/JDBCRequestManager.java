package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import amazOrgan.ifaces.RequestManager;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Request;

public class JDBCRequestManager implements RequestManager {

	private JDBCManager manager;
	public JDBCRequestManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void addRequest(Request r) {
		try {
			String sql = "INSERT INTO request (id, type_organ, size, received, donor VALUES (?,?,?,?,?))";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getId());
			prep.setInt(2, r.getType_organ().getId());
			prep.setFloat(3, r.getSize());
			
			}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateDonorDNI(Donor d) {
		// TODO Auto-generated method stub
		
	}

}
