package groupProjectCode;

public class Location {

	public String locationId;
	public int numApp;
	
	public int affectedCounter;
	
	public Location(String locID, int numApp, int affectedCounter) {
		this.locationId = locID;
		this.numApp = numApp;
		this.affectedCounter = affectedCounter;
	}
	
	
	
	public void incrementAffectedCounter() {
		this.affectedCounter ++;
	}
	
	
	public int getAffectedCounter() {
		return this.affectedCounter;
	}
	
	
	public String getlocID() {
		return this.locationId;
	}
	
	public int getNumApp() {
		return this.numApp;
	}
	
	
	
}