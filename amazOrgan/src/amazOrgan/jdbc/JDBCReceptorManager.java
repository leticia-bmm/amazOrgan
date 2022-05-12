package amazOrgan.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import amazOrgan.ifaces.ReceptorManager;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Receptor;
import amazOrgan.jdbc.JDBCAntigenManager;

public class JDBCReceptorManager implements ReceptorManager{
	
	private JDBCManager manager;
	
	public JDBCReceptorManager(JDBCManager m) {
		this.manager = m;
	
	}

	@Override
	public void addReceptor(Receptor r) {
			try {
				String sql = "INSERT INTO receptor (dni, dob, status, blood_type, alive, urgency, antigen, antibody, location, request) VALUES (?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				prep.setInt(1, r.getDni());
				prep.setDate(2, r.getDob());
				prep.setString(3, r.getStatus());
				prep.setString(4, r.getBlood_type());
				prep.setBoolean(5, r.getAlive());
				prep.setInt(6, r.getUrgency());
				prep.setInt(7, r.getAntigen().getId());
				prep.setInt(8, r.getAntibody().getID());
				prep.setInt(9, r.getLocation().getId());
				prep.setInt(10, r.getRequest().getId());
				prep.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	//inUpdate receptor, we are giving the NEW receptor to the function in 
	//JDBC, that means that we have to create the function change the 
	//data of the receptor in JAVA
	//TODO write the java function for the JDBCs
	@Override
	public void updateReceptor (Receptor r) {
		try {
			String sql = "UPDATE receptor " + "SET alive = ? " + "status = ? " + "urgency = ? " + "WHERE dni = ?" ;
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1,  r.getAlive());
			prep.setString(2, r.getStatus());
			prep.setInt(3, r.getUrgency());
			prep.setInt(4, r.getDni());
			prep.executeUpdate();			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Receptor getReceptor(Integer dni) {
		Receptor r = new Receptor ();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM receptor WHERE dni = ?" + dni;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Date dob = rs.getDate("dob");
				String status = rs.getString("status");
				String blood_type = rs.getString("blood_type");
				Integer urgency = rs.getInt("urgency");
				Integer antigen_id = rs.getInt("antigen_id");
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return r;
		
	}

	
	@Override
	public void assignDoctor(Receptor r, Doctor d) {
		try {
			String sql = "INSERT INTO examines (receptor_id, medical_id) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, r.getDni());
			p.setInt(2, d.getmedical_id());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unassignDoctor(Receptor r, Doctor d) {
		try {
			String sql = "DELETE FROM examines WHERE receptor_id=? AND medical_id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, r.getDni());
			p.setInt(2, d.getmedical_id());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Receptor> showReceptorsByBloodType(String bloodtype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receptor> showReceptorsByUrgency() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	

}
