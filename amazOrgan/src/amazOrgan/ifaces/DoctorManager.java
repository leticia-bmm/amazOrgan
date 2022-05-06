package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Receptor;

public interface DoctorManager {
	//insert a doctor
	public void addDoctor (Doctor d);
	// Get all Receptors
	public List <Receptor>listMyPatients (Integer medical_ID); 
	// Update my data
	public void changeMyData (Doctor d);
	// show my data
	public void showMyData (Doctor d);
	
	
	
	
	
}
