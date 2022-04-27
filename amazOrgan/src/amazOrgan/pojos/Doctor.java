package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Doctor implements Serializable {
	
	
	private static final long serialVersionUID = -7252588993432397735L;
	
	private Integer Medical_id;
	private Integer Phone_number;
	private String Name;
	
	
	
	public Doctor() {
		super();
	}
	public Integer getMedical_ID() {
		return Medical_id;
	}
	public void setMedical_ID(Integer medical_ID) {
		Medical_id = medical_ID;
	}
	public Integer getPhone_number() {
		return Phone_number;
	}
	public void setPhone_number(Integer phone_number) {
		Phone_number = phone_number;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String toString() {
		return "Doctor [Medical_ID=" + Medical_id + ", Phone_number=" + Phone_number + ", Name=" + Name + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(Medical_id, Name, Phone_number);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(Medical_id, other.Medical_id) && Objects.equals(Name, other.Name)
				&& Objects.equals(Phone_number, other.Phone_number);
	}
	
	
	
}
