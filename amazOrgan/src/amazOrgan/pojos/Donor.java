package amazOrgan.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Donor implements Serializable{

	private static final long serialVersionUID = 7891925133827348336L;
	private Integer dni;
	private LocalDate dob;
	private boolean alive;
	private String bloodType;
	private Antigen antigen;
	private Antibody antibody;
	private Location location;
	private Doctor doctor_charge;
	private List <Organ> organs;
	
	//constructor
	public Donor(Integer dNI, LocalDate dOB, boolean alive, String bloodType, Antigen antigen, Antibody antibody,
			Location location, Doctor doctor_charge, List<Organ> organs) {
		super();
		dni = dNI;
		dob = dOB;
		this.alive = alive;
		this.bloodType = bloodType;
		this.antigen = antigen;
		this.antibody = antibody;
		this.location = location;
		this.doctor_charge = doctor_charge;
		this.organs = organs;
	}
	
	// getters and setters

	public Integer getdni() {
		return dni;
	}

	public void setdni(Integer dni) {
		this.dni = dni;
	}

	public LocalDate getdob() {
		return dob;
	}

	public void setdob(LocalDate dOB) {
		this.dob = dOB;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Antigen getAntigen() {
		return antigen;
	}

	public void setAntigen(Antigen antigen) {
		this.antigen = antigen;
	}

	public Antibody getAntibody() {
		return antibody;
	}

	public void setAntibody(Antibody antibody) {
		this.antibody = antibody;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Doctor getDoctor_charge() {
		return doctor_charge;
	}

	public void setDoctor_charge(Doctor doctor_charge) {
		this.doctor_charge = doctor_charge;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	
	//hash 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		return Objects.equals(dni, other.dni);
	}

	
	//this string has to be adapted with the foreign keys
	@Override
	public String toString() {
		return "Donor [dni=" + dni + ", dob=" + dob + ", alive=" + alive + ", bloodType=" + bloodType + "]";
	}
}
