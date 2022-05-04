package amazOrgan.jpa;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import amazOrgan.ifaces.UserManager;
import amazOrgan.pojos.User;


public class JPAUserManager implements UserManager {

	private EntityManager em;
	
	
	public JPAUserManager() {
		this.connect();
	}

	private void connect() {
		em = Persistence.createEntityManagerFactory("amazOrgan-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
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


	@Override
	public User checkPassword(String medical_id, String passwd) {
		// null user if match not found
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE medical_id = ? AND password = ?", User.class);
		q.setParameter(1, medical_id);
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
