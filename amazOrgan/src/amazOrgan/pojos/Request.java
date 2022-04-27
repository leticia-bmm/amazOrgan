package amazOrgan.pojos;

import java.util.Objects;

public class Request {

	Integer ID;
	Type_organ type_organ;
	Float size;
	boolean received;
	
	//constructor
	public Request(Integer ID, Type_organ type_organ, Float size, boolean received) {
		super();
		this.ID = ID;
		this.type_organ = type_organ;
		this.size = size;
		this.received = received;
	}
	
	
	//getters and setters
	public Integer getDNI() {
		return ID;
	}

	public void setDNI(Integer dNI) {
		ID = dNI;
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

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}


	//hash code only taking into account the id
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(ID, other.ID);
	}


	//to string method
	@Override
	public String toString() {
		return "Request [ID=" + ID + ", type_organ=" + type_organ + ", size=" + size + ", received=" + received + "]";
	}
	
	
	
	
}
