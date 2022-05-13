package amazOrgan.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import amazOrgan.ifaces.DoctorManager;
import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Location;
import amazOrgan.pojos.Receptor;
import amazOrgan.pojos.Request;

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
	// we have to do a method to change the doctor
	public void changeMyData(Doctor d) {
		try {
			String sql = "UPDATE Doctor " + " phone_number=? " + " name=? WHERE medical_id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, d.getphone_number());
			p.setString(2, d.getname());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// list of receptors
	public List<Receptor> listMyPatients(Integer medical_id) {
		// 
		List<Receptor> receptors = new ArrayList<Receptor>();
		Receptor  r = new Receptor();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM examines WHERE medical_id=" + medical_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {	
				Integer receptor_id = rs.getInt("receptor_id");
				r = JDBCReceptorManager.getReceptor();
				receptors.add(r);			
				rs.close();
				
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return receptors;

	}

	@Override
	public Doctor getDoctor(Integer medical_id) {
		
		Doctor d = new Doctor ();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctor WHERE medical_id = ?" + medical_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer phone_number = rs.getInt("phone_number");
				String name = rs.getString("name");
											}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return d;
		
		// TODO Auto-generated method stub
	
	}


}
