package amazOrgan.pojos;
import java.io.Serializable;
import java.util.Objects;

public class Type_organ implements Serializable {

	private static final long serialVersionUid = 3624405488128120546L;
	
	private Integer id;
	private String name;
	private Integer lifespan;
	
	public Type_organ() {
		super(); 
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
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
		return "Type_organ [id=" + id + ", name=" + name + ", lifespan=" + lifespan + "]";
	}
	
	

	
	
	
	
}
