package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import amazOrgan.ifaces.Type_organManager;
import amazOrgan.pojos.Type_organ;

public class JDBCType_organManager implements Type_organManager {

	private JDBCManager manager;
	
	
	public JDBCType_organManager(JDBCManager m) {
		this.manager = m;
	}
	//TODO check if the methods are well created
	@Override
	public void addTypeOfOrgan(Type_organ o) {
		try {
			String sql = "INSERT INTO type_of_organ (id, name, lifespan) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, o.getId());
			prep.setString(2, o.getName());
			prep.setInt(3, o.getLifespan());
			prep.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Type_organ searchTypeOfOrgan(Integer ID) {
		Type_organ t = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM type_of_organ WHERE id="+ID;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("name");
				int lifespan = rs.getInt("lifespan");
				t = new Type_organ(ID, name, lifespan);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
		e.printStackTrace();	
		}
		return t;
	}

}
