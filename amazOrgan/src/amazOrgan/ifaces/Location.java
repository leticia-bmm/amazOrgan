package amazOrgan.ifaces;

public interface Location {
	
	// Insert location when donor dies
	public void addLocation (Location l) ;
	// Delete location
	public void deleteLocation (Integer DNI);
}
