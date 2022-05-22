package amazOrgan.jdbc;

import java.sql.ResultSet;
import java.sql.Statement;

import amazOrgan.ifaces.Type_organManager;
import amazOrgan.pojos.Type_organ;

public class JDBCType_organManager implements Type_organManager {

	private JDBCManager manager;
	
	
	public JDBCType_organManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public Type_organ getTypeOfOrgan(Integer id) {
		Type_organ t = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM type_of_organ WHERE id="+id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("name");
				int lifespan = rs.getInt("lifespan");
				t = new Type_organ(id, name, lifespan);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
		e.printStackTrace();	
		}
		return t;
	}

}
