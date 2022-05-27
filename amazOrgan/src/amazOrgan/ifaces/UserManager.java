package amazOrgan.ifaces;

import amazOrgan.pojos.User;
import java.util.List;
import amazOrgan.pojos.Role;



public interface UserManager {

	public void disconnect();
	public void newUser(User u);
	public Role getRole(String name);
	public List<Role> getRoles();
	public void deleteUserDonor(Integer dni);
	public void updatePassword(Integer id, String newPassword);
	
	/**
	 * 
	 * @param id
	 * @param passwd
	 * @return A User if there is a match, null if there isn't
	 */
	public User checkPassword(Integer id, String passwd);
	
}

