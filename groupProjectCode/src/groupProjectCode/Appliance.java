package groupProjectCode;
import java.util.Random;

public class Appliance{
    public int onWattage;
    private double probabilityOn;
    private String applianceID;
    private String locationID;
    private double powerReductionPercentage;
    private String description;
    private boolean isSmart; 
    private boolean isOn = false;
    
    public Appliance(String locID, String description, int onWatt, double probOn, boolean isSmart, double percentPowerReduction, String appID, boolean onStatus) {
    	locationID = locID;
    	this.description = description; 
    	onWattage = onWatt;
    	this.probabilityOn = probOn; 
    	this.isSmart = isSmart;
    	powerReductionPercentage = percentPowerReduction;
    	this.applianceID = appID;
    	this.isOn = onStatus;	
    }
    
    public String getLocationID() {
    	return locationID;
    }
    public String getDescription() {
    	return this.description;
    }
    
    public double getOnProbability() {
    	return this.probabilityOn;
    }
    public double getPercentPowerReduction() {
    	return this.powerReductionPercentage;
    }
    
    
    public void setApplianceID(String applianceID) {
    	this.applianceID = applianceID;
    }
    
    
    public String getAppID() {
    	return this.applianceID;
    }
    
    
    public int getOnWattage() {
    	return onWattage;
    	
    }
    
    public void setOnStatus() {
    	Random rand = new Random();
    	if (rand.nextDouble() <= this.probabilityOn) {
    		isOn = true;
    	}
    	else isOn = false;
    }
    
    public void setOnStatus(boolean isOn) {
    	this.isOn = isOn;
    }
    
    public boolean getOnStatus() {
    	return isOn;
    }
    
    public boolean getSmartStatus() {
    	return this.isSmart;
    }
    
    public void setLowStatus() {
    	this.onWattage =(int) Math.ceil((double)this.onWattage * (1.0 - powerReductionPercentage));
    }

}