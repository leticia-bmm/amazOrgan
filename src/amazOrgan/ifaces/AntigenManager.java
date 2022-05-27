package amazOrgan.ifaces;

import amazOrgan.pojos.Antigen;

public interface AntigenManager {

	//adding a new antigen to the database
	public void addAntigen (Antigen a);
	
	//get antigen
	public Antigen getAntigen(Integer id);
	
	//delete an antigen from the database
	public void deleteAntigen (Integer ID);
	
	
}
