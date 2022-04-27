package amazOrgan.pojos;

import java.io.Serializable;
import java.util.List;
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
	  private Request request;
	  private List <Doctor> doctors;
	 
	  
	//empty Constructor
	public Receptor() {
		super();
	}

	//getters setters
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
		return "Receptor [dni=" + dni + ", dob=" + dob + ", status=" + status + ", blood_type=" + blood_type
				+ ", urgency=" + urgency + ", antigen=" + antigen + ", antibody=" + antibody + ", location=" + location
				+ ", request=" + request + ", doctors=" + doctors + "]";
	}
	  
	
}
