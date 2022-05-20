package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import amazOrgan.ifaces.LocationManager;
import amazOrgan.pojos.Location;

//public void addLocation (Location l);
//public void deleteLocation (Integer ID);

public class JDBCLocationManager implements LocationManager {

	private JDBCManager manager;

	public JDBCLocationManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addLocation(Location l) {
		try {
			String sql = "INSERT INTO location (latitude, longitude) VALUES (?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, l.getLatitude());
			prep.setFloat(2, l.getLongitude());
			prep.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteLocation(Integer ID) {
		try {
			String sql = "DELETE FROM location WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, ID);
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Location getLocation(Integer id) {
		Location l = null;
		try {
			String sql = "SELECT * FROM location WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Float latitude = rs.getFloat("latitude");
				Float longitude = rs.getFloat("longitude");
				l = new Location(id, latitude, longitude);
			}
			rs.close();
			prep.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}

}
