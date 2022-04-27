package amazOrgan.ifaces;

import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Request;

public interface RequestManager {
	
	/*addRequest (req: Request):void 

	updateDonorDNI (d: Donor): void */
	
	public void addRequest(Request r);
	public void updateDonorDNI (Donor d);
	

}
