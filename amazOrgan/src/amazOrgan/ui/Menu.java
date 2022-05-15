package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
import amazOrgan.pojos.Location;

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

	
	
	//TODO: receives the medical_id of the doctor
	public static void doctor_menu() {

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
					// list donors from JDBCDoctorManager
					// list receptors from JDBCDoctorManager (join with examines)

					break;

				case 2:
					// Change my data
					System.out.println("CHANGE MY DATA");
					doctorManager.changeMyData(null);
					break;

				case 3:
					// call donor menu
					doc_donor_menu();
					break;

				case 4:
					// call receptor menu
					doc_receptor_menu();
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

	public static void doc_donor_menu() {

		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Register donor");
				System.out.println("2) Show donors");
				System.out.println("3) Update alive");
				System.out.println("4) Delete donor");
				System.out.println("5) Get donor");
				System.out.println("0) Back");
				option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					// Register donor
					System.out.println("REGISTER DONOR");
					// steps:
					// 1) ask for: DNI, dob, blood type, alive
					// 2)Antigen, Antibody, Location, Doctor in charge
					// 3)introduce the organs in a list
					// call the constructor
					// call match function

					break;

				case 2:
					// Show donors
					System.out.println("SHOW DONORS");
					// list all the donors (JDBCDonorManager)

					break;

				case 3:
					// Update alive
					System.out.println("UPDATE ALIVE");
					updateAlive();
					// call match function

					break;

				case 4:
					// Delete donor
					System.out.println("DELETE DONOR");
					deleteDonor();
					break;

				case 5:
					// Get donor
					System.out.println("GET DONOR");
					getDonor();

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

	public static void doc_receptor_menu() {

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
					// Register receptor
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
					System.out.println("SHOW RECEPTORS");
					// list receptors (JDBCReceptorManager)
					// another menu for: by bloodType, by Urgency

					break;

				case 3:
					// Search receptor
					System.out.println("SEARCH RECEPTOR");
					getReceptor();
					break;

				case 4:
					// Update data
					System.out.println("UPDATE DATA");
					updateReceptor();
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
					//login_doctor()   ask here for the id and the password				
					//doctor_menu(medical_id)

					break;

				case 2:
					// Register as a Doctor
					System.out.println("REGISTER AS A DOCTOR");
					//register_doctor()   ask here for the id and the password				
					//doctor_menu(medical_id)
					break;
					
				case 3:
					// Login as a Donor
					System.out.println("LOGIN AS A DONOR");
					//login_donor()   ask here for the id and the password				
					//donor_menu(dni) you can only insert info or see your data

					break;

				case 4:
					// Register as a Donor
					System.out.println("Register AS A DONOR");
					//register_donor()   ask here for the id and the password				
					//donor_menu(dni) you can only insert info or see your data

					break;

				default:
					System.out.println("The selected option is not correct.");
					break;
				}

				break; // to exit the loop
			}

			
			// if we reach this point, it is because the user wants to exit the program

			// TODO
			// Close the connection with the database
			// ---------------------------------------
			// disconnect()

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
	
	
	//TODO: menu del donor pero desde el donor pasandole su DNI
	
	//TODO
	//loginDoctor
	
	public static void loginDoctor() throws Exception {
		//User needs to provide an id and a password
		
		User u = userManager.checkPassword(id, password);
		//Enter the doctor menu if the combination was valid
		if (u != null) {
			//doctor menu with that user
		}
		//if not, ask again
		
	}
	
	//AL REGISTRAR un doctor:
	//crear un doctor
	//crear un user y un role = "doctor" (con user.setRole)
	//el digest se crea al registrar
	
	
	//IF ALGUIEN TIENE DUDAS SOBRE JPA (USER-ROLE): VER CLASE 27/04

}
