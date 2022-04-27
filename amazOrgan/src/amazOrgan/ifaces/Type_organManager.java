package amazOrgan.ifaces;

import amazOrgan.pojos.Type_organ;

public interface Type_organManager {

	//add a new type of organ to the database
	public void addTypeOfOrgan (Type_organ o);
	
	//search a type of organ by its ID
	public Type_organ searchTypeOfOrgan (Integer ID);
	
}
