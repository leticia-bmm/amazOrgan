package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Receptor;

public interface Doctor {
	//insert a doctor
	public void addDoctor (Doctor d);
	// Get all Receptors
	public List <Receptor>listMyPatients (Integer medical_ID); 
	// Update my data
	public void changeMyData (Integer medical_ID);
	// show my data
	public void showMyData (Integer medica_ID);
	
	
	
	
	
}
