package amazOrgan.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import amazOrgan.ifaces.OrganManager;
import amazOrgan.jdbc.JDBCManager;
import amazOrgan.jdbc.JDBCOrganManager;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.OrganList;

public class XmlManager {

	public static OrganManager organManager;

	// from Java to Xml
	public static void marshallOrgan() throws Exception {

		//everything that is a null, in the database is read as a 0
		JAXBContext jaxbContext = JAXBContext.newInstance(Organ.class); // has to be the name of the root element
		Marshaller marshaller = jaxbContext.createMarshaller();

		// to put everything in order, not all in the same line
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// print organs
		// ask for an organ to Xml file

		OrganList organs = organManager.getOrgans();
		System.out.println(organs);
		// do a query to acces the info inside the database
		File file = new File("./xmls/Organ.xml");
		marshaller.marshal(organs, file);

		// print my organ in console
		marshaller.marshal(organs, System.out);

	}

	// from Xml to Java
	public static void unmarshallOrgan() throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(OrganList.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// take the organ from the xmls and put it in java
		File file = new File("./xmls/Organ.xml");
		OrganList organs = (OrganList) unmarshaller.unmarshal(file);

		System.out.println(organs);
		Organ organ = null;
		

		// Store the organ in the database
		for (Organ organ2 : organs.getOrgans()) {
			
			//cheking if the organ is in the database
			organ = organManager.getOrgan(organ2.getID());
			
			if (organ == null) {
				organManager.addOrgan(organ2);
			} else {
				System.out.println("this organ is already in the database");
			}
		}

	}

	// from Xml to HTML

	public static void main(String[] ars) {
		JDBCManager jdbcManager = new JDBCManager();
		organManager = new JDBCOrganManager(jdbcManager);
		System.out.println("working");
		try {
			//marshallOrgan();
			unmarshallOrgan();

		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
