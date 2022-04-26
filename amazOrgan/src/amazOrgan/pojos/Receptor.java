package amazOrgan.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Receptor implements Serializable {
	

	
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer DNI;
  private Integer DOB;
  private String status;
  private String blood_type;
  private Integer urgency;
  List<Antigen> antigen;
  List<Antibody> antobody;
  Location location;
  Doctor doctor_charge;
  Request request;
  int DNI_donor;
  
  
  
public Receptor(Integer dNI, Integer dOB, String status, String blood_type, Integer urgency,
		List<Antigen> antigen, List<Antibody> antobody, Location location, Doctor doctor_charge,
		Request request, int dNI_donor) {
	super();
	DNI = dNI;
	DOB = dOB;
	this.status = status;
	this.blood_type = blood_type;
	this.urgency = urgency;
	this.antigen = antigen;
	this.antobody = antobody;
	this.location = location;
	this.doctor_charge = doctor_charge;
	this.request = request;
	DNI_donor = dNI_donor;
}
public Integer getDNI() {
	return DNI;
}
public void setDNI(Integer dNI) {
	DNI = dNI;
}
public Integer getDOB() {
	return DOB;
}
public void setDOB(Integer dOB) {
	DOB = dOB;
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
public List<Antigen> getAntigen() {
	return antigen;
}
public void setAntigen(List<Antigen> antigen) {
	this.antigen = antigen;
}
public List<Antibody> getAntobody() {
	return antobody;
}
public void setAntobody(List<Antibody> antobody) {
	this.antobody = antobody;
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
public Request getRequest() {
	return request;
}
public void setRequest(Request request) {
	this.request = request;
}
public int getDNI_donor() {
	return DNI_donor;
}
public void setDNI_donor(int dNI_donor) {
	DNI_donor = dNI_donor;
}
@Override
public String toString() {
	return "Receptor [DNI=" + DNI + ", DOB=" + DOB + ", status=" + status + ", blood_type=" + blood_type + ", urgency="
			+ urgency + ", location=" + location + ", DNI_donor=" + DNI_donor + "]";
}
@Override
public int hashCode() {
	return Objects.hash(DNI, DNI_donor, DOB, blood_type, location, status, urgency);
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
	return Objects.equals(DNI, other.DNI) && DNI_donor == other.DNI_donor && Objects.equals(DOB, other.DOB)
			&& Objects.equals(blood_type, other.blood_type) && Objects.equals(location, other.location)
			&& Objects.equals(status, other.status) && Objects.equals(urgency, other.urgency);
}
  
}
