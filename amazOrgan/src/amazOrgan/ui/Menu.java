package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
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

public class Menu {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

	public static void doctor_menu(int medical_id) {

		try {
			int option;
			while (true) {

				System.out.println("Please, choose an option:");
				System.out.println("1) See my patients");
				System.out.println("2) Change my data");
				System.out.println("3) Work with donors");
				System.out.println("4) Work with receptors");
				System.out.println("5) Change my password");
				System.out.println("0) Exit");
				option = Utilities.readIntFromKeyboardInRange("Option", 0, 5);

				switch (option) {

				case 1:
					// See my patients
					System.out.println("SEE MY PATIENTS");
					System.out.println(receptorManager.listMyReceptors(medical_id));
					System.out.println(donorManager.listMyDonors(medical_id));

					break;

				case 2:
					// Change my data
					System.out.println("CHANGE MY DATA");
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
					System.out.println("CHANGE MY PASSWORD");
					String newpass = Utilities.readStringFromKeyboard("Introduce your new password");
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
					// Add donor
					System.out.println("ADD DONOR"); // ONLY DEAD DONORS (when they are alive, they register themselves)
					Donor d = Utilities.readDonorFromKeyboard(medical_id, "Introduce the data of the donor");
					donorManager.addDonor(d);
					// introduce the organs in a list

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
					System.out.println("SHOW ALL AVAILABLE DONORS");

					// these donors are alive and their organs are available
					List<Donor> list = donorManager.listAllDonors();
					System.out.println(list);

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
						//if the donor is not in the database we cannot update him
						System.out.println("This donor is not in the database.");
						break;
					}
					
					Donor newd = Utilities.readDonortoUpdate(don);
					donorManager.updateDonor(newd, medical_id);

					// CALL THE MATCH FUNCTION
					Receptor recMatched = receptorManager.matchWithReceptor(d);
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
					donorManager.getDonor(donorDNI);

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

	public static void doc_receptor_menu(int medical_id) {

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
					//ask for all the information
					Receptor r = Utilities.addreceptormenu();
					receptorManager.addReceptor(r);

					// CALL THE MATCH FUNCTION

					break;

				case 2:
					// Show receptors
					// list receptors (JDBCReceptorManager)
					// another menu for: by bloodType, by Urgency
					System.out.println("SHOW RECEPTORS");
					System.out.println("Please, choose an option:");
					System.out.println("1) By bloodtype");
					System.out.println("2) By urgency");

					int choice = Integer.parseInt(reader.readLine());
					switch (choice) {
					case 1:
						String bt = Utilities.askBT();
						System.out.println(receptorManager.showReceptorsByBloodType(bt));

						break;

					case 2:
						System.out.println(receptorManager.showReceptorsByUrgency());

						break;

					default:
						System.out.println("The selected option is not correct.");
						break;
					}
					break;

				case 3:
					// Search receptor
					System.out.println("SEARCH RECEPTOR");
					System.out.println("INSERT DNI");
					Integer receptorDNI = Integer.parseInt(reader.readLine());
					receptorManager.getReceptor(receptorDNI);
					// getReceptor();
					// DONE
					break;

				case 4:
					// + // Update data
					// YOOOOOOOOOO // + // Update data
					Receptor oldreceptor = null;
					Receptor newreceptor = null;
					System.out.println("UPDATE DATA");
					System.out.println("INSERT DNI");
					Integer receptor_DNI = Integer.parseInt(reader.readLine());
					oldreceptor = receptorManager.getReceptor(receptor_DNI);
					if (oldreceptor == null) {
						System.out.println("DNI incorrect");
					} else {
						System.out.println(oldreceptor);
						newreceptor = Utilities.updatereceptormenu(oldreceptor);

						receptorManager.updateReceptor(newreceptor);
					}

					break;
				// we call match function when changing urgency and status(only when waiting)

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

//
//	public static void main(String[] ars) {
//
//		System.out.println("Welcome to amazOrgan!");
//
//		// TODO
//		// Initialize database for JDBC
//		// -----------------------------
//		JDBCManager jdbcManager = new JDBCManager();
//		antibodyManager = new JDBCAntibodyManager(jdbcManager);
//		antigenManager = new JDBCAntigenManager(jdbcManager);
//		doctorManager = new JDBCDoctorManager(jdbcManager);
//		donorManager = new JDBCDonorManager(jdbcManager);
//		locationManager = new JDBCLocationManager(jdbcManager);
//		organManager = new JDBCOrganManager(jdbcManager);
//		receptorManager = new JDBCReceptorManager(jdbcManager);
//		requestManager = new JDBCRequestManager(jdbcManager);
//		type_organManager = new JDBCType_organManager(jdbcManager);
//
//		// = new ...Manager()
//
//		// TODO
//		// Initialize database for JPA
//		// ----------------------------
//		userManager = new JPAUserManager();
//
//		// Menu loop
//		try {
//			Integer option;
//			while (true) {
//				System.out.println("Please, choose an option: ");
//				System.out.println("1) Login as a Doctor");
//				System.out.println("2) Register as a Doctor");
//				System.out.println("3) Login as a Donor");
//				System.out.println("4) Register as a Donor");
//				System.out.println("5) See our web page");
//				System.out.println("6) Import an xml");
//				System.out.println("7) Export an xml");
//
//				option = Integer.parseInt(reader.readLine());
//
//				switch (option) {
//				case 1:
//					// Login as a Doctor
//					System.out.println("LOGIN AS A DOCTOR");
//					// login_doctor() ask here for the id and the password
//					int medical_id = 1;
//					doctor_menu(medical_id); // this method is called from the login
//
//					break;
//
//				case 2:
//					// Register as a Doctor
//					System.out.println("REGISTER AS A DOCTOR");
//					// register_doctor() ask here for the id and the password
//					int id = 1;
//					doctor_menu(id); // this method is called from the register
//					break;
//
//				case 3:
//					// Login as a Donor
//					System.out.println("LOGIN AS A DONOR");
//					// login_donor() ask here for the id and the password
//					int dni = 1;
//					donor_menu(dni); // this method is called from the login
//					// you can only insert info or see your data
//
//					break;
//
//				case 4:
//					// Register as a Donor
//					System.out.println("Register AS A DONOR");
//					// register_donor() ask here for the id and the password
//					int DNI = 3;
//					donor_menu(DNI); // this method is called from the register
//					// you can only insert info or see your data
//
//					break;
//	
//				case 5:
//					// See our web page
//					
//				
//					break;
//					
//				case 6:
//					// Import an xml
//					
//				
//					break;
//					
//				case 7:
//					// Export an xml
//				
//					break;
//
//				default:
//					System.out.println("The selected option is not correct.");
//					break;
//				}
//
//				break; // to exit the loop
//			}
//
//			// if we reach this point, it is because the user wants to exit the program
//
//			// Close the connection with the database
//			jdbcManager.disconnect();
//			System.exit(0);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
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
				System.out.println("0) Exit");

				option = Integer.parseInt(reader.readLine());
				switch (option) {

				case 1:
					System.out.println("ADD ORGANS");
					// al insertar los organs available es by default false
					// addOrgans(DNI);

					break;

				case 2:
					System.out.println("SEE MY DATA");
					Donor donor = donorManager.getDonor(DNI);
					System.out.println(donor);
					break;

				case 3:
					System.out.println("DELETE MYSELF");

					break;

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

		LocalDate dob = Utilities.readDateFromKeyboard("Introduce your Date of birth (yyyy-MM-dd) :");

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

	public static void main(String[] ars) {
		JDBCManager jdbcManager = new JDBCManager();
		donorManager = new JDBCDonorManager(jdbcManager);
		antigenManager = new JDBCAntigenManager(jdbcManager);
		antibodyManager = new JDBCAntibodyManager(jdbcManager);
		locationManager = new JDBCLocationManager(jdbcManager);
		doctorManager = new JDBCDoctorManager(jdbcManager);
		receptorManager = new JDBCReceptorManager(jdbcManager);
		organManager = new JDBCOrganManager(jdbcManager);
		// userManager = new JPAUserManager();

		try {
			
			
			
			//Donor don = donorManager.getDonor(5124);
		    //Receptor rec = receptorManager.getReceptor(5);
			
			//Organ o = organManager.getOrgan(1);
			
			//List <Organ> organs = new LinkedList();
			//organs.add(o);
			
			
			//don.setOrgans(organs);
			
			//System.out.println(don);
			System.out.println("hello");
			
			//Receptor new_receptor = receptorManager.matchWithReceptor(don);
			//System.out.println(new_receptor);
			
			//Donor new_donor = donorManager.matchWithDonor(rec);
			
			//System.out.println("\n NEW DONOR: \n" + new_donor);
			
			//userManager.deleteUserDonor(621);

			// Donor new_donor = donorManager.matchWithDonor(rec);

			// System.out.println("\n NEW DONOR: \n" + new_donor);

			// userManager.deleteUserDonor(621);

			// Donor d1 = donorManager.getDonor(5124);
			// System.out.println("GOOD ONE" + d1);

			// Donor d = donorManager.getDonor(621);
			// System.out.println("BAD ONE" + d);

		} catch (Exception e) {
			e.printStackTrace();
		}

		jdbcManager.disconnect();
		// userManager.disconnect();
		System.exit(0);
	}

}
