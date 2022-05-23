package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;

public interface DonorManager {

	
	
	public void addDonor(Donor d);
	public void addAliveDonor(Donor d);
	public void deleteDonor(Integer DNI);
	public Donor getDonor(Integer DNI);
	public List <Donor> showDonorsByBloodType (String bloodType);
	public void updateDonor (Donor d, int medicalId);
	public List <Donor> listAllDonors();
	public List <Donor> listMyDonors(Integer medical_id);
	
	
	
}
