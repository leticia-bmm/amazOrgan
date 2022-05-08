package amazOrgan.jdbc;

import java.sql.PreparedStatement;

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
			System.out.println(l.getLatitude());
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
			String sql = "DELETE FROM location WHERE id= “?”";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, ID);
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
