package amazOrgan.xml;

import java.io.File;
import java.util.List;

import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import amazOrgan.ifaces.OrganManager;
import amazOrgan.jdbc.JDBCManager;
import amazOrgan.jdbc.JDBCOrganManager;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.OrganList;

public class Java2XmlOrgan {

	public static OrganManager organManager;

	public static void marshallOrgan() throws Exception {

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

	public static void main(String[] ars) {
		JDBCManager jdbcManager = new JDBCManager();
		organManager = new JDBCOrganManager(jdbcManager);
		try {
			marshallOrgan();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
