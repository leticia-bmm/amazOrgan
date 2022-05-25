package amazOrgan.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import amazOrgan.ifaces.RequestManager;
import amazOrgan.jdbc.JDBCManager;
import amazOrgan.jdbc.JDBCRequestManager;
import amazOrgan.pojos.Request;

public class Java2XmlRequest {

	public static RequestManager requestManager;

	public static void marshallRequest() throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(Request.class); // has to be the name of the root element
		Marshaller marshaller = jaxbContext.createMarshaller();

		// to put everything in order, not all in the same line
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// print organs
		// ask for an organ to Xml file

		Request request = requestManager.getRequest(6);
		System.out.println(request);
		// do a query to acces the info inside the database
		File file = new File("./xmls/Request.xml");
		marshaller.marshal(request, file);

		// print my organ in console
		marshaller.marshal(request, System.out);

	}

	public static void main(String[] ars) {
		JDBCManager jdbcManager = new JDBCManager();
		requestManager = new JDBCRequestManager(jdbcManager);
		try {
			marshallRequest();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
