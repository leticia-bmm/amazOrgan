package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Antibody implements Serializable {

	
	private static final long serialVersionUID = 7893987927997686073L;
	
	
	Integer ID;
	boolean class_I;
	boolean class_II;
	
	
	//Empty constructor
	public Antibody() {
		super();
	}

	
	//getters and setters
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
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
		return "Antibody [ID=" + ID + ", class_I=" + class_I + ", class_II=" + class_II + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(ID, class_I, class_II);
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
		return Objects.equals(ID, other.ID) && class_I == other.class_I && class_II == other.class_II;
	}
	
	
	
	
	
	
	
	
}
