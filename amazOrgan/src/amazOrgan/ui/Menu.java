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
	// TODO
	// private static ....Managers
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
				//TODO change password
				System.out.println("0) Exit");
				option = Integer.parseInt(reader.readLine());

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
					// getDoctor to show the info
					// ask for changes
					// create the new doctor by calling the constructor
					// doctorManager.changeMyData(doctor);
					// hecho
					break;

				case 3:
					// call donor menu
					doc_donor_menu(medical_id);
					break;

				case 4:
					// call receptor menu
					doc_receptor_menu(medical_id);
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

	// TODO tiene que recibir el medical_id???????????
	public static void doc_donor_menu(int medical_id) {

		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Add donor");
				System.out.println("2) Show all available donors");
				System.out.println("3) Update existing donor");
				System.out.println("4) Delete donor");//TODO eliminar esta opcion
				System.out.println("5) Get donor");	
				System.out.println("0) Back");
				option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					// Register donor 
					System.out.println("ADD DONOR");// ONLY DEAD DONORS
					
					// steps:
					// Hay que llamar a addDonor con toda la info
					// dob, blood type, alive
					//llamar a los add:
					// Antigen, Antibody, Location y Organs (dentro de un for), Doctor in charge NO
					// porque es el mismo --> hacer un get doctor con el id //
					// PREGUNTA DE PRATS de donde cojo el medical id si no lo tengo pasado por nada
					// introduce the organs in a list
					
										
					// call the constructor

					// 3) call match function

					break;

				case 2:
					// Show donors
					System.out.println("SHOW ALL AVAILABLE DONORS");
					// list all the donors (JDBCDonorManager)
					// solo de los donors que son alive y whose organs are available
					// cosas que queremos del donor: dni, blood type y de la lista de organos
					// donados solo el type of organ
					// desde este metodo habria que llamar a los constructores correspondientes pero
					// pasandoles solo pocas cosas
					// vamos a tener dos queries: una que devuleve los dnis de los donors que estan
					// dead y sus organs available y otra que devuelve el nombre de los organs
					//create a method that only reads from the list the dni, bloodType and organs

					break;

				case 3:
					// Update alive
					Donor d = null;
					System.out.println("UPDATE EXISTING DONOR");
					System.out.println("Insert DNI:");
					Integer donoDNI = Integer.parseInt(reader.readLine());
					d = donorManager.getDonor(donoDNI);
					while(d=null) {
						System.out.println("DNI incorrect");
						System.out.println("Insert DNI:");
						Integer donoDNI = Integer.				}
					System.out.println(d);
					donorManager.updateDonor(d);
					
					// 1) ask for: DNI // DONE
					// 2) select del donor con el dni: (getDonor)// DONE
					// si el donor estaba en la database:
					// llamar a updateDonor (con la info que se ha leido + la que falta por pedir)
					// call the constructor
					//
					// para pasar de alive = true a alive = false
					// lo que hace que tambien se pase de available = false a available = true

					// call match function

					break;

				case 4:
					// Delete donor
					System.out.println("DELETE DONOR");
					//this method is called when a dono has died but is unavailable to donate.
					//when the donor is deleted from the databse, we also have to delete him as a user
					
					//TODO? first show all the donors
					System.out.println("INSERT DNI");
					Integer dono_DNI = Integer.parseInt(reader.readLine());
					donorManager.deleteDonor(dono_DNI);
					// hay que tener en cuenta on cascade 
					// ES DECIR TENEMOS QUE BORRAR TAMBIÉN EN LA TABLA ORGAN.
					// deleteDonor();
					
					try {
						//deleteUser()
					}catch(Exception e) {
						//the donor was not registered as a user
					}
					break;

				case 5:
					// Get donor
					System.out.println("GET DONOR");// BY DNI
					System.out.println("INSERT DNI");
					Integer donorDNI = Integer.parseInt(reader.readLine());
					donorManager.getDonor(donorDNI);
					// getDonor();
					// DONE

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

	// TODO tiene que recibir el medical_id???????????
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

				option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					// + // Register receptor
					// we are going to register an entire receptor
					// all the atribites that te receptor has
					// except for the doctor in charge
					// since it is a may to many relationship
					// we are then asking
					System.out.println("REGISTER RECEPTOR");
					System.out.println("Insert the next value: " + "D " + " ");
					// when we register a patient, we have to
					// call the methods add
					// and all the objects to addthem individually un the database
					// call match function
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
						String bt = askBT();
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
					Receptor r = new Receptor();

					System.out.println("UPDATE DATA");
					System.out.println("INSERT DNI");
					Integer receptor_DNI = Integer.parseInt(reader.readLine());
					r = receptorManager.getReceptor(receptor_DNI);
					System.out.println(r);

					// TENDRÍA QUE HACER UN SWITCH CON LAS OPCIONES QUE TENDRÍA QUE HACER??
					// NO LO CREO, PERO SINO COMO CAMBIO SOLO LAS QUE QUIERO
					// updateReceptor();
					// we can change alive, urgency and status
					// we call match function when changing urgency and status(only when waiting)
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

	// TODO: menu del donor pero desde el donor pasandole su DNI
	public static void donor_menu(int DNI) {
		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Add organs");
				System.out.println("2) See my data");
				//TODO change password
				// TODO delete donor
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
		System.out.println("Insert your Medical Id:");
		Integer id = Integer.parseInt(reader.readLine());
		System.out.println("Insert your password:");
		String password = reader.readLine();

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
			System.out.println("Medical Id:");
			id = Integer.parseInt(reader.readLine());
			System.out.println("Insert your password:");
			password = reader.readLine();
			u = userManager.checkPassword(id, password);
			
			if (u != null && u.getRole().getName().equals("doctor")) {
				System.out.println("Login succesful");
				doctor_menu(u.getId());
			}
			return;

		}

	}

	//this method works
	public static void loginDonor() throws Exception {
		System.out.println("Insert your DNI:");
		Integer id = Integer.parseInt(reader.readLine());
		System.out.println("Insert your password:");
		String password = reader.readLine();

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
			System.out.println("DNI:");
			id = Integer.parseInt(reader.readLine());
			System.out.println("Insert your password:");
			password = reader.readLine();
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
		System.out.println("Insert your Medical Id:");
		Integer medical_id = Integer.parseInt(reader.readLine());
		// COMPROBAR QUE NO EXISTE
		Doctor doc = doctorManager.getDoctor(medical_id);
		if (doc != null) {
			// Si existe, hacer return
			System.out.println("This medical Id is alredy registered in the database. Try to login as a doctor.");
			return;
		}

		System.out.println("Insert your phone number:");
		Integer phone_number = Integer.parseInt(reader.readLine());
		System.out.println("Insert your name:");
		String name = reader.readLine();
		List<Donor> donors = null;
		List<Receptor> receptors = null;

		// we have to create a Doctor but also a User
		Doctor d = new Doctor(medical_id, phone_number, name, receptors, donors);

		System.out.println("Insert your password:");
		String password = reader.readLine();

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

	//this method works
	public static void registerDonor() throws Exception {

		// ask for all the information
		// can only insert insert his dni, dob and organs (type of organ)
		// alive by default is true
		// doctor by default would be the unassigned

		System.out.println("Introduce your DNI:");
		Integer dni = Integer.parseInt(reader.readLine());
		// CHECK IF THE USER EXISITS
				Donor don = donorManager.getDonor(dni);
				if (don != null) {
					//if it exisits, return
					System.out.println("This DNI is alredy registered in the database. Try to login as a donor.");
					return;
				}
		System.out.println("Introduce your Date of birth (yyyy-MM-dd) :");
		
		// TODO ask for date
		String withoutTime = reader.readLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(withoutTime, formatter);

		// we have to create a Donor but also a User
		Donor d = new Donor(dni, dob, true);

		System.out.println("Insert your password:");
		String password = reader.readLine();

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

	private static String askBT() {
		try {
			Integer option;
			while (true) {
				String a = "A+";
				String b = "B+";
				String o = "O+";
				String ab = "AB+";
				String x = "A-";
				String y = "B-";
				String z = "O-";
				String xy = "AB-";
				System.out.println("INSERT THE NUMBER OF THE BLOODTYPE YOU WANT");
				System.out.println("A+    1");
				System.out.println("B+    2");
				System.out.println("AB+   3");
				System.out.println("O+    4");
				System.out.println("A-    5");
				System.out.println("B-    6");
				System.out.println("AB-   7");
				System.out.println("O-    8");

				option = Integer.parseInt(reader.readLine());
				switch (option) {

				case 1:
					return a;

				case 2:
					return b;

				case 3:
					return o;

				case 4:
					return ab;

				case 5:
					return x;
				case 6:
					return y;

				case 7:
					return z;

				case 8:
					return xy;

				default:
					System.out.println("The option is not correct");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// IF ALGUIEN TIENE DUDAS SOBRE JPA (USER-ROLE): VER CLASE 27/04
	// if salen excepciones mirar la first class que hayamos creado nosotros

	public static void main(String[] ars) {
		JDBCManager jdbcManager = new JDBCManager();
		donorManager = new JDBCDonorManager(jdbcManager);
		antigenManager = new JDBCAntigenManager(jdbcManager);
		antibodyManager = new JDBCAntibodyManager(jdbcManager);
		locationManager = new JDBCLocationManager(jdbcManager);
		doctorManager = new JDBCDoctorManager(jdbcManager);
		userManager = new JPAUserManager();

		try {
			// registerDoctor();
			// loginDoctor();
			// registerDonor();
			//loginDonor();
			
			//userManager.updatePassword(397, "ana");
			//loginDoctor();
			
			userManager.deleteUserDonor(621);

		} catch (Exception e) {
			e.printStackTrace();
		}

		jdbcManager.disconnect();
		userManager.disconnect();
		System.exit(0);
	}

}
