package amazOrgan.ifaces;

import amazOrgan.pojos.User;
import java.util.List;
import amazOrgan.pojos.Role;



public interface UserManager {

	public void disconnect();
	public void newUser(User u);
	public Role getRole(String name);
	public List<Role> getRoles();
	
	/**
	 * 
	 * @param id
	 * @param passwd
	 * @return A User if there is a match, null if there isn't
	 */
	public User checkPassword(Integer id, String passwd);
	
}

