package amazOrgan.ifaces;

import amazOrgan.pojos.Location;

public interface LocationManager {
	
	// Insert location when donor dies
	public void addLocation (Location l) ;
	// Delete location
	public void deleteLocation (Integer ID);
}
