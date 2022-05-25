package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import amazOrgan.ifaces.OrganManager;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.OrganList;
import amazOrgan.pojos.Type_organ;
import amazOrgan.xml.Report;


public class JDBCOrganManager implements OrganManager {

	private JDBCManager manager;
	
	public JDBCOrganManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addOrgan(Organ o) {
		try {
			String sql = "INSERT INTO organ(id_type_organ, size_organ, donor_dni, available) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, o.getType_organ().getId());
			prep.setFloat(2, o.getSize());
			prep.setInt(3, o.getDonor().getdni());
			prep.setBoolean(4, o.isAvailable());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrgan(Integer id) {
		try {
		String sql = "DELETE FROM organ WHERE id=?";
		PreparedStatement p = manager.getConnection().prepareStatement(sql);
		p.setInt(1, id);
		p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Organ getOrgan(Integer id) {
		Organ o = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM organ AS o1 "
					+ "JOIN type_of_organ AS t1 "
					+ "ON o1.id_type_organ = t1.id "
					+ "WHERE o1.id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Integer id_type_organ = rs.getInt("id_type_organ");
				Float size = rs.getFloat("size_organ");
				boolean available = rs.getBoolean("available");
				o = new Organ(id, size, available);
				o.setType_organ(this.getType_organOfOrgan(id_type_organ));
				
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	@Override
	public OrganList getOrgans() {
		OrganList os = new OrganList();
		Organ o = null;
		List <Organ> organs = new LinkedList();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM organ AS o1 "
					+ "JOIN type_of_organ AS t1 "
					+ "ON o1.id_type_organ = t1.id ";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Integer id = rs.getInt(1);
				Integer id_type_organ = rs.getInt("id_type_organ");
				Float size = rs.getFloat("size_organ");
				Boolean available = rs.getBoolean("available");
				o = new Organ(id, size, available);
				o.setType_organ(this.getType_organOfOrgan(id_type_organ));
				organs.add(o);
			}
			os.setOrgans(organs);
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return os;
	}
	
	@Override
	public Type_organ getType_organOfOrgan(Integer id_type_organ) {
		Type_organ t = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM type_of_organ WHERE id = "+id_type_organ;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("name");
				int lifespan = rs.getInt("lifespan");
				t = new Type_organ(id_type_organ, name, lifespan);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return t;
	}	

	
}
