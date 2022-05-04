package amazOrgan.jpa;

package hospital.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hospital.ifaces.UserManager;
import hospital.pojos.Role;
import hospital.pojos.User;

public class JPAUserManager implements UserManager {

	private EntityManager em;
	
	
	public JPAUserManager() {
		this.connect();
	}

	private void connect() {
		em = Persistence.createEntityManagerFactory("doghospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		// Insert the roles needed only if they are not there already
		if (this.getRoles().isEmpty()) {
			Role owner = new Role("owner");
			Role vet = new Role("vet");
			this.newRole(owner);
			this.newRole(vet);
		}
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void newUser(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	private void newRole(Role r) {
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
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public User checkPassword(String email, String passwd) {
		// null user if match not found
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?", User.class);
		q.setParameter(1, email);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			q.setParameter(2, digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			u = (User) q.getSingleResult();
		} catch (NoResultException e) {}
		return u;
	}

}
