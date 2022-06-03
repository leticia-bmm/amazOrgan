package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Receptor;

public interface DonorManager {

	
	
	public void addDonor(Donor d);
	public void addAliveDonor(Donor d);
	public void deleteDonor(Integer DNI);
	public Donor getDonor(Integer DNI);
	//public List <Donor> showDonorsByBloodType (String bloodType);
	public void updateDonor (Donor d, Integer medicalId);
	public List <Donor> listAllDonors();
	public List <Donor> listMyDonors(Integer medical_id);
	public Donor matchWithDonor(Receptor r);
	
	
	
}
