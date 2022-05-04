package amazOrgan.ifaces;

import amazOrgan.pojos.User;

public interface UserManager {

	public void disconnect();
	public void newUser(User u);
	/**
	 * 
	 * @param medical_id
	 * @param passwd
	 * @return A User if there is a match, null if there isn't
	 */
	public User checkPassword(String medical_id, String passwd);
	
}

