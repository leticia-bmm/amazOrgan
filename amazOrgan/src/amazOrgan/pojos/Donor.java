package amazOrgan.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"dni", "bloodType"})
public class Donor implements Serializable {

	private static final long serialVersionUID = 7891925133827348336L;
	@XmlAttribute
	private Integer dni;
	@XmlTransient
	private LocalDate dob;
	@XmlTransient
	private Boolean alive;
	@XmlAttribute
	private String bloodType;
	@XmlTransient
	private Antigen antigen; 
	@XmlTransient
	private Antibody antibody;
	@XmlTransient
	private Location location;
	@XmlTransient
	private Doctor doctor_charge;
	@XmlTransient
	private List<Organ> organs;

	// constructor
	public Donor() {
		super();
	}
	
	public Donor(Integer dNI, LocalDate dOB, Boolean alive, String bloodType, Antigen antigen, Antibody antibody,
			Location location, Doctor doctor_charge, List<Organ> organs) {
		super();
		this.dni = dNI;
		this.dob = dOB;
		this.alive = alive;
		this.bloodType = bloodType;
		this.antigen = antigen;
		this.antibody = antibody;
		this.location = location;
		this.doctor_charge = doctor_charge;
		this.organs = organs;
	}
	

	public Donor(Integer dni, String bloodType, List<Organ> organs) {
		super();
		this.dni = dni;
		this.bloodType = bloodType;
		this.organs = organs;
	}
	
	public Donor (Integer dni) {
		super();
		this.dni = dni;
	}
	

	public Donor(Integer donor_id, Boolean alive) {
		super();
		this.dni = donor_id;
		this.alive = alive;
	}

	public Donor(Integer dni, LocalDate dob, Boolean alive, String bloodType, Antigen antigen, Antibody antibody,
			Location location, Doctor doctor_charge) {
		super();
		this.dni = dni;
		this.dob = dob;
		this.alive = alive;
		this.bloodType = bloodType;
		this.antigen = antigen;
		this.antibody = antibody;
		this.location = location;
		this.doctor_charge = doctor_charge;
	}


	public Donor(Integer dni, LocalDate dob, Boolean b) {
		super();
		this.dni = dni;
		this.dob = dob;
		this.alive = b;
		this.antigen = new Antigen();
		this.antibody = new Antibody();
		this.location = new Location();
		
	}


	public Donor(Integer dni, LocalDate dob, Boolean alive, String bloodtype, Antigen antigen, Antibody antibody,
			Location location) {
		super();
		this.dni = dni;
		this.dob = dob;
		this.alive = alive;
		this.bloodType = bloodtype;
		this.antigen = antigen;
		this.antibody = antibody;
		this.location = location;
		this.doctor_charge = null;
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

	// hash
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


    @Override
	public String toString() {
		return  "-----------DONOR-----------"
				+ "\nDNI: " + dni
				+ "\nDate of birth: " + dob
				+ "\nAlive: " + alive
				+ "\nBlood type: " + bloodType
				+ "\n" + antigen
				+ "\n" + antibody
				+ "\n" + location
				+ "\n" + organs
				+ "\n" + doctor_charge;
	}

		
}
