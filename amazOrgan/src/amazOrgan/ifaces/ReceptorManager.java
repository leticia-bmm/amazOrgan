package amazOrgan.ifaces;

import amazOrgan.pojos.Receptor;

public interface ReceptorManager {
	// Insert a Receptor
	public void addReceptor (Receptor r);
	// Update receptor (the only parameters we are updating are alive / urgency / status)
	public void updateReceptor (Receptor r); 
	//get receptor
	public Receptor getReceptor (Integer DNI);
	// Show by bloodtype
	public void showReceptorsByBloodType (String bloodtype);
	//Show by urgency
	public void showReceptorsByUrgency();
}
