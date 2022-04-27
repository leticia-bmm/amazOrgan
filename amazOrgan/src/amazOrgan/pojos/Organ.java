package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Organ implements Serializable{

	
	private static final long serialVersionUID = 5480172447522775066L;
	private Integer id;
	private Type_organ type_organ;
	private Float size;
	private boolean available;
	
	//constructor
	public Organ() {
		super();
	}
	
	//getters and setters
	public Integer getID() {
		return id;
	}
	public void setID(Integer iD) {
		this.id = iD;
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
		return "Organ [ID=" + id + ", size=" + size + ", available=" + available + "]";
	}

	
	//hash code only with id
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
	
	

}
