package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Location;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Receptor;
import amazOrgan.pojos.Request;
import amazOrgan.pojos.Type_organ;

public class Utilities {

	public static Receptor addreceptormenu() {

		Receptor receptor = null;

		Integer dni = readPositiveIntFromKeyboard("Insert the dni.");
		LocalDate dob = readDateFromKeyboard();
		String status = askStatus();
		String blood_type = askBloodType();
		Boolean alive = readBooleanFromKeyboard("Is the receptor alive?");
		Integer urgency = readIntFromKeyboardInRange("What is the level of urgency?", 1, 5);
		Antigen antigen = readAntigenFromKeyboard();
		Antibody antibody = readAntibodyFromKeyboard();
		Location location = readLocationFromKeyboard();
		Request request = readRequestFromKeyboard("Introduce the organ needed.");

		receptor = new Receptor(dni, dob, status, blood_type, alive, urgency, antigen, antibody, location, request);

		return receptor;

//**********		//TODO call match function
	}
//**********		// call match function

	public static Receptor updateReceptorMenu(Receptor r) {
		


	System.out.println("You will only be able to change the status, alive and the urgency: ");
	String newstatus = askStatus();	
	Boolean newalive = readBooleanFromKeyboard("\nIs the receptor alive? ");
	Integer newurgency = readIntFromKeyboardInRange("\nWhat is the new level of urgency? ", 1, 5);

	r.setStatus(newstatus);
	r.setAlive(newalive);
	r.setUrgency(newurgency);

	
	return r;
}
	
