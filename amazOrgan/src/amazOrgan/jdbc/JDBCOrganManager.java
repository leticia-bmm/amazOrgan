package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import amazOrgan.ifaces.OrganManager;
import amazOrgan.pojos.Organ;

public class JDBCOrganManager implements OrganManager {

	private JDBCManager manager;
	
	public JDBCOrganManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addOrgan(Organ o) {
		try {
			String sql = "INSERT INTO organ(id, type_organ, size, available) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, o.getID());
			prep.setInt(2, o.getType_organ().getid());
			prep.setFloat(3, o.getSize());
			prep.setBoolean(4, o.isAvailable());
			
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrgan(Integer id) {
		try {
		String sql = "DELETE FROM organ WHERE id = ?";
		PreparedStatement p = manager.getConnection().prepareStatement(sql);
		p.setInt(1, id);
		p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Organ getOrgan(Integer id) {
		try {
		String sql = "SELECT * FROM organ WHERE id = ?";
		PreparedStatement p = manager.getConnection().prepareStatement(sql);
		p.setInt(1, id);
		p.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
