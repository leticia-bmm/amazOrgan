package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import amazOrgan.ifaces.RequestManager;
import amazOrgan.pojos.Donor;
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
<<<<<<< HEAD
			String sql = "INSERT INTO request (id, id_type_organ, received, donor_DNI, size_organ) VALUES (?,?,?,?,?)";
=======
			String sql = "INSERT INTO request (id, type_organ, size, received, donor) VALUES (?,?,?,?,?)";
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getId());
			prep.setInt(2, r.getType_organ().getId());
			prep.setBoolean(3, r.isReceived());
			prep.setInt(4, r.getDonor().getdni());
			prep.setFloat(5, r.getSize());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDonorDNI(Donor d) {
		try {
			String sql = "UPDATE donor" + " SET alive=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setBoolean()
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
