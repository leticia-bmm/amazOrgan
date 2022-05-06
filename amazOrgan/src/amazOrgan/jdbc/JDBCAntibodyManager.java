package amazOrgan.jdbc;
	

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

import amazOrgan.ifaces.AntibodyManager;
import amazOrgan.pojos.Antibody;

public class JDBCAntibodyManager implements AntibodyManager {
	
	private JDBCManager manager;
	
	public JDBCAntibodyManager(JDBCManager m) {
		this.manager = m;
	
	}

	@Override
	public void addAntibody(Antibody a) {
		
		try {
			String sql = "INSERT INTO antibody (class I, class II) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, a.isClass_I());
			prep.setBoolean(2, a.isClass_II());
			prep.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
	}
	}
	

	@Override
	public void deleteAntibody(Integer id) {
		try {
			String sql = "DELETE FROM antibody WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, id);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		}
	
		
	


