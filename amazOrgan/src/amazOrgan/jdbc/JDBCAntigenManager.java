package amazOrgan.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import amazOrgan.ifaces.AntigenManager;
import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;

public class JDBCAntigenManager {
	
	private JDBCManager manager;
	
	public JDBCAntigenManager(JDBCManager m) {
		this.manager= m;
	}
	

	public void addAntigen (Antigen a) {
		
		try {
			String sql = "INSERT INTO antigen (a,b,c,dp,dq,dr) VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, a.isA());
			prep.setBoolean(2, a.isB());
			prep.setBoolean(3, a.isC());
			prep.setBoolean(4, a.isDp());
			prep.setBoolean(5, a.isDq());
			prep.setBoolean(6, a.isDr());
			prep.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAntigen(Integer id) {
		try{
			String sql = "DELETE FROM antigen WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, id);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		
		
	}

}

	

}
