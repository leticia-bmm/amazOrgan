package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Antigen implements Serializable {

	
	private static final long serialVersionUID = -228773951826510194L;
	
	private Integer id;
	private Boolean a;
	private Boolean b;
	private Boolean c;
	private Boolean dp;
	private Boolean dq;
	private Boolean dr;
	
	
	
	//Empty constructor
	public Antigen() {
		super();
	}


	public Antigen(Boolean a2, Boolean b2, Boolean c2, Boolean dp2, Boolean dq2, Boolean dr2) {
		super();
		this.a = a2;
		this.b = b2;
		this.c = c2;
		this.dp = dp2;
		this.dq = dq2;
		this.dr = dr2;
	}

	public Antigen(Integer id, Boolean a2, Boolean b2, Boolean c2, Boolean dp2, Boolean dq2, Boolean dr2) {
		super();
		this.id= id;
		this.a = a2;
		this.b = b2;
		this.c = c2;
		this.dp = dp2;
		this.dq = dq2;
		this.dr = dr2;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Boolean isA() {
		return a;
	}



	public void setA(Boolean a) {
		this.a = a;
	}



	public Boolean isB() {
		return b;
	}



	public void setB(Boolean b) {
		this.b = b;
	}



	public Boolean isC() {
		return c;
	}



	public void setC(Boolean c) {
		this.c = c;
	}



	public Boolean isDp() {
		return dp;
	}



	public void setDp(Boolean dp) {
		this.dp = dp;
	}



	public Boolean isDq() {
		return dq;
	}



	public void setDq(Boolean dq) {
		this.dq = dq;
	}



	public Boolean isDr() {
		return dr;
	}



	public void setDr(Boolean dr) {
		this.dr = dr;
	}



    @Override
	public String toString() {
		return  "ANTIGENS:"
				+ "\n\tA: " + a
				+ "\n\tB: " + b
				+ "\n\tC: " + c
				+ "\n\tDP: " + dp
				+ "\n\tDQ: " + dq
				+ "\n\tDR: " + dr;
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
