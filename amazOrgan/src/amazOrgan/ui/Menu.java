package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Menu {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static int first_menu() {

		try {
			while (true) {

				System.out.println("Please, choose an option:");
				System.out.println("1) See my patients");
				System.out.println("2) Change my data");
				System.out.println("3) Work with donors");
				System.out.println("4) Work with receptors");
				System.out.println("0) Exit");
				int option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					return 1;
				case 2:
					return 2;
				case 3: // call donor menu
					int a = donor_menu();
					if (a == 0) {
						break;
					} else {
						return a;
					}

				case 4: // call receptor menu
					int b = receptor_menu();
					if (b == 0) {
						break;
					} else {
						return b;
					}
				case 0:
					return 0;
				default:
					System.out.println("The selected option is not correct.");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int donor_menu() {

		try {
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Register donor");
				System.out.println("2) Show donors");
				System.out.println("3) Update alive");
				System.out.println("4) Delete donor");
				System.out.println("5) Get donor");
				System.out.println("0) Back");
				int option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					return 3;
				case 2:
					return 4;
				case 3:
					return 5;
				case 4:
					return 6;
				case 5:
					return 7;
				case 0:
					return 0;
				default:
					System.out.println("The selected option is not correct.");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int receptor_menu() {

		try {
			while (true) {
				System.out.println("Please, choose an option:");
				System.out.println("1) Register receptor");
				System.out.println("2) Show receptors");
				System.out.println("3) Search receptor");
				System.out.println("4) Update data");
				System.out.println("0) Back");

				int option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					return 8;
				case 2:
					return 9;
				case 3:
					return 10;
				case 4:
					return 11;
				case 0:
					return 0;
				default:
					System.out.println("The selected option is not correct.");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void main(String[] ars) {

		System.out.println("Welcome to amazOrgan!");
		// Initialize database for JDBC

		// Initialize database for JPA

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
					break;
				case 2:
					// Register as a Doctor
					break;

				default:
					System.out.println("The selected option is not correct.");
					break;
				}

				break; // to exit the loop
			}

			// The doctor is able to work with the database
			// call first menu
			// call second menu (donor or receptor)

			while (true) {
				option = first_menu();
				switch (option) {

				// Doctor
				case 1:
					// See my patients
					System.out.println("See my patients");
					break;
				case 2:
					// Change my data
					System.out.println("Change my data");
					break;

				// Donor
				case 3:
					// Register donor
					System.out.println("Register donor");
					break;
				case 4:
					// Show donors
					System.out.println("Show donors");
					break;
				case 5:
					// Update alive
					System.out.println("See my patients");
					break;
				case 6:
					// Delete donor
					System.out.println("Update alive");
					break;
				case 7:
					// Get donor
					System.out.println("Get donor");
					break;

				// Receptor
				case 8:
					// Register receptor
					System.out.println("Register receptor");
					break;
				case 9:
					// Show receptors
					System.out.println("Show receptors");
					break;
				case 10:
					// Search receptors
					System.out.println("Search receptors");
					break;
				case 11:
					// Update data
					System.out.println("Update data");
					break;

				case 0:
					// Close the connection with the database

					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
