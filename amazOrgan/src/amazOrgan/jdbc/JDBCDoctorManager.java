package amazOrgan.jdbc;


import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public void addDoctor(DoctorManager d) {
		
			try {
				String sql = "INSERT INTO doctor (medical id, phone number, name) VALUES (?,?,?)";
				PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				prep.setInteger(1, d.getMedical_id());
				prep.setInteger(2, d.getPhone_number());
				prep.setString(3, d.getName());
				prep.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
	}
	// chage my data
	public void changeMyData(Integer medical_id) {
		try {
			String sql = "UPDATE Doctor" + " SET medical_id=?" + " phone_number=?" + " name=?" ;
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInteger(1, d.getMedical_id());
			p.setInteger(2, d.getPhone_number());
			p.setString(3, d.getname());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		// show the data
		public void showMyData(Integer medical_id) {
			//TODO 
		
			
			
			
			
		}
		
		//list of receptors
		public List <Receptor> listMyPatients (Integer medical_ID){
			
			// TODO is not finished . i do not know how to do it
			List<Receptor> receptors = new ArrayList<Receptor>();
				try {
					Statement stmt = manager.getConnection().createStatement();
					String sql = "SELECT * FROM vets";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Integer id = rs.getInt("id");
						String name = rs.getString("name");
						String speciality = rs.getString("speciality");
						Vet v = new Vet(id, name, speciality);
						vets.add(v);
					}
					rs.close();
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return vets;
			}

		@Override
		public void addDoctor(Doctor d) {
			// TODO Auto-generated method stub
			
		}

			@Override
			
			
		}
		
	}
	
	
	
	
	
