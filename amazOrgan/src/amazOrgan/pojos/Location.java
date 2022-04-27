package amazOrgan.pojos;

import java.io.Serializable;

public class Location implements Serializable{
	
	private static final long serialVersionUID = 2724475628719553532L;
	
	private Integer id;
	private Float latitude;
	private Float longitude;
		
	
	public Location() {
	
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
		
		

}
