package amazOrgan.xml;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import amazOrgan.pojos.Request;

public class Xml2JavaRequest {

	private static final String PERSISTENCE_PROVIDER = "amazOrgan-provider";
	private static EntityManagerFactory factory;

	public static void unmarshallRequest() throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// take the organ from the xmls and put it in java
		File file = new File("./xmls/Request.xml");
		Request request = (Request) unmarshaller.unmarshal(file);

		System.out.println(request);

		// Store the report in the database
		// Create entity manager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction tx1 = em.getTransaction();

		// Start transaction
		tx1.begin();

		// Persist
		// We assume the authors are not already in the database
		// In a real world, we should check if they already exist
		// and update them instead of inserting as new
		em.persist(request);

		// End transaction
		tx1.commit();

	}

	public static void main(String[] ars) {

		System.out.println("working");
		try {
			unmarshallRequest();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
