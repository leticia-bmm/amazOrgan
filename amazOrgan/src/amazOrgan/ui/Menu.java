package amazOrgan.ui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;


import amazOrgan.ifaces.AntibodyManager;
import amazOrgan.ifaces.AntigenManager;
import amazOrgan.ifaces.DoctorManager;
import amazOrgan.ifaces.DonorManager;
import amazOrgan.ifaces.LocationManager;
import amazOrgan.ifaces.OrganManager;
import amazOrgan.ifaces.ReceptorManager;
import amazOrgan.ifaces.RequestManager;
import amazOrgan.ifaces.Type_organManager;
import amazOrgan.ifaces.UserManager;
import amazOrgan.jdbc.JDBCAntibodyManager;
import amazOrgan.jdbc.JDBCAntigenManager;
import amazOrgan.jdbc.JDBCDoctorManager;
import amazOrgan.jdbc.JDBCDonorManager;
import amazOrgan.jdbc.JDBCLocationManager;
import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Location;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Receptor;
import amazOrgan.pojos.Request;
import amazOrgan.pojos.Role;
import amazOrgan.pojos.Type_organ;
import amazOrgan.jdbc.JDBCManager;
import amazOrgan.jdbc.JDBCOrganManager;
import amazOrgan.jdbc.JDBCReceptorManager;
import amazOrgan.jdbc.JDBCRequestManager;
import amazOrgan.jdbc.JDBCType_organManager;
import amazOrgan.jpa.JPAUserManager;
import amazOrgan.pojos.User;
import amazOrgan.xml.XmlManager;

public class Menu {

	private static AntibodyManager antibodyManager;
	private static AntigenManager antigenManager;
	private static DoctorManager doctorManager;
	private static DonorManager donorManager;
	private static LocationManager locationManager;
	private static OrganManager organManager;
	private static ReceptorManager receptorManager;
	private static RequestManager requestManager;
	private static Type_organManager type_organManager;
	private static UserManager userManager;
	private static XmlManager xmlManager;

	// ---------------------------------------------------------------------------------------------------------------------------
	// MENUS

