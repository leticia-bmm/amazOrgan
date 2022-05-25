package amazOrgan.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)  //annotations in the attributes
@XmlRootElement(name = "Organ-List")//int his calss the name of the root is going to be Organ
//@XmlSeeAlso({Organ.class})
@XmlType(propOrder = { "organs"})
//propr element the order in wicht they apear 
public class OrganList implements Serializable{


	private static final long serialVersionUID = -2837126457259916536L;
	@XmlElement
	private List<Organ> organs;

	public OrganList(List<Organ> organs) {
		//super();
		this.organs = organs;
	}
	
	public OrganList () {
		
	}


	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(organs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganList other = (OrganList) obj;
		return Objects.equals(organs, other.organs);
	}

	@Override
	public String toString() {
		return "OrganList [organs=" + organs + "]";
	}
	
	
}
