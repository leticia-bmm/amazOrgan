package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Doctor implements Serializable {
	
	
	private static final long serialVersionUID = -7252588993432397735L;
	
	private Integer medical_id;
	private Integer phone_number;
	private String name;
	
	
	
	public Doctor() {
		super();
	}
	
	public Integer getmedical_id() {
		return medical_id;
	}
	public void setmedical_id(Integer medical_id) {
		this.medical_id  = medical_id;
	}
	public Integer getphone_number() {
		return phone_number;
	}
	public void setphone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Doctor [medical_id=" + medical_id + ", phone_number=" + phone_number + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(medical_id);
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
		return Objects.equals(medical_id, other.medical_id);
	}
	
	
	
	
}
