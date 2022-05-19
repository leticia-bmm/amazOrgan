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
			String sql = "INSERT INTO request (id_type_organ, received, organ_id, size_organ) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, r.getType_organ().getId());
			prep.setBoolean(2, r.isReceived());
			prep.setInt(3, r.getOrgan().getID();
			prep.setFloat(4, r.getSize());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrganId(Organ o) {
		//TODO all method with an update of received -> true and organ _id
		String sql ;

	}

	@Override
	public Request getRequest(Integer id) {
		//TODO all method
		return null;
	}

}
