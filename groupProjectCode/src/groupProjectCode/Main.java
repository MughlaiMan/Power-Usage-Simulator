package groupProjectCode;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {

	public static String summary_Report = "";

	
    public static void main(String[] args) throws IOException {
        ArrayList<Appliance> applianceList = new ArrayList<>();
        ArrayList<String> applianceID = new ArrayList<>();
        
    	
    	int lowCount = 0;
    	int brownCount = 0;
    	
    	
    	String maxEffLoc = "";
    	String output = ""; 
    	
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the total allowed wattage.");
  
        int totalAllowedWattage = scnr.nextInt();
        
      
        System.out.println("Enter the amount of timesteps.");
        int timeSteps = scnr.nextInt();
        
        System.out.println("Enter the name of the input file");

        String nameInputFile = scnr.next();
        addAppliancesFromFile(applianceList,applianceID,nameInputFile);
        


        //USER MENU OPTIONS
        String menuInput = "";
        
        while (!menuInput.equalsIgnoreCase("S")) {
        	 
        	 
        	 System.out.println("Select an option:");
             System.out.println("Type 'A' Add an appliance");
             System.out.println("Type 'D' Delete an appliance");
             System.out.println("Type 'L' List the appliances");
             System.out.println("Type 'F' Read Appliances from a file");
             System.out.println("Type 'S' To Start the simulation");
             System.out.println("Type 'Q' Quit the program");
             
             
             menuInput = scnr.next();
             
             if(menuInput.equalsIgnoreCase("A")) {
             	
                 
                 System.out.println("Enter the location ID:");
                 String locationID = scnr.next();

                 
                 System.out.println("Enter the appliance description:");
                 String desc = scnr.next();
                 
                 
                 System.out.println("Enter the wattage used by the appliance when its on:");
                 int onWattage = scnr.nextInt();
                 
                 System.out.println("Enter the probability that the appliance is on:");
                 double probOn = scnr.nextDouble();
                 
                 System.out.println("Is the appliance smart or not(Enter true or false):");
                 boolean isSmart = scnr.nextBoolean();
                 
                 
                 System.out.println("Enter the percentage of power reduction(Enter a decimal value between 0 and 1):");
                 double percentPowerRed = scnr.nextDouble();
                 
                 
                 applianceList.add(new Appliance(locationID, desc, onWattage, probOn, isSmart, percentPowerRed, generateAppID(applianceID), false));
                 
             }
             
             if (menuInput.equalsIgnoreCase("D")) {
            	 
            	 
            	 System.out.println("Enter the appliance ID:");
                 String delApp = scnr.next();
                 
                 for (int i = 0; i < applianceList.size(); i++) {
                	 if (applianceList.get(i).getAppID().equals(delApp)) {
                		 applianceList.remove(i);
                		 break;
                	 }
                 }
             }
             
             if (menuInput.equalsIgnoreCase("L")) {
            	 for (int i = 0; i < applianceList.size(); i++) {
            		 
            		 System.out.println( 
            		 applianceList.get(i).getLocationID()
            		 + ","
            		 + applianceList.get(i).getDescription()
            		 + ","
            		 + applianceList.get(i).getOnWattage()
            		 + ","
            		 + applianceList.get(i).getOnProbability()
            		 + ","
            		 + applianceList.get(i).getSmartStatus()
            		 + ","
            		 + applianceList.get(i).getPercentPowerReduction()
            		 + ",APPLIANCE ID: "
            		 + applianceList.get(i).getAppID()
            		);
            		 
            		 
            	 }
             }
             
             if (menuInput.equalsIgnoreCase("F")){//Read and add appliances from File
        		 
        		 System.out.println("Enter the name of the file:");
        		 String userFileName = scnr.next();
        		 addAppliancesFromFile (applianceList, applianceID, userFileName);
        	             
        	 } 

             if(menuInput.equalsIgnoreCase("Q")) { //Quit Program
            	 System.exit(0);
             }
             

             
        } // end of menu while loop
  
        ArrayList<Appliance> originalApplianceList = new ArrayList<>();
        
        duplicateArrayList(applianceList, originalApplianceList);
        
        
        ArrayList<Location> allLocations = new ArrayList<>();
        appliancesPerLocation(applianceList, allLocations);
        
        
        
        // TIMESTEP LOOP
        for (int t = 1; t < timeSteps + 1; t++) {
        	
        	for (int i = 0; i < applianceList.size(); i++) {
            	Appliance originalAppliance = new Appliance(originalApplianceList.get(i).getLocationID(),
    					originalApplianceList.get(i).getDescription(),
    					originalApplianceList.get(i).getOnWattage(),
    					originalApplianceList.get(i).getOnProbability(), 
    					originalApplianceList.get(i).getSmartStatus(), 
    					originalApplianceList.get(i).getPercentPowerReduction(),
    					originalApplianceList.get(i).getAppID(),
    					originalApplianceList.get(i).getOnStatus());
            			applianceList.set(i, originalAppliance);
            }
        	
        	
        	
        	ArrayList<String> affectedApp = new ArrayList<>();
        	
        	
        	// Using on probability to decide if a device will be on each timestep
        	applianceOnOrOff(applianceList);
        
	        // Sort appliances in descending order based off wattage used when ON
	        Collections.sort(applianceList, new Comparator<Appliance>(){
	        	public int compare(Appliance a1, Appliance a2) {
	        		return Integer.valueOf(a2.onWattage).compareTo(a1.onWattage);
	        	}
	        });
	        
	        
	        
	        
	        
	        lowCount = 0;
	        ArrayList<String> affectedLocations = new ArrayList<>();
	        affectedLocations.add(" ");
	        
	        
	        // setting smart appliances to low if allowed wattage is exceeded
	        if (calcTotalPowerUsage(applianceList) > totalAllowedWattage){
	        	for(int k = 0; k < applianceList.size(); k++) {
	        		
	        		
	        		if (applianceList.get(k).getSmartStatus() && applianceList.get(k).getOnStatus()) {
	        			applianceToLow(applianceList, k);
	        			
	        			for (int i = 0; i < allLocations.size(); i++) {
	        				if (applianceList.get(k).getLocationID().equals(allLocations.get(i).getlocID())) {
	        					incrementAffectedLocation(allLocations, i);
	        				}
	        			}
	        			
	        			lowCount++;
	        			
	        			if (affectedApp.indexOf(applianceList.get(k).getAppID()) == -1) {
	        				affectedApp.add(applianceList.get(k).getAppID());
	        			}
	        	        
	        	        		
	        	       	for(int q = 0; q < affectedLocations.size(); q++) {
	        	       		
	        	       		if (affectedLocations.get(0).equals(" ")) {
		        	       		affectedLocations.remove(0);

	        	       		}
	        	       		
	        	       		
	        	        	if (affectedLocations.indexOf(applianceList.get(k).getLocationID()) == -1) {
	        	        		
	        	        			affectedLocations.add(applianceList.get(k).getLocationID());
	        	        	}
	        	        }
	        	        	
	        	        
	                
	        			
	        		}
	        		if(calcTotalPowerUsage(applianceList) <= totalAllowedWattage) {
	        			break;
	        		}
	        	}
	        }
	        

	        // brown out if total allowed wattage is still exceeded
	        
	        
	        ArrayList<Location> locArray = new ArrayList<>();
	        
	        
        	appliancesPerLocation(applianceList, locArray);// counts appliances per location and sorts locations in ascending order

	        
	        
	        if (calcTotalPowerUsage(applianceList) > totalAllowedWattage) {
	        	
	
		        
		        brownCount = 0;
		        
		        
		        for (int i = 0; i < locArray.size(); i++) {
		        	
		        	for (int j = 0; j < applianceList.size(); j++) {
		        		
		        		if(applianceList.get(j).getLocationID().equals(locArray.get(i).getlocID())) {
		        			brownOutAppliance(applianceList,j);
		        			
		        			
		        			for (int b = 0; b < allLocations.size(); b++) {
		        				if (applianceList.get(j).getLocationID().equals(allLocations.get(b).getlocID())) {
		        					incrementAffectedLocation(allLocations, b);
		        				}
		        			}
		        			
		        			if (affectedApp.indexOf(applianceList.get(j).getAppID()) == -1) {
		        				affectedApp.add(applianceList.get(j).getAppID());
		        			}
		        		}
		        		
		        	}
	
		            
		            brownCount++;
		            
		            if (affectedLocations.get(0).equals(" ")) {
		            	affectedLocations.remove(0);
		            }
		            
		            if (affectedLocations.indexOf(locArray.get(i).getlocID()) == -1) {
		            	affectedLocations.add(locArray.get(i).getlocID());
		            	
		            }
		            
		            
		            
		            if(calcTotalPowerUsage(applianceList) <= totalAllowedWattage) {
		    			break;
		    		}

		        }
  
	        } // end of brown out if statement
	   
	        
	        


	        int uniqueLocationsAffected = affectedLocations.size();
	        
	        if (lowCount == 0 && brownCount == 0) {
	        	uniqueLocationsAffected = 0;
	        }
	        
	        
	        System.out.println("Timestep " + t + " Report");
	        
	        System.out.println("Total appliances set to low: " + lowCount);
	        
	        System.out.println("Total browned out locations: " + brownCount);
	        
	        System.out.println("Total effected locations: " + uniqueLocationsAffected);

	        
	   
	        
	        
	        System.out.println();
	        
	        
	        
	 
	        
	        Collections.sort(allLocations, new Comparator<Location>(){
	        	public int compare(Location a1, Location a2) {
	        		return Integer.valueOf(a2.affectedCounter).compareTo(a1.affectedCounter);
	        	}
	        });
	        
	      
	        
	        maxEffLoc = allLocations.get(0).getlocID();
	        
	        if (lowCount==0 && brownCount==0) {
	        	maxEffLoc = "None";
	        }
	        
	        
	        
	        summaryReport(uniqueLocationsAffected, t, maxEffLoc);
	        
	        
	        output += "Time Step: " + t + "\n" + "Affected Appliances \n";
	        
	        for (int i = 0; i < affectedApp.size(); i++) {
	        	for (int j = 0; j < originalApplianceList.size(); j++) {
	        		if (originalApplianceList.get(j).getAppID().equals(affectedApp.get(i))) {
	        			output += "\n"+ affectedApp.get(i) + ","
	    	        			+ originalApplianceList.get(j).getLocationID() + ","
	    	        			+ originalApplianceList.get(j).getDescription() + ","
	    	        			+ originalApplianceList.get(j).getOnWattage() + ","
	    	        			+ originalApplianceList.get(j).getOnProbability() + ","
	    	        			+ originalApplianceList.get(j).getSmartStatus() + ","
	    	        			+ originalApplianceList.get(j).getPercentPowerReduction()
	    	        			;
	    	        	
	        		}
	        	}
	        }
	        
	        
	        output += "\nAffected Locations\n";
	        
	        for (int i = 0; i < affectedLocations.size(); i++) {
	        	output += affectedLocations.get(i) + "\n";
	        }
	        
	        output += "\n";
	        
        }
        
       
       fileReport(output);
 
        
       System.out.println(summary_Report);
       
       System.out.println("Max Effected Location Is: " + maxEffLoc);
       
       
 

    } // end of main
    
    
    
    
    
    public static void duplicateArrayList(ArrayList<Appliance> applianceList, ArrayList<Appliance> originalApplianceList) {
    	for (int i = 0; i < applianceList.size(); i++) {
        	Appliance originalAppliance = new Appliance(applianceList.get(i).getLocationID(),
					applianceList.get(i).getDescription(),
					applianceList.get(i).getOnWattage(),
					applianceList.get(i).getOnProbability(), 
					applianceList.get(i).getSmartStatus(), 
					applianceList.get(i).getPercentPowerReduction(),
					applianceList.get(i).getAppID(),
					applianceList.get(i).getOnStatus());
					
        			originalAppliance.setOnStatus();			        			

        			originalApplianceList.add(originalAppliance);
        }
    }
    
    public static void fileReport(String output) throws FileNotFoundException {
    	File outputFile = new File("output.txt");
    	
    	PrintWriter printWriter = new PrintWriter(outputFile);
    	
    	printWriter.print(output);
    	
    	printWriter.close();
    }
    
    
    
    
    
    public static String generateAppID(ArrayList<String> applianceID) {
		Random rand = new Random();
        int genNum = rand.nextInt(1000000);
        
        while(applianceID.indexOf(String.valueOf(genNum)) != -1) { // checks if appliance ID exists before assigning
        	
        	genNum = rand.nextInt(1000000);
        	
        }
        applianceID.add(String.valueOf(genNum));
        return String.valueOf(genNum);
	}
	
	public static int calcTotalPowerUsage(ArrayList<Appliance> appliances) {
		int totalPowerConsumption = 0;
        for (int j = 0; j < appliances.size(); j++) {
        	if (appliances.get(j).getOnStatus()) {
        		totalPowerConsumption += appliances.get(j).getOnWattage();
        	}
        	
        }
        return totalPowerConsumption;
	}
	
	public static void addAppliancesFromFile(ArrayList<Appliance> applianceList, ArrayList<String> applianceID, String userFileName) {
		try{  
            File file = new File(userFileName);
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNextLine()){
                String currentLine = inputFile.nextLine();
                String[] applianceInfo = currentLine.split(",");
                String locationID = applianceInfo[0];
                String description = applianceInfo[1];
                int onWatt = Integer.parseInt(applianceInfo[2]);
                double probabilityOn = Double.parseDouble(applianceInfo[3]);
                boolean isSmart = Boolean.parseBoolean(applianceInfo[4]);
                double percentPowerReduction = Double.parseDouble(applianceInfo[5]);



                generateAppID(applianceID);





                Appliance currentAppliance = new Appliance(locationID, description, onWatt, probabilityOn, isSmart, percentPowerReduction, generateAppID(applianceID), false);
                currentAppliance.setOnStatus(); 
                applianceList.add(currentAppliance);
            }
            inputFile.close();
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
        }
	}
	
	
	public static void applianceOnOrOff(ArrayList<Appliance> applianceList) {
    	for(int k = 0; k < applianceList.size(); k++) {
    		
    		
			Appliance timestepAppliance = new Appliance(applianceList.get(k).getLocationID(),
						        					applianceList.get(k).getDescription(),
						        					applianceList.get(k).getOnWattage(),
						        					applianceList.get(k).getOnProbability(), 
						        					applianceList.get(k).getSmartStatus(), 
						        					applianceList.get(k).getPercentPowerReduction(),
						        					applianceList.get(k).getAppID(),
													applianceList.get(k).getOnStatus());
													
			timestepAppliance.setOnStatus();			        			
			
			applianceList.set(k,timestepAppliance);
			
    	}
	}
	
	public static void applianceToLow(ArrayList<Appliance> applianceList, int indexOfApp) {
		Appliance lowAppliance = new Appliance(applianceList.get(indexOfApp).getLocationID(),
				applianceList.get(indexOfApp).getDescription(),
				applianceList.get(indexOfApp).getOnWattage(),
				applianceList.get(indexOfApp).getOnProbability(), 
				applianceList.get(indexOfApp).getSmartStatus(), 
				applianceList.get(indexOfApp).getPercentPowerReduction(),
				applianceList.get(indexOfApp).getAppID(),
				applianceList.get(indexOfApp).getOnStatus());
				
				lowAppliance.setLowStatus();			        			

				applianceList.set(indexOfApp,lowAppliance);
	}
	
	public static void brownOutAppliance(ArrayList<Appliance> applianceList, int indexOfApp) {
	Appliance brownApp = new Appliance(applianceList.get(indexOfApp).getLocationID(),
			applianceList.get(indexOfApp).getDescription(),
			applianceList.get(indexOfApp).getOnWattage(),
			applianceList.get(indexOfApp).getOnProbability(), 
			applianceList.get(indexOfApp).getSmartStatus(), 
			applianceList.get(indexOfApp).getPercentPowerReduction(),
			applianceList.get(indexOfApp).getAppID(),
			false); 			        			
			applianceList.set(indexOfApp,brownApp);
	}
	
	
	public static void appliancesPerLocation(ArrayList<Appliance> applianceList, ArrayList<Location> locArray) {// counts appliances per locations and sorts locations in 
																													//ascending order based off appliance count
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
        for (int i = 0; i < locationList.size(); i++) {
        	Location loc = new Location(locationList.get(i), locationCounterList.get(i), 0);
        	locArray.add(loc);
        }
        
        
        Collections.sort(locArray, new Comparator<Location>(){
        	public int compare(Location a1, Location a2) {
        		return Integer.valueOf(a1.numApp).compareTo(a2.numApp);
        	}
        });
	}
    
    
    public static void summaryReport(int uniqueLocations, int timeStep, String maxEffLoc) {
    	
    	summary_Report += "TimeStep " + timeStep + ": " + "Total number of locations affected is " + uniqueLocations + "\n";
    }
    
    public static void printStuff(ArrayList<Appliance> applianceList) {
    	for (int i = 0; i < applianceList.size(); i++ ) {
    		System.out.println(applianceList.get(i).getLocationID() + " " + applianceList.get(i).getDescription() + " " + applianceList.get(i).getOnWattage());
    	}
    }
    
    
    public static void incrementAffectedLocation(ArrayList<Location> locations, int index) {
    	Location location = new Location(locations.get(index).getlocID(), locations.get(index).getNumApp(), locations.get(index).getAffectedCounter());
    	location.incrementAffectedCounter();
    	
    	locations.set(index, location);
    }

} // end of PowerGrid SimulationClass