	static String askBloodType() {
		try {
			Integer option;
			while (true) {
				String aPositive = "A+";
				String bPositive = "B+";
				String oPositive = "O+";
				String abPositive = "AB+";
				String aNegative = "A-";
				String bNegative = "B-";
				String oNegative = "O-";
				String abNegative = "AB-";
				System.out.println("\nWhat is the blood type of the patient?: ");
				System.out.println("1. A+");
				System.out.println("2. B+");
				System.out.println("3. AB+");
				System.out.println("4. O+");
				System.out.println("5. A-");
				System.out.println("6. B-");
				System.out.println("7. AB-");
				System.out.println("8. O-");

				option = readIntFromKeyboard("\nInsert the number corresponding to the blood type: ");
				
				switch (option) {

				case 1:
					return aPositive;

				case 2:
					return bPositive;

				case 3:
					return abPositive;

				case 4:
					return oPositive;

				case 5:
					return aNegative;
					
				case 6:
					return bNegative;

				case 7:
					return abNegative;

				case 8:
					return oNegative;

				default:
					System.out.println("The option is not correct");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static Request readRequestFromKeyboard(String question) {
		// TODO test method
		System.out.println(question);
		Type_organ typeOfOrgan = askTypeOfOrgan();
		Float organSize = readPositiveFloatFromKeyboard("Insert the size of the organ needed: ");
		Boolean received = false;
		Request request = new Request(typeOfOrgan, organSize, received , null);
		return request;
	}

	public static Type_organ askTypeOfOrgan() {
		try {
			Integer option;
			while (true) {
				Type_organ kidney = new Type_organ(1, "kidney", 50);
				Type_organ liver = new Type_organ(2, "liver", 30);
				Type_organ pancreas = new Type_organ(3, "pancreas", 30);
				Type_organ lungs = new Type_organ(4, "lung", 8);
				Type_organ heart = new Type_organ(5, "heart", 8);
				Type_organ bowel = new Type_organ(6, "bowel", 12);
				Type_organ boneMarrow = new Type_organ(7, "bone marrow", null);

				System.out.println("What is the type of organ? ");
				System.out.println("1. Kidney");
				System.out.println("2. Liver");
				System.out.println("3. Pancreas");
				System.out.println("4. Lungs");
				System.out.println("5. Heart");
				System.out.println("6. Bowel");
				System.out.println("7. Bone marrow");

				option = readIntFromKeyboard("\nInsert the number corresponding to the organ: ");
				switch (option) {
				case 1:

					return kidney;

				case 2:
					return liver;

				case 3:
					return pancreas;

				case 4:
					return lungs;
			
				case 5:
					return heart;

				case 6:
					return bowel;

				case 7:
					return boneMarrow;

				default:
					System.out.println("The option is not correct");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static String askStatus() {
		// WORKS
		try {
			Integer option;

			while (true) {
				String accepted = "Accepted";
				String rejected = "Rejected";
				String waiting = "Waiting";
				String operating = "Operating";
				System.out.println("In what status is the patient? ");
				System.out.println("1. The patient is waiting for an organ ");
				System.out.println("2. The patient has accepted the organ");
				System.out.println("3. The patient has rejected the organ");
				System.out.println("4. The patien is being operated");
				option = readIntFromKeyboard("Choose an option: ");
				switch (option) {

				case 1:
					return waiting;

				case 2:
					return accepted;

				case 3:
					return rejected;

				case 4:
					return operating;

				default:
					System.out.println("The option is not correct");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	// TODO test method
	public static Location readLocationFromKeyboard() {

		Float latitude = readFloatFromKeyboard("Introduce the latitude: ");
		Float longitude = readFloatFromKeyboard("Introduce the longitude: ");

		Location location = new Location(latitude, longitude);
		return location;
	}

	public static int readIntFromKeyboard(String question) {
		System.out.println(question);
		Integer num;

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

	// Read Positive Integer
	public static Integer readPositiveIntFromKeyboard(String question) {
		System.out.println(question);
		Integer num;

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Integer.parseInt(s);

				if (num > 0) {
					return num;
				} else {
					System.out.println("Enter a positive number.");
				}

			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number.");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter an integer number.");
			}
		}
	}

	// Read Integer in range
	public static Integer readIntFromKeyboardInRange(String question, int begin, int end) {
		System.out.println(question);
		Integer num;
		if (begin > end) {
			Integer temp = begin;
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

	// Read String
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

	// Read Float
	public static float readFloatFromKeyboard(String question) {
		System.out.println(question);
		Float num;

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

	// Read Boolean
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

	// Read a date
	public static LocalDate readDateFromKeyboard() {
		System.out.println("Insert the date of Birth enter a date in this format (yyyy-mm-dd)");
		String dateString = null;

		while (true) {
			try {
				dateString = readStringFromKeyboard("");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(dateString, formatter);
				return date;

			} catch (Exception e) {
				System.out.println("Error. Re-enter a date in this format (yyyy-MM-dd)");
			}
		}
	}

	// Read antigen
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

	// Read antibody
	public static Antibody readAntibodyFromKeyboard() {

		Boolean class_I = readBooleanFromKeyboard("Is the antibody class_I present?");
		Boolean class_II = readBooleanFromKeyboard("Is the antibody class_II present?");

		Antibody antibody = new Antibody(class_I, class_II);
		return antibody;
	}


	public static Float readPositiveFloatFromKeyboard(String question) {
		System.out.println(question);
		Float num;

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Float.parseFloat(s);

				if (num > 0) {
					return num;
				} else {
					System.out.println("Enter a positive number.");
				}

			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number.");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter an integer number.");
			}
		}
	}

	// Read a death donor
	// TODO method
	public static Donor readDeadDonorFromKeyboard(Integer medical_id, String question) {
		System.out.println(question);

		Donor donor = null;
		Integer dni = readPositiveIntFromKeyboard("Enter the dni: ");
		LocalDate dob = readDateFromKeyboard();
		Boolean alive = false;
		String bloodType = askBloodType();
		Antigen antigen = readAntigenFromKeyboard();
		Antibody antibody = readAntibodyFromKeyboard();
		Location location = readLocationFromKeyboard();
		Doctor doctor_charge = new Doctor(medical_id);
		// List<Organ> organs = readListOrgansFromKeyboard();
		Organ organ = null;
		List<Organ> organs = new LinkedList<>();

		donor = new Donor(dni, dob, alive, bloodType, antigen, antibody, location, doctor_charge);

		while (true) {
			Boolean cont = readBooleanFromKeyboard("Would you like to introduce an organ? ");
			if (cont) {
				Type_organ type_organ = askTypeOfOrgan();
				Float size = readPositiveFloatFromKeyboard("Insert the size of the organ: ");
				Boolean available = true;
				organ = new Organ(type_organ, size, available, donor);
				organs.add(organ);
			} else {
				break;
			}
		}
		donor.setOrgans(organs);

		donor = new Donor(dni, dob, alive, bloodType, antigen, antibody, location, doctor_charge, organs);
		return donor;
	}

	// Update a donor
	//this otption is only used when a donor was already in the database
	// TODO test method
	public static Donor readDonortoUpdate(Donor d) {

		Donor donor = null;
		Integer dni = d.getdni();
		LocalDate dob = d.getdob();
		Boolean alive = false;
		String bloodType = askBloodType();
		Antigen antigen = readAntigenFromKeyboard();
		Antibody antibody = readAntibodyFromKeyboard();
		Location location = readLocationFromKeyboard();
		Doctor doctor_charge = d.getDoctor_charge();
		List<Organ> listOrgans = d.getOrgans();

		for (Organ o : listOrgans) {
			System.out.println("Insert the info of the " + o.getType_organ().getName());
			Float size = readPositiveFloatFromKeyboard("Insert the size of the organ: ");
			o.setSize(size);
		}

		donor = new Donor(dni, dob, alive, bloodType, antigen, antibody, location, doctor_charge, listOrgans);
		return donor;
	}

	public static Donor readAliveDonor() {
		Donor donor = null;

		Integer dni = readPositiveIntFromKeyboard("Insert your DNI: ");
		LocalDate dob = readDateFromKeyboard();
		Boolean alive = true;

		donor = new Donor(dni, dob, alive);
		return donor;
	}

	public static List<Organ> readOrgansAliveDonorFromKeyboard(Donor donor) {
		List<Organ> organs = new LinkedList<>();
		Organ organ;

		while (true) {
			Boolean cont = readBooleanFromKeyboard("Would you like to introduce an organ? ");
			if (cont) {
				Type_organ type_organ = askTypeOfOrgan();
				Boolean available = false;
				organ = new Organ(type_organ, available, donor);
				organs.add(organ);
			} else {
				break;
			}
		}

		return organs;
	}

	public static void main(String[] ars) {
		System.out.println("HELLO");
		Receptor r = addreceptormenu();
		System.out.println(r);
		Type_organ to = new 
		Donor donor = new Donor(12,bt,organs);
		String hello = askBloodType();
		System.out.println(hello);
		
	}

}
