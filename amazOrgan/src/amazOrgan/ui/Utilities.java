package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Location;

public class Utilities {

	public static Boolean readBooleanFromKeyboard(String question) {
		System.out.println(question + " (yes or no)");

		Boolean b;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				String r = null;
				r = bf.readLine();
				if (r.equals("yes")) {
					return true;
				} else if (r.equals("no")) {
					return false;
				} else {
					System.out.println("Error, introduce 'yes' or 'no'.");
				}

			} catch (IOException ioe) {
				System.out.println("Error");
			}
		}

	}

	public static Antigen readAntigenFromKeyboard() {

		Boolean a = readBooleanFromKeyboard("Is the antigen A present?");
		Boolean b = readBooleanFromKeyboard("Is the antigen B present?");
		Boolean c = readBooleanFromKeyboard("Is the antigen C present?");
		Boolean dp = readBooleanFromKeyboard("Is the antigen DP present?");
		Boolean dq = readBooleanFromKeyboard("Is the antigen DQ present?");
		Boolean dr = readBooleanFromKeyboard("Is the antigen DR present?");

		Antigen antigen = new Antigen(a, b, c, dp, dq, dr);
		return antigen;
	}

	//TODO test method
	public static Antibody readAntibodyFromKeyboard() {

		Boolean class_I = readBooleanFromKeyboard("Is the antibody class_I present?");
		Boolean class_II = readBooleanFromKeyboard("Is the antibody class_II present?");

		Antibody antibody = new Antibody(class_I, class_II);
		return antibody;
	}

	//TODO test method
	public static Location readLocationFromKeyboard() {

		Float latitude = readFloatFromKeyboard("Introduce the latitude.");
		Float longitude = readFloatFromKeyboard("Introduce the longitude.");

		Location location = new Location(latitude, longitude);
		return location;
	}

	// LEER UN ENTERO POR CONSOLA
	public static int readIntFromKeyboard(String question) {
		System.out.println(question);
		int numero;

		while (true) {
			try {
				String stringLeido = null;
				BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
				stringLeido = consola.readLine();
				numero = Integer.parseInt(stringLeido);
				return numero;

			} catch (IOException ioe) {
				System.out.println("Error. Vuelva a introducir un número");
			} catch (NumberFormatException nfe) {
				System.out.println("No ha introducido un número. Introduzca un número entero.");
			}
		}
	}

	// LEER UN STRING POR CONSOLA
	public static String readStringFromKeyboard(String question) {
		System.out.println(question);

		while (true) {
			try {
				String stringLeido = null;
				BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
				stringLeido = consola.readLine();

				return stringLeido;

			} catch (IOException ioe) {
				System.out.println("Error. Introduzca una nueva cadena de texto.");
			}
		}
	}

	// LEER ENTERO POR CONSOLA DENTRO DE UN RANGO
	public static int readIntFromKeyboardInRange(String question, int begin, int end) {
		System.out.println(question);
		int numero;
		if (begin > end) {
			int temp = begin;
			begin = end;
			end = begin;
		}
		while (true) {
			try {
				String stringLeido = null;
				BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
				stringLeido = consola.readLine();
				numero = Integer.parseInt(stringLeido);
				if (numero >= begin && numero <= end) {
					return numero;
				} else {
					System.out.println("El número debe estar entre " + begin + " y " + end + ".");
				}
			} catch (IOException ioe) {
				System.out.println("Error. Vuelva a introducir un número.");
			} catch (NumberFormatException nfe) {
				System.out.println("No ha introducido un número. Introduzca un número entero.");
			}
		}
	}

	// LEER UN FLOAT POR CONSOLA
	public static float readFloatFromKeyboard(String question) {
		System.out.println(question);
		float numero;

		while (true) {
			try {
				String stringLeido = null;
				BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
				stringLeido = consola.readLine();
				numero = Float.parseFloat(stringLeido);
				return numero;

			} catch (IOException ioe) {
				System.out.println("Ha habido un error. Vuelva a introducir un número");
			} catch (NumberFormatException nfe) {
				System.out.println("No ha introducido un número. Introduzca un número entero.");
			}
		}
	}

	public static void main(String[] ars) {
		Antigen a = readAntigenFromKeyboard();
	}
}
