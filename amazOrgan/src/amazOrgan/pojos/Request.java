package amazOrgan.pojos;

import java.util.Objects;

public class Request {

	private Integer id;
	private Type_organ type_organ;
	private Float size;
	private boolean received;
	private Donor donor;
	
	//constructor
	public Request() {
		super();
	}	
	
	
	//getters and setters
	public Integer getDNI() {
		return id;
	}

	public void setDNI(Integer dNI) {
		this.id = dNI;
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
	
	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}


	//hash code only with id
	@Override
	public int hashCode() {
<<<<<<< HEAD
		return Objects.hash(id);
=======
		return Objects.hash(donor, id, received, size, type_organ);
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
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
<<<<<<< HEAD
		return Objects.equals(id, other.id);
=======
		return Objects.equals(donor, other.donor) && Objects.equals(id, other.id) && received == other.received
				&& Objects.equals(size, other.size) && Objects.equals(type_organ, other.type_organ);
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
	}


<<<<<<< HEAD
	//to string method
=======
>>>>>>> branch 'master' of https://github.com/leticia-bmm/amazOrgan
	@Override
	public String toString() {
		return "Request [id=" + id + ", type_organ=" + type_organ + ", size=" + size + ", received=" + received
				+ ", donor=" + donor + "]";
	}

	
	
	
	
	
	
}
