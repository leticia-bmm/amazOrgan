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
import amazOrgan.pojos.Doctor;

public class JDBCAntigenManager implements AntigenManager {

	private JDBCManager manager;

	public JDBCAntigenManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addAntigen(Antigen a) {

		try {
			String sql = "INSERT INTO antigen (a, b, c, dp, dq, dr) VALUES (?,?,?,?,?,?)";
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

	@Override
	public void deleteAntigen(Integer id) {
		try {
			String sql = "DELETE FROM antigen WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, id);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public Antigen getAntigen(Integer id) {
		Antigen ant = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM antigen WHERE id = ?" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Boolean a = rs.getBoolean("a");
				Boolean b = rs.getBoolean("b");
				Boolean c = rs.getBoolean("c");
				Boolean dp = rs.getBoolean("dp");
				Boolean dq = rs.getBoolean("dq");
				Boolean dr = rs.getBoolean("dr");
				ant = new Antigen(id, a, b, c, dp, dq, dr);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ant;
	}

}
