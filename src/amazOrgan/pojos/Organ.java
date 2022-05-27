package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)  //annotations in the attributes
@XmlRootElement(name = "Organ")//int his calss the name of the root is going to be Organ
@XmlType(propOrder = { "type_organ", "size", "available", "donor"})
//propr element the order in wicht they apear 
@XmlSeeAlso({OrganList.class})
public class Organ implements Serializable{

	
	private static final long serialVersionUID = 5480172447522775066L;
	@XmlAttribute
	private Integer id;
	@XmlElement
	private Type_organ type_organ;
	@XmlAttribute
	private Float size;
	@XmlAttribute
	private Boolean available;
	@XmlElement
	private Donor donor;
	//we have to anotate the class even if we only have to show DNI
	
	//for ommiting infromation to XML
	//the annotation will be XmlTransient
	
	//element --> can have other elements and attributes inside 
	//written as <>
	//attributes --> cannot have anything inside
	//written beside the element or root
	
	
	//constructor
	public Organ() {
		super();
	}
	
	//Another constructor
	public Organ(Integer id, Float size, boolean available) {
		super();
		this.id = id;
		this.size = size;
		this.available = available;
		
	}
	
	//TODO preguntar lo del super
	public Organ(Integer organ_id, Type_organ t, Float size_organ, Boolean available, Donor d) {
		super();
		this.id = organ_id;
		this.type_organ = t;
		this.size = size_organ;
		this.available = available;
		this.donor = d;
		
	}

	public Organ(Type_organ t) {
		super();
		this.type_organ = t;
	}

	public Organ(Integer id, Float size_organ, Boolean available, Donor donor) {
		super();
		this.id = id;
		this.size = size_organ;
		this.available = available;
		this.donor = donor;
	}

	//getters and setters
	public Integer getID() {
		return id;
	}

	public void setID(Integer iD) {
		this.id = iD;
	}
	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
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
		return "Organ [ID=" + id + ", size=" + size + ", available=" + available + "donor = "+ donor +"]";
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
