package amazOrgan.pojos;

import java.util.List;
import java.util.Objects;

public class OrganList {

	private List<Organ> organs;

	public OrganList(List<Organ> organs) {
		super();
		this.organs = organs;
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
