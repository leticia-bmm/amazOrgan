package amazOrgan.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Receptor implements Serializable{

	  private static final long serialVersionUID = -4194855458607458480L;
	  private Integer dni;
	  private LocalDate dob;
	  private String status;
	  private String blood_type;
	  private Boolean alive;
	  private Integer urgency;
	  private Antigen antigen;
	  private Antibody antibody;
	  private Location location;
	  private Request request;
	  private List <Doctor> doctors;

	 
	  
	//empty Constructor
	public Receptor() {
		super();
	}
	
	public Receptor(Integer dni, LocalDate dob, String status, String blood_type, Boolean alive, Integer urgency,
			Antigen antigen, Antibody antibody, Location location, Request request) {
		super();
		this.dni = dni;
		this.dob = dob;
		this.status = status;
		this.blood_type = blood_type;
		this.alive = alive;
		this.urgency = urgency;
		this.antigen = antigen;
		this.antibody = antibody;
		this.location = location;
		this.request = request;
		
	}

	public Receptor(Integer receptor_id, String status, Boolean alive, Integer urgency) {
		super();
		this.dni = receptor_id;
		this.status = status;
		this.urgency = urgency;
		this.alive = alive;
	}

	public Receptor(Integer dni, String status, Integer urgency, Request request) {
		super();
		this.dni = dni;
		this.status = status;
		this.urgency = urgency;
		this.request = request;		
	}

	public Receptor(Integer dni, LocalDate dob, String status, String blood_type, Boolean alive, Integer urgency) {
		super();
		this.dni = dni;
		this.dob = dob;
		this.status = status;
		this.blood_type = blood_type;
		this.alive = alive;
		this.urgency = urgency;
	}

	public Receptor(Integer receptor_id, String status, Integer urgency, Boolean alive) {
		super();
		this.dni = receptor_id;
		this.status = status;
		this.urgency = urgency;
		this.alive = alive;
	}

	public Boolean getAlive() {
		return alive;
	}
	
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	//getters setters
	public Integer getDni() {
		return dni;
	}


	public void setDni(Integer dni) {
		this.dni = dni;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getBlood_type() {
		return blood_type;
	}


	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}


	public Integer getUrgency() {
		return urgency;
	}


	public void setUrgency(Integer urgency) {
		this.urgency = urgency;
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


	public Request getRequest() {
		return request;
	}


	public void setRequest(Request request) {
		this.request = request;
	}


	public List<Doctor> getDoctors() {
		return doctors;
	}


	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	//hash code
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receptor other = (Receptor) obj;
		return Objects.equals(dni, other.dni);
	}

	//to string
	@Override
	public String toString() {
		return "-----------RECEPTOR-----------"
				+ "\nDNI: " + dni
				+ "\nDate of birth: " + dob
				+ "\nStatus: " + status
				+ "\nAlive: " + alive
				+ "\nBlood type: " + blood_type
				+ "\nUrgency: " + urgency
				+ "\n" + antigen
				+ "\n" + antibody
				+ "\n" + location
				+ "\n" + request
				+ "\n" + doctors;
	}
	  
	
}
