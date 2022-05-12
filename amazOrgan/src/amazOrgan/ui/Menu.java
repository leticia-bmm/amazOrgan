package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import amazOrgan.ifaces.LocationManager;
import amazOrgan.jdbc.JDBCLocationManager;
import amazOrgan.pojos.Location;

import amazOrgan.jdbc.JDBCManager;

public class Menu {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	// TODO
	// private static ....Managers
	private static LocationManager locationManager;

	public static void first_menu() {

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

					break;

				case 2:
					// Change my data
					System.out.println("CHANGE MY DATA");

					break;

				case 3:
					// call donor menu
					donor_menu();
					break;

				case 4:
					// call receptor menu
					receptor_menu();
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

	public static void donor_menu() {

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

					break;

				case 2:
					// Show donors
					System.out.println("SHOW DONORS");

					break;

				case 3:
					// Update alive
					System.out.println("UPDATE ALIVE");

					break;

				case 4:
					// Delete donor
					System.out.println("DELETE DONOR");

					break;

				case 5:
					// Get donor
					System.out.println("GET DONOR");

					break;

				case 0:
					// Back
					return; // we exit the method to return to first_menu

				default:
					System.out.println("The selected option is not correct.");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void receptor_menu() {

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
					System.out.println("REGISTER RECEPTOR");

					break;

				case 2:
					// Show receptors
					System.out.println("SHOW RECEPTORS");

					break;

				case 3:
					// Search receptor
					System.out.println("SEARCH RECEPTOR");

					break;

				case 4:
					// Update data
					System.out.println("UPDATE DATA");

					break;

				case 0:
					// Back
					return; // we exit the method to return to first_menu

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
		System.out.println("hola");

		// TODO
		// Initialize database for JDBC
		// -----------------------------
		JDBCManager jdbcManager = new JDBCManager();
		locationManager = new JDBCLocationManager(jdbcManager);

		// = new ...Manager()

		// TODO
		// Initialize database for JPA
		// ----------------------------
		// userManager = new JPAUserManager();

		// Menu loop
		try {
			int option;
			while (true) {
				System.out.println("Please, choose an option: ");
				System.out.println("1) Login as a Doctor");
				System.out.println("2) Register as a Doctor");

				option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					// Login as a Doctor
					System.out.println("LOGIN AS A DOCTOR");
					System.out.println("Add a location");
					Float f = 1.09f;
					System.out.println(f);
					Float f2 = 1.34f;
					Location l = new Location(f, f2);
					locationManager.addLocation(l);

					break;

				case 2:
					// Register as a Doctor
					System.out.println("REGISTER AS A DOCTOR");

					break;

				default:
					System.out.println("The selected option is not correct.");
					break;
				}

				break; // to exit the loop
			}

			// Now, the doctor is able to work with the database
			// 1) call first menu
			// 2) call second menu (donor or receptor)

			first_menu();
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

	}
	
	

}
