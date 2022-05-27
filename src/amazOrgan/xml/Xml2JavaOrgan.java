package amazOrgan.xml;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import amazOrgan.pojos.Organ;

public class Xml2JavaOrgan {



	public static void unmarshallOrgan() throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(Organ.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// take the organ from the xmls and put it in java
		File file = new File("./xmls/Organ.xml");
		Organ organ = (Organ) unmarshaller.unmarshal(file);

		System.out.println(organ);

		// Store the organ in the database
		

	}

	public static void main(String[] ars) {

		System.out.println("working");
		try {
			unmarshallOrgan();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
