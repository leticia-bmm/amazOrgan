package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Organ implements Serializable{

	
	private static final long serialVersionUID = 5480172447522775066L;
	Integer ID;
	Type_organ type_organ;
	Float size;
	boolean available;
	
	//constructor
	public Organ(Integer iD, Type_organ type_organ, Float size, boolean available) {
		super();
		ID = iD;
		this.type_organ = type_organ;
		this.size = size;
		this.available = available;
	}
	
	//getters and setters
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Type_organ getType_organ() {
		return type_organ;
	}
	public void setType_organ(Type_organ type_organ) {
		this.type_organ = type_organ;
	}
	public Float getSize() {
		return size;
	}
	public void setSize(Float size) {
		this.size = size;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	//tostring
	@Override
	public String toString() {
		return "Organ [ID=" + ID + ", size=" + size + ", available=" + available + "]";
	}
	
	
	//hash code only taking into account the ID
	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organ other = (Organ) obj;
		return Objects.equals(ID, other.ID);
	}
	
	
	
	
	
	
	
	
	

}
