package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Antigen implements Serializable {

	
	private static final long serialVersionUID = -228773951826510194L;
	
	private Integer id;
	private boolean a;
	private boolean b;
	private boolean c;
	private boolean dp;
	private boolean dq;
	private boolean dr;
	
	
	
	//Empty constructor
	public Antigen() {
		super();
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public boolean isA() {
		return a;
	}



	public void setA(boolean a) {
		this.a = a;
	}



	public boolean isB() {
		return b;
	}



	public void setB(boolean b) {
		this.b = b;
	}



	public boolean isC() {
		return c;
	}



	public void setC(boolean c) {
		this.c = c;
	}



	public boolean isDp() {
		return dp;
	}



	public void setDp(boolean dp) {
		this.dp = dp;
	}



	public boolean isDq() {
		return dq;
	}



	public void setDq(boolean dq) {
		this.dq = dq;
	}



	public boolean isDr() {
		return dr;
	}



	public void setDr(boolean dr) {
		this.dr = dr;
	}




	@Override
	public String toString() {
		return "Antigen [id=" + id + ", a=" + a + ", b=" + b + ", c=" + c + ", dp=" + dp + ", dq=" + dq + ", dr=" + dr
				+ "]";
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
		Antigen other = (Antigen) obj;
		return Objects.equals(id, other.id);
	}


	
	
}
