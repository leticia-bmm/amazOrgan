package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Role;
import amazOrgan.pojos.User;

public interface UserManager {

	public void disconnect();
	public void newUser(User u);
	public Role getRole(String name);
	public List<Role> getRoles();
	/**
	 * 
	 * @param medical_id
	 * @param passwd
	 * @return A User if there is a match, null if there isn't
	 */
	public User checkPassword(String medical_id, String passwd);
	
}

