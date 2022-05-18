package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Antibody implements Serializable {

	
	private static final long serialVersionUID = 7893987927997686073L;
	
	
	private Integer id;
	private boolean class_I;
	private boolean class_II;
	
	
	//Empty constructor
	public Antibody() {
		super();
	}

	
	public Antibody(Boolean classI, Boolean classII) {
		this.class_I= classI;
		this.class_II= classII;
		// TODO Auto-generated constructor stub
	}


	//getters and setters
	public Integer getID() {
		return id;
	}

	public void setID(Integer iD) {
		this.id = iD;
	}

	public boolean isClass_I() {
		return class_I;
	}

	public void setClass_I(boolean class_I) {
		this.class_I = class_I;
	}

	public boolean isClass_II() {
		return class_II;
	}

	public void setClass_II(boolean class_II) {
		this.class_II = class_II;
	}


	@Override
	public String toString() {
		return "Antibody [ID=" + id + ", class_I=" + class_I + ", class_II=" + class_II + "]";
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
