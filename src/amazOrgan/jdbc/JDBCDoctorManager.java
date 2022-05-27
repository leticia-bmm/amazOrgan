package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import amazOrgan.ifaces.DoctorManager;
import amazOrgan.pojos.Doctor;

public class JDBCDoctorManager implements DoctorManager {


	private JDBCManager manager;

	public JDBCDoctorManager(JDBCManager m) {
		this.manager = m;

	}

	@Override
	public void addDoctor(Doctor d) {
		try {
			String sql = "INSERT INTO doctor (medical_id, phone_number, name) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getMedical_id());
			prep.setInt(2, d.getPhone_number());
			prep.setString(3, d.getName());
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
	// we have to do a method to change the doctor
	public void changeMyData(Doctor d) {
		try {
			String sql = "UPDATE Doctor " + " phone_number=? " + " name=? WHERE medical_id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, d.getPhone_number());
			p.setString(2, d.getName());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Doctor getDoctor(Integer medical_id) {

		Doctor d = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctor WHERE medical_id = " + medical_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer phone_number = rs.getInt("phone_number");
				String name = rs.getString("name");
				//TODO finish the method
				d = new Doctor (medical_id, phone_number, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;

	}

}
