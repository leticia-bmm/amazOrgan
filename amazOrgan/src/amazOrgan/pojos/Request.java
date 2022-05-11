package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable{

	private static final long serialVersionUID = 5641142258720418989L;
	private Integer id;
	private Type_organ type_organ;
	private Float size;
	private boolean received;
	private Organ organ_id;
	
	//constructor
	public Request() {
		super();
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
	
	public Organ getOrganId() {
		return organ_id;
	}

	public void setOrganId(Organ organ_id) {
		this.organ_id = organ_id;
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
				+ ", donor=" + organ_id + "]";
	}

	
	
	
	
	
	
}
