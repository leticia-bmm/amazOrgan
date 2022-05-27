package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1798339578536720273L;

	@Id // to indicate the primary key: the following attribute
	@GeneratedValue(generator = "users")
	@TableGenerator(name = "users", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "users")

	private Integer id; // can be medical_id if the user is a doctor or the DNI if the user is a donor

	@Lob // to indicate that it is an array of bytes
	private byte[] password; //this is the digest not the password

	@ManyToOne // the other side of the relationship
	@JoinColumn(name = "role_id")
	private Role role;

	
	public User() {
		super();
	}

	public User(Integer id, byte[] password) {
		super();
		this.id = id;
		this.password = password;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + Arrays.toString(password) + ", role="
				+ role.getName() + "]";
	}

}