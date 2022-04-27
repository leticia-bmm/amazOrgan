package amazOrgan.ifaces;

import amazOrgan.pojos.Antigen;

public interface AntigenManager {

	//adding a new antigen to the database
	public void addAntigen (Antigen a);
	
	//delete an antigen from the database
	public void deleteAntigen (Integer ID);
	
	
}