	public static void doctor_menu(int medical_id) { // this works

		try {
			int option;
			while (true) {

				System.out.println("Please, choose an option:");
				System.out.println("1) See my patients");
				System.out.println("2) Change my data");
				System.out.println("3) Work with donors");
				System.out.println("4) Work with receptors");
				System.out.println("5) Change my password");
				System.out.println("0) Log out");
				option = Utilities.readIntFromKeyboardInRange("Option", 0, 5);

				switch (option) {

				case 1:
					// See my patients
					System.out.println("SEE MY PATIENTS"); // ok

					// see the receptors
					System.out.println("Receptors: \n");
					List<Receptor> list1 = receptorManager.listMyReceptors(medical_id);
					for (Receptor r : list1) {
						System.out.println("DNI: " + r.getDni());
						System.out.println("Status: " + r.getStatus());
						System.out.println("Alive: " + r.getAlive());
						System.out.println("Urgency: " + r.getUrgency() + "\n");

					}

					// see the donors
					System.out.println("Donors: \n");
					List<Donor> list2 = donorManager.listMyDonors(medical_id);
					for (Donor d : list2) {
						System.out.println("DNI: " + d.getdni());
						System.out.println("Alive: " + d.isAlive() + "\n");

					}

					break;

				case 2:
					// Change my data
					System.out.println("CHANGE MY DATA"); // ok
					// first show the info of the doctor
					doctorManager.getDoctor(medical_id);

					// ask for changes
					Integer number = Utilities.readIntFromKeyboard("Introduce your new phone number: ");
					Doctor d = new Doctor(medical_id, number);
					doctorManager.changeMyData(d);

					break;

				case 3:
					// call donor menu
					doc_donor_menu(medical_id);
					break;

				case 4:
					// call receptor menu
					doc_receptor_menu(medical_id);
					break;

				case 5:
					System.out.println("CHANGE MY PASSWORD"); // ok
					String newpass = Utilities.readStringFromKeyboard("Introduce your new password: ");
					userManager.updatePassword(medical_id, newpass);
					break;

				case 0:
					// go out of the method to exit the program from the main
					// this is the only moment we exit from this method
					System.out.println("Thanks for choosing amazOrgan");
					return;

				default:
					System.out.println("The selected option is not correct.");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void doc_donor_menu(int medical_id) {

		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Add donor");
				System.out.println("2) Show all available donors");
				System.out.println("3) Update existing donor");
				System.out.println("4) Get donor");
				System.out.println("0) Back");
				option = Utilities.readIntFromKeyboardInRange("Option", 0, 4);

				switch (option) {
				case 1:
					// Add donor ok
					System.out.println("ADD DONOR"); // ONLY DEAD DONORS (when they are alive, they register themselves)
					Doctor doc = doctorManager.getDoctor(medical_id);
					Donor d = Utilities.readDeadDonorFromKeyboard(doc, "Introduce the data of the donor");
					donorManager.addDonor(d);
					// this method also adds the organs

					// CALL THE MATCH FUNCTION
					Receptor rMatched = receptorManager.matchWithReceptor(d);
					if (rMatched != null) {
						System.out.println("Match found !!");
						System.out.println("This is the receptor matched with your donor: ");
						System.out.println(rMatched);
					}

					break;

				case 2:
					// Show donors
					System.out.println("SHOW ALL AVAILABLE DONORS"); // ok

					// these donors are not alive and their organs are available
					List<Donor> list = donorManager.listAllDonors();
					// for each donor, it only contains: DNI, bloodType and the name of the type of
					// organ
					for (Donor don : list) {
						System.out.println("DNI: " + don.getdni());
						System.out.println("Blood Type: " + don.getBloodType());
						System.out.println("Organs: ");
						List<Organ> organs = don.getOrgans();
						for (Organ o : organs) {
							System.out.println("\t" + o.getType_organ().getName());
						}

						System.out.println();
					}
					break;

				case 3:
					// Update existing donor
					System.out.println("UPDATE EXISTING DONOR");
					// this option is selected when a donor that was registered in the database but
					// now has died and the doctor
					// is adding the organs that are available to donate
					// to go from alive = true a alive = false and available = false to available =
					// true

					Integer donor_dni = Utilities.readIntFromKeyboard("Introduce the DNI of the donor you want to update: ");
					Donor don = donorManager.getDonor(donor_dni);
					if (don == null) {
						// if the donor is not in the database we cannot update him
						System.out.println("This donor is not in the database.");
						break;
					}
					Doctor doctor = doctorManager.getDoctor(medical_id);
					Donor newd = Utilities.readDonortoUpdate(don, doctor);
					donorManager.updateDonor(newd, medical_id);
					// this also updates the organs

					// CALL THE MATCH FUNCTION
					Receptor recMatched = receptorManager.matchWithReceptor(newd);
					if (recMatched != null) {
						System.out.println("Match found !!");
						System.out.println("This is the receptor matched with your donor: ");
						System.out.println(recMatched);
					}

					break;

				case 4:
					// Get donor
					System.out.println("GET DONOR");
					Integer donorDNI = Utilities.readPositiveIntFromKeyboard("Introduce the DNI of the donor");
					Donor donor = donorManager.getDonor(donorDNI);
					System.out.println(donor);

					break;

				case 0:
					// Back
					return; // we exit the method to return to doctor_menu

				default:
					System.out.println("The selected option is not correct.");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void doc_receptor_menu(Integer medical_id) {

		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Register receptor");
				System.out.println("2) Show receptors");
				System.out.println("3) Search receptor");
				System.out.println("4) Update data");
				System.out.println("0) Back");

				option = Utilities.readIntFromKeyboardInRange("Option", 0, 4);

				switch (option) {

				case 1:
					System.out.println("REGISTER RECEPTOR");
					// ask for all the information
					Receptor r = Utilities.addreceptormenu();
					receptorManager.addReceptor(r);
					Integer receptor_id = r.getDni();
					r = receptorManager.getReceptor(receptor_id);

					// CALL THE MATCH FUNCTION
					Donor donMatched = donorManager.matchWithDonor(r);
					if (donMatched != null) {
						System.out.println("Match found !!");
						System.out.println("This is the donor matched with your receptor: ");
						System.out.println(donMatched);
					}

					break;

				case 2:
					// Show receptors
					System.out.println("SHOW RECEPTORS");
					System.out.println("Please, choose an option:");
					System.out.println("1) By bloodtype");
					System.out.println("2) By urgency");

					int choice = Utilities.readIntFromKeyboardInRange("Option: ", 1, 2);

					switch (choice) {
					case 1:
						String bt = Utilities.askBloodType();
						List<Receptor> receptorsbloodType = receptorManager.showReceptorsByBloodType(bt);
						for (Receptor receptor1 : receptorsbloodType) {
							System.out.println("\nDNI: " + receptor1.getDni() + "\nType of Organ: "
									+ receptor1.getRequest().getType_organ().getName() + "\nStatus: "
									+ receptor1.getStatus() + "\nUrgency: " + receptor1.getUrgency());
						}

						break;

					case 2:
						List<Receptor> receptorsUrgency = receptorManager.showReceptorsByUrgency();
						for (Receptor receptor1 : receptorsUrgency) {
							System.out.println("\nDNI: " + receptor1.getDni() + "\nType of Organ: "
									+ receptor1.getRequest().getType_organ().getName() + "\nStatus: "
									+ receptor1.getStatus() + "\nUrgency: " + receptor1.getUrgency());
						}

						break;

					default:
						System.out.println("The selected option is not correct.");
						break;
					}

					break;

				case 3:
					// Search receptor
					System.out.println("SEARCH RECEPTOR");
					Integer receptorDNI = Utilities.readPositiveIntFromKeyboard("Introduce the DNI of the receptor");
					Receptor receptor = receptorManager.getReceptor(receptorDNI);
					System.out.println(receptor);

					break;

				case 4:
					// Update data
					Receptor oldreceptor = null;
					Receptor newreceptor = null;
					System.out.println("UPDATE DATA");

					Integer receptor_DNI = Utilities.readPositiveIntFromKeyboard("Introduce the DNI of the receptor");
					oldreceptor = receptorManager.getReceptor(receptor_DNI);

					if (oldreceptor == null) {
						System.out.println("DNI incorrect. This donor is not in the database");
					} else {
						System.out.println(oldreceptor);
						newreceptor = Utilities.updateReceptorMenu(oldreceptor);
						receptorManager.updateReceptor(newreceptor);

						// we CALL THE MATCH FUNCTION when status = waiting
						if (newreceptor.getStatus() == "Waiting") {

							Donor dMatched = donorManager.matchWithDonor(newreceptor);
							if (dMatched != null) {
								System.out.println("Match found !!");
								System.out.println("This is the donor matched with your receptor: ");
								System.out.println(dMatched);
							}
						}

					}

					break;

				case 0:
					// Back
					return; // we exit the method to return to doctor_menu

				default:
					System.out.println("The selected option is not correct.");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void donor_menu(int DNI) {
		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Add organs");
				System.out.println("2) See my data");
				System.out.println("3) Delete myself");
				System.out.println("4) Change my password");
				System.out.println("0) Log out");

				option = Utilities.readIntFromKeyboardInRange("Option: ", 0, 4);
				switch (option) {

				case 1:
					System.out.println("ADD ORGANS");
					Donor d1 = donorManager.getDonor(DNI);
					List<Organ> organs = Utilities.readOrgansAliveDonorFromKeyboard(d1);
					for (Organ o : organs) {
						organManager.addOrganAlive(o);
					}

					break;

				case 2:
					System.out.println("SEE MY DATA");
					Donor donor = donorManager.getDonor(DNI);
					System.out.println(donor);
					break;

				case 3:
					System.out.println("DELETE MYSELF");
					// when the donor is deleted from the database, we also have to delete him as a
					// user
					Donor deleting_donor = donorManager.getDonor(DNI);

					List<Organ> deleting_organs = deleting_donor.getOrgans();
					for (Organ organ : deleting_organs) {
						organManager.deleteOrgan(organ.getID());
					}

					donorManager.deleteDonor(DNI);
					userManager.deleteUserDonor(DNI);
					System.out.println("Thanks for choosing amazOrgan.");

					return;

				case 4:
					System.out.println("CHANGE MY PASSWORD");
					String newpass = Utilities.readStringFromKeyboard("Introduce your new password");
					userManager.updatePassword(DNI, newpass);
					break;

				case 0:
					// Exit
					System.out.println("Thanks for choosing amazOrgan");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	// JPA

	// this method works
	public static void loginDoctor() throws Exception {
		// User needs to provide an id and a password
		Integer id = Utilities.readIntFromKeyboard("Insert your Medical Id:");
		String password = Utilities.readStringFromKeyboard("Insert your password:");

		User u = userManager.checkPassword(id, password); // this method returns a user
		// the user can be valid or not:
		// Enter the doctor menu if the combination was valid
		// we also have to check if the user is a doctor and not a donor

		if (u != null && u.getRole().getName().equals("doctor")) {
			System.out.println("Login succesful");
			// enter doctor menu with that user

			doctor_menu(u.getId());
		} else {

			System.out.println("Medical Id or Password incorrect. Please insert them again.");
			id = Utilities.readIntFromKeyboard("Medical Id:");
			password = Utilities.readStringFromKeyboard("Insert your password:");
			u = userManager.checkPassword(id, password);

			if (u != null && u.getRole().getName().equals("doctor")) {
				System.out.println("Login succesful");
				doctor_menu(u.getId());
			}
			return;

		}

	}

	// this method works
	public static void loginDonor() throws Exception {

		Integer id = Utilities.readIntFromKeyboard("Insert your DNI:");
		String password = Utilities.readStringFromKeyboard("Insert your password:");

		User u = userManager.checkPassword(id, password); // this method returns a user
		// the user can be valid or not:
		// Enter the doctor menu if the combination was valid
		// we also have to check if the user is a donor and not a doctor

		if (u != null && u.getRole().getName().equals("donor")) {
			System.out.println("Login succesful");
			// enter donor menu with that user
			donor_menu(u.getId());

		} else {
			// we are only letting the user introduce the data again once more. If they are
			// wrong again, we go back
			// while (u == null || !u.getRole().getName().equals("donor")) {

			System.out.println("DNI or Password incorrect. Please insert them again.");
			id = Utilities.readIntFromKeyboard("DNI:");
			password = Utilities.readStringFromKeyboard("Insert your password:");

			u = userManager.checkPassword(id, password);

			if (u != null && u.getRole().getName().equals("donor")) {
				System.out.println("Login succesful");
				donor_menu(u.getId());
			}
			return;
		}

	}

	// this method works
	public static void registerDoctor() throws Exception {

		// ask for all the information
		Integer medical_id = Utilities.readIntFromKeyboard("Insert your Medical Id:");

		// COMPROBAR QUE NO EXISTE
		Doctor doc = doctorManager.getDoctor(medical_id);
		if (doc != null) {
			// Si existe, hacer return
			System.out.println("This medical Id is alredy registered in the database. Try to login as a doctor.");
			return;
		}

		Integer phone_number = Utilities.readIntFromKeyboard("Insert your phone number:");
		String name = Utilities.readStringFromKeyboard("Insert your name:");
		List<Donor> donors = null;
		List<Receptor> receptors = null;

		// we have to create a Doctor but also a User
		Doctor d = new Doctor(medical_id, phone_number, name, receptors, donors);

		String password = Utilities.readStringFromKeyboard("Insert your password:");

		// to create the digest:
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // MD5: most common algorithm
			md.update(password.getBytes());

			// we get the hash from the digest
			byte[] digest = md.digest();
			User u = new User(medical_id, digest);
			// this user needs to have a role
			Role role = userManager.getRole("doctor");

			// remember to work with both sides!!
			u.setRole(role);
			role.addUser(u);

			// insert the user using userManager

			userManager.newUser(u);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// insert the Doctor into the database
		doctorManager.addDoctor(d);
		doctor_menu(d.getMedical_id());

	}

	// this method works
	public static void registerDonor() throws Exception {

		// ask for all the information
		// can only insert insert his dni, dob and organs (type of organ)
		// alive by default is true
		// doctor by default would be the unassigned

		Integer dni = Utilities.readIntFromKeyboard("Introduce your DNI:");

		// CHECK IF THE USER EXISITS
		Donor don = donorManager.getDonor(dni);
		if (don != null) {
			// if it exisits, return
			System.out.println("This DNI is alredy registered in the database. Try to login as a donor.");
			return;
		}

		LocalDate dob = Utilities.readDateFromKeyboard();

		// we have to create a Donor but also a User
		Donor d = new Donor(dni, dob, true);

		String password = Utilities.readStringFromKeyboard("Insert your password:");

		// to create the digest:
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // MD5: most common algorithm
			md.update(password.getBytes());
			// we get the hash from the digest
			byte[] digest = md.digest();
			User u = new User(dni, digest);
			// this user needs to have a role
			Role role = userManager.getRole("donor");

			// remember to work with both sides!!
			u.setRole(role);
			role.addUser(u);

			// insert the user using userManager
			userManager.newUser(u);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// insert the Doctor into the database
		donorManager.addAliveDonor(d);
		donor_menu(d.getdni());

	}

//-----------------------------------------------------------------------------------------------------------------------------

//MAIN

	public static void main(String[] ars) {

		System.out.println("Welcome to amazOrgan!");

		// Initialize database for JDBC
		// -----------------------------
		JDBCManager jdbcManager = new JDBCManager();
		antibodyManager = new JDBCAntibodyManager(jdbcManager);
		antigenManager = new JDBCAntigenManager(jdbcManager);
		doctorManager = new JDBCDoctorManager(jdbcManager);
		donorManager = new JDBCDonorManager(jdbcManager);
		locationManager = new JDBCLocationManager(jdbcManager);
		organManager = new JDBCOrganManager(jdbcManager);
		receptorManager = new JDBCReceptorManager(jdbcManager);
		requestManager = new JDBCRequestManager(jdbcManager);
		type_organManager = new JDBCType_organManager(jdbcManager);
		xmlManager = new XmlManager();

		// Initialize database for JPA
		// ----------------------------
		userManager = new JPAUserManager();

		// Menu loop
		try {

			Integer option;
			while (true) {
				System.out.println("Please, choose an option: ");
				System.out.println("1) Login as a Doctor");
				System.out.println("2) Register as a Doctor");
				System.out.println("3) Login as a Donor");
				System.out.println("4) Register as a Donor");
				System.out.println("5) See our web page");
				System.out.println("6) Import an xml");
				System.out.println("7) Export an xml");
				System.out.println("0) Exit");

				option = Utilities.readIntFromKeyboardInRange("Option: ", 0, 7);

				switch (option) {
				case 1:
					// Login as a Doctor
					System.out.println("LOGIN AS A DOCTOR");
					loginDoctor(); // this method calls the doctor menu
					break;

				case 2:
					// Register as a Doctor
					System.out.println("REGISTER AS A DOCTOR");
					registerDoctor();
					break;

				case 3:
					// Login as a Donor
					System.out.println("LOGIN AS A DONOR");
					loginDonor();
					break;

				case 4:
					// Register as a Donor
					System.out.println("REGISTER AS A DONOR");
					registerDonor();
					break;

				case 5:
					// See our web page
					System.out.println("Our web page will automatically appear in the XMLS folder!"
							+ "\nRefresh if not!");
					xmlManager.simpleTransform("./xmls/Organ.xml", "./xmls/Organ.xslt", "./xmls/SuperWebPage.html");
					break;

				case 6:
					// Import an Xml (put in the database)
					try {
						String path = Utilities.readStringFromKeyboard("Introduce the path of the file you want to import: ");
						xmlManager.unmarshallOrgan(path);
					}catch(Exception e) {
						System.out.println("No file found with that name :(");
					}
					break;

				case 7:
					// Export an xml (take out of the database)
					try {
						String path = Utilities.readStringFromKeyboard("Introduce the path were you want to export: ");
						xmlManager.marshallOrgan(path);
					}catch(Exception e) {
						System.out.println("Not a valid file");
					}

					break;
					
				case 0:
					// Close the connection with the database
					jdbcManager.disconnect();
					System.exit(0);

				default:
					System.out.println("The selected option is not correct.");
					break;
				}

			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//	public static void main(String[] ars) {
//		JDBCManager jdbcManager = new JDBCManager();
//		donorManager = new JDBCDonorManager(jdbcManager);
//		antigenManager = new JDBCAntigenManager(jdbcManager);
//		antibodyManager = new JDBCAntibodyManager(jdbcManager);
//		locationManager = new JDBCLocationManager(jdbcManager);
//		doctorManager = new JDBCDoctorManager(jdbcManager);
//		receptorManager = new JDBCReceptorManager(jdbcManager);
//		organManager = new JDBCOrganManager(jdbcManager);
//		userManager = new JPAUserManager();
//
//		try {
//			
//			Donor don = donorManager.getDonor(donor_dni);
//			Doctor doctor = doctorManager.getDoctor(222);
//			Donor newd = Utilities.readDonortoUpdate(don, doctor);
//			donorManager.updateDonor(newd, 222);
//			// this also updates the organs
//
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		jdbcManager.disconnect();
//		userManager.disconnect();
//		System.exit(0);
//	}
//
//}
}
