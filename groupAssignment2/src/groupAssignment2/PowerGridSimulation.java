package groupAssignment2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



    //locationID, description, onWatt, probabilityOn, isSmart, percentPowerReduction);


//public class Smart extends Appliances{


//public class Regular extends Appliances {

public class PowerGridSimulation {

    public static void main(String[] args) {
    
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the total allowed wattage.");
        // add input validation
        int totalAllowedWattage = scnr.nextInt();
        // add input validation
        System.out.println("Enter the amount of timesteps.");
        int timeSteps = scnr.nextInt();
        
        System.out.println("Enter the name of the input file");

        String nameInputFile = scnr.next();
        
        
        
        
        
        // Timestep Algorithm Part 1: 
        // 
        ArrayList<Appliance> applianceList = new ArrayList<>();
        try{
        	File file = new File(nameInputFile);
            Scanner inputFile = new Scanner(file);
            
            
            // add option for user to make changes 
            
            while(inputFile.hasNextLine()){
                String currentLine = inputFile.nextLine();
                String[] applianceInfo = currentLine.split(",");
                String locationID = applianceInfo[0];
                String description = applianceInfo[1];
                int onWatt = Integer.parseInt(applianceInfo[2]);
                double probabilityOn = Double.parseDouble(applianceInfo[3]);
                boolean isSmart = Boolean.parseBoolean(applianceInfo[4]);
                double percentPowerReduction = Double.parseDouble(applianceInfo[5]);
                
                
                
                
                Appliance currentAppliance = new Appliance(locationID, description, onWatt, probabilityOn, isSmart, percentPowerReduction);
                currentAppliance.setOnStatus(); 
                applianceList.add(currentAppliance);
            }
        }
        
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        // Sort appliances in descending order 
        Collections.sort(applianceList, new Comparator<Appliance>(){
        	public int compare(Appliance a1, Appliance a2) {
        		return Integer.valueOf(a2.onWattage).compareTo(a1.onWattage);
        	}
        });
        
        int totalPowerConsumption = 0;
        for (int j = 0; j < applianceList.size(); j++) {
        	if (applianceList.get(j).getOnStatus()) {
        		totalPowerConsumption += applianceList.get(j).getOnWattage();
        	}
        	
        }
        //test
        for (int a = 0; a < 5; a++ ) {
        	System.out.print(applianceList.get(a).getSmartStatus() + " ");
        	System.out.println(applianceList.get(a).getOnWattage());
        }
        
        
        
        // setting smart appliances to low if allowed wattage is exceeded
        if (totalPowerConsumption > totalAllowedWattage){
        	for(int k = 0; k < applianceList.size(); k++) {
        		
        		
        		if (applianceList.get(k).getSmartStatus()) {
        			Appliance lowAppliance = new Appliance(applianceList.get(k).getLocationID(),
								        					applianceList.get(k).getDescription(),
								        					applianceList.get(k).getOnWattage(),
								        					applianceList.get(k).getOnProbability(), 
								        					applianceList.get(k).getSmartStatus(), 
								        					applianceList.get(k).getPercentPowerReduction());
					lowAppliance.setLowStatus();			        			
        			
        			applianceList.set(k,lowAppliance);
        			
        		}
        		if(totalPowerConsumption <= totalAllowedWattage) {
        			break;
        		}
        	}
        }
        
        // brown out if total allowed wattage is still exceeded
        
        if (totalPowerConsumption > totalAllowedWattage) {
        	
        }
        //test after
        System.out.println("\nAfter");
        for (int a = 0; a < 5; a++ ) {
        	System.out.print(applianceList.get(a).getSmartStatus() + " ");
        	System.out.println(applianceList.get(a).getOnWattage());
        }
        
        
        // Counting appliances in each location 
        // index of locationList will be match the index of its Location counter in Location counterlist
        ArrayList<Integer> locationCounterList = new ArrayList<>(); // number of appliances in each location 
        ArrayList<String> locationList = new ArrayList<>(); // this list has the total unique locations
        
        for(int i = 0; i < applianceList.size(); i++) {
        	String location = applianceList.get(i).getLocationID();
        	if (locationList.indexOf(location) != -1) { // if the location has already been counted we will skip to 
        												// the next iteration
        		continue;
        	}
        	locationList.add(location); // adds location string to locationList to ensure we do not count the same location
        	int locationCounter = 1; // first instance of location id
        	
        	for (int j = 0; j < applianceList.size(); j++) {
        		if (j != i) {
        			if (applianceList.get(j).getLocationID().equals(location)) {
        				locationCounter++;
        			}
        		}
        		
        	}
        	
        	locationCounterList.add(Integer.valueOf(locationCounter));
        }
        
        for (int a = 0; a <10 ; a++) {
        	System.out.println(locationList.get(a) + ": "+ locationCounterList.get(a));
        }
        
        
        for()
        
        //test loop
        
        
        // use this loop to test 
        /*for (int i = 0; i < applianceList.size(); i++) {
        	
        	System.out.println(applianceList.get(i).getLocationID() + " " + applianceList.get(i).getOnWattage() + 
        			applianceList.get(i).getOnStatus() );
        	
        }
        
        System.out.println(totalPowerConsumption);
        System.out.println(totalPowerConsumption / applianceList.size());
        */
        

    } // end of main 

} // end of PowerGrid SimulationClass
