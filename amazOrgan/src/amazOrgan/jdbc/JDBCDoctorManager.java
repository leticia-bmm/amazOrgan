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
import hospital.pojos.Vet;

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

	@Override
	public void showMyData(Doctor d) {
		try {
			String sql = "SELECT * FROM doctor WHERE medical_id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);	
			p.setInt(1, d.getmedical_id());
			p.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	// list of receptors
	public List<Receptor> listMyPatients(Integer Medical_Id) {

		List<Receptor> receptors = new ArrayList<Receptor>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM receptor ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { 			
				Integer dni = rs.getInt("dni");
				Date dob = rs.getDate("dob");
				String status= rs.getString("status");
				String blood_type= rs.getString("blood_type");
				Integer urgency =rs.getInt("urgency");
				Antigen antigen =rs.getAntigen("antigen");
				Antibody antibody = rs.getAntibody("antibody");
				Location location = rs.getLocation("location");
				Request request = rs.getRequest("request");
				Boolean alive = rs.getBoolean("alive");
				Receptor r = new Receptor (dni, dob, status, blood_type, urgency, antigen ,antibody, location, request, alive);
				receptors.add(r);
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return receptors;

	}

	@Override
	public Doctor getDoctor(Integer medical_id) {
		// TODO Auto-generated method stub
		return null;
	}


}
