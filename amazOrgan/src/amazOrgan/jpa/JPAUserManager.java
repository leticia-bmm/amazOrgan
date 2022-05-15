package amazOrgan.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import amazOrgan.ifaces.UserManager;
import amazOrgan.pojos.User;
import amazOrgan.pojos.Role;

public class JPAUserManager implements UserManager {

	private EntityManager em;  //create an entity manager to do the connection

	public JPAUserManager() {
		this.connect();
	}

	
	//the only place where connect is called is from the constructor so it should be private
	private void connect() {
		em = Persistence.createEntityManagerFactory("amazOrgan-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Insert the roles needed only if they are not there already
		if (this.getRoles().isEmpty()) {
			Role doctor = new Role("doctor");
			Role donor = new Role("donor");
			this.newRole(doctor);
			this.newRole(donor);
		}
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void newUser(User u) { // insert a new user into the database
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	
	// private because it is only called from connect (because we only want the roles that are defined)
	private void newRole(Role r) { // insert a new role into the database
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name = ?", Role.class);
		q.setParameter(1, name);
		return (Role) q.getSingleResult();
	}

	@Override
	public List<Role> getRoles() {
		// to access the database we need a query
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList(); // it returns the list of roles that have been read from the
															// database
		return roles;
	}

	@Override
	public User checkPassword(Integer id, String passwd) {
		// PASSWORDS ARE NEVER STORED INTO THE DATABASE
		// null user if match not found
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE id = ? AND password = ?", User.class);
		// from users because it is where the password is stored
		// User.class because it returns a User
		q.setParameter(1, id);		//the first ? is going to be the id
		
		//we have to create the digest to store it (cannot insert the password!!)
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");		//MD5: most common algorithm
			md.update(passwd.getBytes());
			//we get the hash from the digest
			byte[] digest = md.digest();
			q.setParameter(2, digest); //the digest is what is inserted into the database (because it is difficult to decode)
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			u = (User) q.getSingleResult();  //the user that is going to be received
		} catch (NoResultException e) {
		}
		return u;
	}

}