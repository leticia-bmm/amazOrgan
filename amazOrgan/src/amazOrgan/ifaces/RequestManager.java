package amazOrgan.ifaces;

import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Request;

public interface RequestManager {
	
	/*addRequest (req: Request):void 

	updateDonorDNI (d: Donor): void */
	
	public void addRequest(Request r);
	public void updateOrganId (Organ o);
	public Request getRequest (Integer id);
	
	

}
