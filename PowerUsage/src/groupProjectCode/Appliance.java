package groupProjectCode;
import java.util.Random;

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
    private boolean isOn = false;
    private boolean isLow = false;
    
    
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
    public String getDescription() {
    	return this.description;
    }
    
    public double getOnProbability() {
    	return this.probabilityOn;
    }
    public double getPercentPowerReduction() {
    	return this.powerReductionPercentage;
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
    
    public boolean getOnStatus() {
    	return isOn;
    }
    
    public boolean getSmartStatus() {
    	return this.isSmart;
    }
    
    public void setLowStatus() {
    	this.onWattage =(int) Math.ceil((double)this.onWattage * (1.0 - powerReductionPercentage));
    }
    
    public void setOnStatus(boolean isOn) {
    	this.isOn = isOn;
    }
    
}