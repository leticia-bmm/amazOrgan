package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import amazOrgan.ifaces.AntibodyManager;
import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;

public class JDBCAntibodyManager implements AntibodyManager {

	private JDBCManager manager;

	public JDBCAntibodyManager(JDBCManager m) {
		this.manager = m;

	}

	@Override
	public void addAntibody(Antibody a) {

		try {
			String sql = "INSERT INTO antibody (class_I, class_II) VALUES (?,?)";
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

	@Override
	public Antibody getAntibody(Integer id) {
		Antibody a = new Antibody();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctor WHERE id = ?" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Boolean classI = rs.getBoolean("class_I");
				Boolean classII = rs.getBoolean("class_II");
				//TODO acaba los metodos
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
}
