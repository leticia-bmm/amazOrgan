package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Antibody implements Serializable {

	
	private static final long serialVersionUID = 7893987927997686073L;
	
	
	private Integer id;
	private Boolean class_I;
	private Boolean class_II;
	
	
	//Empty constructor
	public Antibody() {
		super();
	}

	
	public Antibody(Integer id, Boolean classI, Boolean classII) {
		this.id=id;
		this.class_I= classI;
		this.class_II= classII;
	}
	public Antibody(Boolean classI, Boolean classII) {
		this.class_I= classI;
		this.class_II= classII;
	}


	//getters and setters
	public Integer getID() {
		return id;
	}

	public void setID(Integer iD) {
		this.id = iD;
	}

	public Boolean isClass_I() {
		return class_I;
	}

	public void setClass_I(Boolean class_I) {
		this.class_I = class_I;
	}

	public Boolean isClass_II() {
		return class_II;
	}

	public void setClass_II(Boolean class_II) {
		this.class_II = class_II;
	}


	@Override
	public String toString() {
		return "ANTIBODY:"
				+ "\n\tClass I: " + class_I
				+ "\n\tClass II " + class_II;
	}


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
		Antibody other = (Antibody) obj;
		return Objects.equals(id, other.id);
	}


	
	
	
}
