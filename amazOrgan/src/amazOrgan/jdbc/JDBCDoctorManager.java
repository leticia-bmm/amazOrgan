package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import amazOrgan.ifaces.DoctorManager;
import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Receptor;

public class JDBCDoctorManager implements DoctorManager {

	private JDBCManager manager;

	public JDBCDoctorManager(JDBCManager m) {
		this.manager = m;

	}

	@Override
	public void addDoctor(Doctor d) {
		try {
			String sql = "INSERT INTO doctor (medical id, phone number, name) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getmedical_id());
			prep.setInt(2, d.getphone_number());
			prep.setString(3, d.getname());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// change my data
	// here we should give the doctor directly
	// or do a new method called searchDoctor
	// this is so that we can work with the doctors method
	// TODO
	public void changeMyData(Integer medical_id) {
		try {
			String sql = "UPDATE Doctor" + " SET medical_id=?" + " phone_number=?" + " name=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			/*p.setInteger(1, d.getMedical_id());
			p.setInteger(2, d.getPhone_number());
			p.setString(3, d.getname());
			p.executeUpdate();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// show the data
	// the same problem as before
	public void showMyData(Integer medical_id) {
		
	}

	// list of receptors
	public List<Receptor> listMyPatients(Integer medical_ID) {

		// TODO is not finished . i do not know how to do it

		List<Receptor> receptors = new ArrayList<Receptor>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM receptor";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String speciality = rs.getString("speciality");
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return receptors;

	}

}
