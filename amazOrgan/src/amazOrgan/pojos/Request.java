package amazOrgan.pojos;

import java.util.Objects;

public class Request {

	private Integer id;
	private Type_organ type_organ;
	private Float size;
	private boolean received;
	
	//constructor
	
	
	
	//getters and setters
	public Integer getDNI() {
		return id;
	}

	public Request() {
		super();
	}

	public void setDNI(Integer dNI) {
		id = dNI;
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
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}


	//to string method
	@Override
	public String toString() {
		return "Request [id=" + id + ", type_organ=" + type_organ + ", size=" + size + ", received=" + received + "]";
	}
	
	
	
	
}
