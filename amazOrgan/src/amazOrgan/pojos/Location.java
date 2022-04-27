package amazOrgan.pojos;

import java.io.Serializable;

public class Location implements Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 2724475628719553532L;
		private Integer id;
		private Float latitude;
		private Float longitude;
		/**
		 * @return the id
		 */

public Location() {
	
}
	
		public Integer getId() {
			return id;
		}
		
		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}
		/**
		 * @return the latitude
		 */
		public Float getLatitude() {
			return latitude;
		}
		/**
		 * @param latitude the latitude to set
		 */
		public void setLatitude(Float latitude) {
			this.latitude = latitude;
		}
		/**
		 * @return the longitude
		 */
		public Float getLongitude() {
			return longitude;
		}
		/**
		 * @param longitude the longitude to set
		 */
		public void setLongitude(Float longitude) {
			this.longitude = longitude;
		}
		@Override
		public String toString() {
			return "Location [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
		}
		
		

}
