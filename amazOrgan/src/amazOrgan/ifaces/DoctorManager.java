package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Receptor;

public interface DoctorManager {
	//insert a doctor
	public void addDoctor (DoctorManager d);
	// Get all Receptors
	public List <Receptor>listMyPatients (Integer medical_ID); 
	// Update my data
	public void changeMyData (Integer medical_ID);
	// show my data
	public void showMyData (Integer medical_ID);
	
	
	
	
	
}
