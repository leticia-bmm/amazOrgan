package amazOrgan.ifaces;

import amazOrgan.pojos.Antibody;

public interface AntibodyManager {

	//add a new antibody to the database
	public void addAntibody (Antibody a);
	
	//get antibody
	public Antibody getAntibody (Integer id);
	
	
	//delete an antibody from the database
	public void deleteAntibody (Integer ID);
	
}
