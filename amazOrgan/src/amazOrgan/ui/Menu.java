package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
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
<<<<<<< HEAD
=======
import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Doctor;
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Location;
<<<<<<< HEAD
import amazOrgan.pojos.Receptor;
=======
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Type_organ;
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
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
				System.out.println("0) Exit");
				option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					// See my patients
					System.out.println("SEE MY PATIENTS");
					System.out.println(doctorManager.listMyReceptors(medical_id));
					System.out.println(doctorManager.listMyDonors(medical_id));
					// list donors from JDBCDonorManager
					// list receptors from JDBCReceptorManager (join with examines)

					break;

				case 2:
					// Change my data
					System.out.println("CHANGE MY DATA");
					// doctorManager.changeMyData(null);
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
				System.out.println("1) Register donor");
				System.out.println("2) Show all available donors");
				System.out.println("3) Update existing donor");
				System.out.println("4) Delete donor");
				System.out.println("5) Get donor");
				System.out.println("0) Back");
				option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
	//+				// Register donor 
					System.out.println("REGISTER DONOR");// ONLY DEAD DONORS
					
					// steps:
					// Hay que llamar a addDonor con toda la info
					// dob, blood type, alive
<<<<<<< HEAD
					//llamar a los add:
=======
					// llamar a los add:
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
					// Antigen, Antibody, Location y Organs (dentro de un for), Doctor in charge NO
					// porque es el mismo --> hacer un get doctor con el id //
					// PREGUNTA DE PRATS de donde cojo el medical id si no lo tengo pasado por nada
					// introduce the organs in a list
					
					Antigen antigen;
					Antibody antibody;
					Location location;
					Doctor doctor_charge;
					List<Organ> organs;
					
					Donor d = new Donor(...);
					donorManager.addDonor(d);

					
					// call the constructor

					// 3) call match function

					break;

				case 2:
	//+				// Show donors
					System.out.println("SHOW ALL AVAILABLE DONORS");
					// list all the donors (JDBCDonorManager)
					// solo de los donors que son alive y whose organs are available
					// cosas que queremos del donor: dni, blood type y de la lista de organos
					// donados solo el type of organ
					// desde este metodo habria que llamar a los constructores correspondientes pero
					// pasandoles solo pocas cosas
					// vamos a tener dos queries: una que devuleve los dnis de los donors que estan
					// alive y sus organs available y otra que devuelve el nombre de los organs

					break;

				case 3:
	//+				// Update alive
					Donor d = null;
					System.out.println("UPDATE EXISTING DONOR");
					System.out.println("INSERT DNI:");
					Integer donoDNI = Integer.parseInt(reader.readLine());
					d= donorManager.getDonor(donoDNI);
					System.out.println(d);
					
					
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
					System.out.println("INSERT DNI");
					Integer dono_DNI = Integer.parseInt(reader.readLine());
					donorManager.deleteDonor(dono_DNI);
					// hay que tener en cuenta on cascade 
					// ES DECIR TENEMOS QUE BORRAR TAMBIÉN EN LA TABLA ORGAN.
					// deleteDonor();
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
	//+				// Register receptor
					// we are going to register an entire receptor
					// all the atribites that te recepotr has
					// except for the doctor in charge
					// since it is a may to many relationship
					// we are then asking
					System.out.println("REGISTER RECEPTOR");
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
					switch(choice) {
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
	//+ 			// Update data
					Receptor r = new Receptor();
					
					System.out.println("UPDATE DATA");
					System.out.println("INSERT DNI");
					Integer receptor_DNI = Integer.parseInt(reader.readLine());
					r = receptorManager.getReceptor(receptor_DNI);
					System.out.println(r);
					
					// TENDRÍA QUE HACER UN SWITCH  CON LAS OPCIONES QUE TENDRÍA QUE HACER??
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

	public static void main(String[] ars) {

		System.out.println("Welcome to amazOrgan!");

		// TODO
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

		// = new ...Manager()

		// TODO
		// Initialize database for JPA
		// ----------------------------
		userManager = new JPAUserManager();

		// Menu loop
		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option: ");
				System.out.println("1) Login as a Doctor");
				System.out.println("2) Register as a Doctor");
				System.out.println("3) Login as a Donor");
				System.out.println("4) Register as a Donor");

				option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					// Login as a Doctor
					System.out.println("LOGIN AS A DOCTOR");
					// login_doctor() ask here for the id and the password
					int medical_id = 1;
					doctor_menu(medical_id); // this method is called from the login

					break;

				case 2:
					// Register as a Doctor
					System.out.println("REGISTER AS A DOCTOR");
					// register_doctor() ask here for the id and the password
					int id = 1;
					doctor_menu(id); // this method is called from the register
					break;

				case 3:
					// Login as a Donor
					System.out.println("LOGIN AS A DONOR");
					// login_donor() ask here for the id and the password
					int dni = 1;
					donor_menu(dni); // this method is called from the login
					// you can only insert info or see your data

					break;

				case 4:
					// Register as a Donor
					System.out.println("Register AS A DONOR");
					// register_donor() ask here for the id and the password
					int DNI = 3;
					donor_menu(DNI); // this method is called from the register
					// you can only insert info or see your data

					break;

				default:
					System.out.println("The selected option is not correct.");
					break;
				}

				break; // to exit the loop
			}

			// if we reach this point, it is because the user wants to exit the program

			// Close the connection with the database
			jdbcManager.disconnect();
			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * public static void main(String[] ars) { JDBCManager jdbcManager = new
		 * JDBCManager(); System.out.println("Welcome to amazOrgan!");
		 * 
		 * 
		 * jdbcManager.disconnect(); System.exit(0); }
		 */

	}

	// TODO: menu del donor pero desde el donor pasandole su DNI
	public static void donor_menu(int DNI) {
		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Insert data");
				System.out.println("2) See my data");
				System.out.println("0) Exit");

				option = Integer.parseInt(reader.readLine());
				switch (option) {
				
				case 1:
<<<<<<< HEAD
					System.out.println("INSERT DATA");
					// Llamar al método add donor
					// al insertar alive es by default true
					// en los organs available es by default false
=======
					System.out.println("INSERT DATA");					
					insertMyselfAsDonor(DNI);					
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
					break;

				case 2:
					System.out.println("SEE MY DATA");
					Donor donor = donorManager.getDonor(DNI);
					System.out.println(donor);
					break;

				case 0:
					//Exit
					System.out.println("Thanks for choosing amazOrgan");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO
	// loginDoctor

	public static void loginDoctor() throws Exception {
		// User needs to provide an id and a password

		User u = userManager.checkPassword(id, password);
		// Enter the doctor menu if the combination was valid
		if (u != null) {
			// doctor menu with that user
		}
		// if not, ask again

		// llamar desde aquí al doctor_menu

	}

	// AL REGISTRAR un doctor:
	// crear un doctor
	// crear un user y un role = "doctor" (con user.setRole)
	// el digest se crea al registrar

	// IF ALGUIEN TIENE DUDAS SOBRE JPA (USER-ROLE): VER CLASE 27/04

	
	public static void insertMyselfAsDonor(int DNI) {
		
		// can only insert insert his dni, dob and organs (type of organ)
		// al insertar alive es by default true
		// en los organs available es by default false
		
		System.out.println("Introduce your DNI:");
		Integer dni = Integer.parseInt(reader.readLine());
		System.out.println("Introduce your Date of birth:");
		Date dob;//??????????????????????
		
		System.out.println("How many organs do you want to donate?");
		int number = Integer.parseInt(reader.readLine());
		List<Organ> organs = new LinkedList<Organ>();
		Organ o;
		Type_organ t;
		String name;
		
		for (int i = 0; i<number; i++) {
			System.out.println("Introduce the organ:");
			name = reader.readLine();
			t = new Type_organ(name);
			o = new Organ(t, false);
			organs.add(o);
		}
		
		Donor d = new Donor(dni, dob, true, null, null, null, null, null, organs);
		donorManager.addDonor(d);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
