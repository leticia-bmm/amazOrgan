package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import amazOrgan.ifaces.RequestManager;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Request;
import amazOrgan.pojos.Type_organ;

public class JDBCRequestManager implements RequestManager {

	private JDBCManager manager;

	public JDBCRequestManager(JDBCManager m) {
		this.manager = m;
	}

	// TODO test methods

	@Override
	public void addRequest(Request r) {
		try {
			String sql = "INSERT INTO request (id_type_organ, received, organ_id, size_organ) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, r.getType_organ().getId());
			prep.setBoolean(2, r.isReceived());
			prep.setInt(3, r.getOrgan().getID());
			prep.setFloat(4, r.getSize());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrganId(Request r) {
		try {
			String sql = "UPDATE request SET received=? WHERE organ_id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setBoolean(1, r.isReceived());
			p.setInt(2, r.getOrgan().getID());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Request getRequest(Integer id) {
		Request r = null;
		Type_organ t = null;
		Organ o = null;
		Donor d = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM request AS r1 " 
					+ "LEFT JOIN type_of_organ AS t1 ON r1.id_type_organ = t1.id "
					+ "LEFT JOIN organ AS o1 ON r1.id = o1.id " 
					+ "WHERE r1.id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Boolean received = rs.getBoolean("received");
				Float size_organ_request = rs.getFloat(5);

				Integer id_type_organ = rs.getInt(2);
				String name = rs.getString("name");
				Integer lifespan = rs.getInt("lifespan");
				t = new Type_organ(id_type_organ, name, lifespan);

				Integer organ_id = rs.getInt("id_organ");
				//id_organ is read as a 0 when the integer is null
				//it is not out fault, but we read it as a 0 

				if (organ_id != 0) {
					Float size_organ = rs.getFloat(11);
					Boolean available = rs.getBoolean("available");
					Integer donor_dni = rs.getInt("donor_dni");
					d = new Donor(donor_dni);
					o = new Organ(organ_id, t, size_organ, available, d);
				}

				r = new Request(id, t, size_organ_request, received, o);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

}
