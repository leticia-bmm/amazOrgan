package amazOrgan.ifaces;

import amazOrgan.pojos.Organ;
import amazOrgan.pojos.OrganList;
import amazOrgan.pojos.Type_organ;

public interface OrganManager {

	/*addOrgan(o: organ): void 
	 * 
	 * getOrgan(id : int): organ 
	 * 
	 * 
	 * deleteOrgan(id: int):void //when a donor does not want to donate anymore */
	
	public void addOrgan(Organ o);
	public void deleteOrgan(Integer id);
	public Organ getOrgan (Integer id);
	public Type_organ getType_organOfOrgan(Integer id_type_organ);
	public OrganList getOrgans();
	
	
	
}
