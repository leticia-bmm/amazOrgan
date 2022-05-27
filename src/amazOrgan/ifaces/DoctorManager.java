package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Receptor;

public interface DoctorManager {
	//insert a doctor
	public void addDoctor (Doctor d);
	// Update my data
	public void changeMyData (Doctor d);
	// get doctor
	public Doctor getDoctor (Integer medical_id);
	
	
	
	
	
}
