package amazOrgan.pojos;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "lifespan"})
public class Type_organ implements Serializable {

	
	private static final long serialVersionUID = -60093262058037111L;
	@XmlAttribute
	private Integer id;  //same names as in the database
	@XmlAttribute
	private String name;
	@XmlAttribute
	private Integer lifespan;
	@XmlTransient
	private List <Request> requests;
	@XmlTransient
	private List <Organ> organs;
	
	
	public Type_organ() {
		super(); 
	}

	public Type_organ(Integer id, String name, Integer lifespan) {
		super();
		this.id = id;
		this.name = name;
		this.lifespan = lifespan;
	}


	public Type_organ(String organ_name) {
		super();
		this.name = organ_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLifespan() {
		return lifespan;
	}

	public void setLifespan(Integer lifespan) {
		this.lifespan = lifespan;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, lifespan, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type_organ other = (Type_organ) obj;
		return Objects.equals(id, other.id) && Objects.equals(lifespan, other.lifespan)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "\tTYPE OF ORGAN:"
				+ "\n\t\tId of the type of organ: " + id
				+ "\n\t\tName: " + name
				+ "\n\t\tLifespan: " + lifespan;
	}



	
	

	
	
	
	
}
