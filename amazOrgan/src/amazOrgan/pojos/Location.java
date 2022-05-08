package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable{
	
	private static final long serialVersionUID = 2724475628719553532L;
	
	private Integer id;
	private Float latitude;
	private Float longitude;
		
	
	public Location() {
		super();
	}
	
	public Location(Float latitude, Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
		public Integer getId() {
			return id;
		}
		
		
		public void setId(Integer id) {
			this.id = id;
		}
		
		public Float getLatitude() {
			return latitude;
		}
		
		public void setLatitude(Float latitude) {
			this.latitude = latitude;
		}
		
		public Float getLongitude() {
			return longitude;
		}
		
		public void setLongitude(Float longitude) {
			this.longitude = longitude;
		}
		@Override
		public String toString() {
			return "Location [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
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
			Location other = (Location) obj;
			return Objects.equals(id, other.id);
		}


		
		

}
