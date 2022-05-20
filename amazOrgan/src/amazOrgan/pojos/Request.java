package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable{

	private static final long serialVersionUID = 5641142258720418989L;
	private Integer id;
	private Type_organ type_organ;
	private Float size;
	private boolean received;
	private Organ organ_received;
	
	//constructor
	public Request() {
		super();
	}	
	
	public Request(Integer id, Type_organ type_organ, Float size, Boolean received, Organ o) {
		super();
		this.id = id;
		this.type_organ = type_organ;
		this.size = size;
		this.received = received;
		this.organ_received = o;		
	}
	
	
	public Request(Type_organ type_organ) {
		super();
		this.type_organ = type_organ;
	}

	//getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public Organ getOrgan() {
		return organ_received;
	}

	public void setOrgan(Organ organ_received) {
		this.organ_received = organ_received;
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
		Request other = (Request) obj;
		return Objects.equals(id, other.id);
	}

	//to string method
	@Override
	public String toString() {
		return "Request [id=" + id + ", type_organ=" + type_organ + ", size=" + size + ", received=" + received
				+ ", donor=" + organ_received + "]";
	}

	
	
	
	
	
	
}
