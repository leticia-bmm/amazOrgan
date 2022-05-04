package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1798339578536720273L;
	
	//we create the class User to handle the login and log out methods
	@Id
	@GeneratedValue(generator = "users")
	@TableGenerator(name = "users", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "users")
	private Integer id;
	private String medical_id;
	@Lob
	private byte[] password;
	@OneToOne
	//type of relationship with the other class
	@JoinColumn(name = "medical_id")
	//which class?
	private Doctor doctor;
	
	
	User() {
		super();
	}

	public User(String medical_id, byte[] password) {
		super();
		this.medical_id = medical_id;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMedical_id() {
		return medical_id;
	}

	public void setMedical_id(String medical_id) {
		this.medical_id = medical_id;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
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
		return "User [id=" + id + ", medical id=" + medical_id + ", password=" + Arrays.toString(password) + "]";
	}

}
