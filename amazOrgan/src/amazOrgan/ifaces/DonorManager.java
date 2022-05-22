package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;

public interface DonorManager {

	/*addDonor (d: Donor): void 

	deleteDonor(DNI: int): void 

	getDonor( DNI: int): void  

	updateAlive (DNI: int): void 

	showDonorsByBloodType (bloodtype: text): void 
*/
	
	public void addDonor(Donor d, Integer medical_id);
	public void deleteDonor(Integer DNI);
	public Donor getDonor(Integer DNI);
	public List <Donor> showDonorsByBloodType (String bloodType);
	public void updateDonor (Donor d, int medicalId);
	public List <Donor> listAllDonors();
	public List <Donor> listMyDonors(Integer medical_id);
	
	
	
}
