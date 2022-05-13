package amazOrgan.ifaces;

import amazOrgan.pojos.Receptor;

import java.util.List;

import amazOrgan.pojos.Doctor;

public interface ReceptorManager {
	// Insert a Receptor
	public void addReceptor (Receptor r);
	// Update receptor (the only parameters we are updating are alive / urgency / status)
	public void updateReceptor (Receptor r); 
	//get receptor
	public Receptor getReceptor (Integer dni);
	// Show by bloodtype
	public List <Receptor> showReceptorsByBloodType (String bloodtype);
	//Show by urgency
	public List <Receptor> showReceptorsByUrgency();
	//Assign doctor to receptor
	public void assignDoctor (Receptor r, Doctor d);
	//Unassign doctor an receptor 
	public void unassignDoctor (Receptor r, Doctor d);
}
