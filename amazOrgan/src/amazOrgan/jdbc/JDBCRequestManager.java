package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import amazOrgan.ifaces.RequestManager;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Request;

public class JDBCRequestManager implements RequestManager {

	// TODO check if the methods are well created
	private JDBCManager manager;

	public JDBCRequestManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addRequest(Request r) {
		try {
			String sql = "INSERT INTO request (id, id_type_organ, received, donor_DNI, size_organ) VALUES (?,?,?,?,?)";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getId());
			prep.setInt(2, r.getType_organ().getId());
			prep.setBoolean(3, r.isReceived());
			//TODO change donor -> organ 
			prep.setInt(4, r.getDonor().getdni());
			prep.setFloat(5, r.getSize());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrganId(Organ o) {
		// TODO Auto-generated method stub
		// Recibo un organ que ha sido el que me han donado
		// Guardo en la info del request
		
	}

	@Override
	public Request getRequest(Integer id) {
		// TODO Auto-generated method stub
		// getTypeOtgan
		return null;
	}



}
