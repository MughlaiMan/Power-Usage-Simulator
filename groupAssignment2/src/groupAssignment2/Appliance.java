package groupAssignment2;

public class Appliance{
    private String type; 
    public int onWattage;
    private double probabilityOn;
    private String applianceID;
    private String locationID;
    private double powerReductionPercentage;
    private int powerReductionTotal;
    private String description;
    private boolean isSmart; 
    
    public Appliance(String locID, String description, int onWatt, double probOn, boolean isSmart, double percentPowerReduction) {
    	locationID = locID;
    	this.description = description; 
    	onWattage = onWatt;
    	probabilityOn = probOn; 
    	this.isSmart = isSmart;
    	powerReductionPercentage = percentPowerReduction; 
    	
    	
    }
    
    public String getLocationID() {
    	return locationID;
    }
    
    public int getOnWattage() {
    	return onWattage;
    	
    }
}