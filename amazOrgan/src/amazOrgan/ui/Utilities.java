package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Location;
import amazOrgan.pojos.Receptor;
import amazOrgan.pojos.Request;

public class Utilities {
	
public static Receptor addreceptormenu() {
		
		
		Receptor receptor = null;
	
		System.out.println("Insert the next value:");
		Integer dni = readIntFromKeyboard("DNI");
		String date = readStringFromKeyboard("DOB");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(date, format);
//***********		//Date dob= Date.parsedate(reader.readLine());
		String status = readStringFromKeyboard("Status");
		String blood_type = readStringFromKeyboard("BLOOD TYPE");
		Boolean alive = readBooleanFromKeyboard("ALIVE (TRUE FALSE)");
		Integer urgency = readIntFromKeyboard("URGENCY");
		Antigen antigen =readAntigenFromKeyboard();
		Antibody antibody = readAntibodyFromKeyboard();
		Location location = readLocationFromKeyboard();
		//Request request = readRequestFromKeyboard("REQUEST");
		
		//falta readRequestFromKeyBoard
		
		receptor = new Receptor (dni, dob, status, blood_type,alive,urgency, antigen, antibody,location,request);

		return receptor;
//**********		// call match function
	}
	
	public static Receptor updatereceptormenu(Receptor r) {
		
		Receptor newreceptor= r;
		
		System.out.println("YOU CAN ONLY CHANGE ALIVE, URGENCY, STATUS");

		String newstatus = 	readStringFromKeyboard("STATUS");	
		Boolean newalive = readBooleanFromKeyboard("ALIVE");
		Integer newurgency = readIntFromKeyboard("URGENCY");
		//IF WE PUT A WORD IN STATUS THAT DOES NOT BELONG????
		
		newreceptor(r.getDni(),r.getDob(),newstatus,r.getBlood_type(),newalive, newurgency,
				r.getAntigen(),r.getAntibody(),r.getLocation(),r.getRequest());
		
		
		
		
		return newreceptor;
	}
	
	static String askBT() {
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

				option = readIntFromKeyboard("INSERT THE NUMBER");
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

		} return null;
	}

	
	

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

	public static int readIntFromKeyboard(String question) {
		System.out.println(question);
		int num;

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Integer.parseInt(s);
				return num;

			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter an integer number.");
			}
		}
	}


	public static String readStringFromKeyboard(String question) {
		System.out.println(question);

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();

				return s;

			} catch (IOException ioe) {
				System.out.println("Error. Enter a new text string.");
			}
		}
	}


	public static int readIntFromKeyboardInRange(String question, int begin, int end) {
		System.out.println(question);
		int num;
		if (begin > end) {
			int temp = begin;
			begin = end;
			end = begin;
		}
		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Integer.parseInt(s);
				if (num >= begin && num <= end) {
					return num;
				} else {
					System.out.println("The number must be between " + begin + " and " + end + ".");
				}
			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter an integer number.");
			}
		}
	}


	public static float readFloatFromKeyboard(String question) {
		System.out.println(question);
		float num;

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Float.parseFloat(s);
				return num;

			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter a number.");
			}
		}
	}

	public static void main(String[] ars) {
		Antigen a = readAntigenFromKeyboard();
	}
}
