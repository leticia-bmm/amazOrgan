package amazOrgan.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Doctor implements Serializable {
	
	
	private static final long serialVersionUID = -7252588993432397735L;
	
	private Integer medical_id;
	private Integer phone_number;
	private String name;
	private List<Donor> donors;
	private List <Receptor> receptors;
	//TODO elimination of the table 
	
	//empty constructor
	public Doctor() {
		super();
	}
	
	
	public Doctor(Integer medical_id, Integer phone, String name2) {
		super();
		this.medical_id = medical_id;
		this.phone_number = phone;
		this.name = name2;
	}
	
	public Doctor(Integer medical_id, Integer phone, String name2, List <Receptor> r, List <Donor> d) {
		super();
		this.medical_id = medical_id;
		this.phone_number = phone;
		this.name = name2;
		this.donors = d;
		this.receptors = r;
	}


<<<<<<< HEAD
	public Doctor(Integer medical_id) {
		super();
		this.medical_id = medical_id;
=======
	public Doctor(int medical_id2, Integer number) {
		super();
		this.medical_id = medical_id;
		this.phone_number = number;
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
	}


	public Integer getMedical_id() {
		return medical_id;
	}


	public void setMedical_id(Integer medical_id) {
		this.medical_id = medical_id;
	}


	public Integer getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Donor> getDonors() {
		return donors;
	}


	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}


	public List<Receptor> getReceptors() {
		return receptors;
	}


	public void setReceptors(List<Receptor> receptors) {
		this.receptors = receptors;
	}
	
	

	@Override
	public String toString() {
		return "DOCTOR:"
				+ "\n\tMedical ID: " + medical_id
				+ "\n\tName: " + name
				+ "\n\tPhone Number: " + phone_number;
	}


	//hashcode
	@Override
	public int hashCode() {
		return Objects.hash(medical_id);
	}

	//equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(medical_id, other.medical_id);
	}
	
	
	
	
}
