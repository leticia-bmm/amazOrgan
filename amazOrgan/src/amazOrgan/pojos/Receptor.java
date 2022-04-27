package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Receptor implements Serializable{

	  private static final long serialVersionUID = -4194855458607458480L;
	  private Integer dni;
	  private Integer dob;
	  private String status;
	  private String blood_type;
	  private Integer urgency;
	  private Antigen antigen;
	  private Antibody antibody;
	  private Location location;
	  private Doctor doctor;
	  private Request request;
	 
	  
	  //empty Constructor
	public Receptor() {
		super();
	}
	
	//Getters and Setters
	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Integer getDob() {
		return dob;
	}

	public void setDob(Integer dob) {
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	
	

	
	//hashcode
	@Override
	public int hashCode() {
		return Objects.hash(antibody, antigen, blood_type, dni, dob, doctor, location, request, status, urgency);
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
		return Objects.equals(antibody, other.antibody) && Objects.equals(antigen, other.antigen)
				&& Objects.equals(blood_type, other.blood_type) && Objects.equals(dni, other.dni)
				&& Objects.equals(dob, other.dob) && Objects.equals(doctor, other.doctor)
				&& Objects.equals(location, other.location) && Objects.equals(request, other.request)
				&& Objects.equals(status, other.status) && Objects.equals(urgency, other.urgency);
	}

	
	//toString
	@Override
	public String toString() {
		return "Receptor [dni=" + dni + ", dob=" + dob + ", status=" + status + ", blood_type=" + blood_type
				+ ", urgency=" + urgency + ", antigen=" + antigen + ", antibody=" + antibody + ", location=" + location
				+ ", doctor=" + doctor + ", request=" + request + "]";
	}

	

	
	
	
	  
	  
	  
	  
	  
	
}
