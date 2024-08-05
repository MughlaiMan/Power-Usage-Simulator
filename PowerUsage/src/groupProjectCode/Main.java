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



    //locationID, description, onWatt, probabilityOn, isSmart, percentPowerReduction);


//public class Smart extends Appliances{


//public class Regular extends Appliances {

public class Main {

	public static String summary_Report = "";

	
	
	public static String generateAppID(ArrayList<String> applianceID) {
		Random rand = new Random();
        
        
        
        int genNum = rand.nextInt(1000000);
        
        while(applianceID.indexOf(String.valueOf(genNum)) != -1) {
        	
        	genNum = rand.nextInt(1000000);
        	
        }
        
        return String.valueOf(genNum);
	}
	
	
	
	
	
    public static void main(String[] args) throws IOException {
    
    	
    	int lowCount = 0;
    	int brownCount = 0;
    	
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the total allowed wattage.");
        // add input validation
        int totalAllowedWattage = scnr.nextInt();
        // add input validation
        System.out.println("Enter the amount of timesteps.");
        int timeSteps = scnr.nextInt();
        
        System.out.println("Enter the name of the input file");

        String nameInputFile = scnr.next();
        
        
        
        // Takes appliances from input file
        ArrayList<Appliance> applianceList = new ArrayList<>();
        
        ArrayList<String> applianceID = new ArrayList<>();
        
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
                
                
                
            	applianceID.add(generateAppID(applianceID));

                
                
                
                
                Appliance currentAppliance = new Appliance(locationID, description, onWatt, probabilityOn, isSmart, percentPowerReduction, generateAppID(applianceID), false);
                currentAppliance.setOnStatus(); 
                applianceList.add(currentAppliance);
            }
        }
        
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        
     
        
        
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
             
             
             
             if(menuInput.equals("Q")) {
            	 System.exit(0);
             }
            	
             
             
             
             if (menuInput.equals("F")){


            	 try{  
            		 
            		 System.out.println("Enter the name of the file:");
            	  
            	             String userFileName = scnr.next();
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



            	                 applianceID.add(generateAppID(applianceID));





            	                 Appliance currentAppliance = new Appliance(locationID, description, onWatt, probabilityOn, isSmart, percentPowerReduction, generateAppID(applianceID), false);
            	                 currentAppliance.setOnStatus(); 
            	                 applianceList.add(currentAppliance);
            	             }
            	         }

            	         catch (FileNotFoundException e){
            	             e.printStackTrace();
            	         }
            	 } 

             
             
             
             
             
             
             
             if (menuInput.equals("D")) {
            	 
            	 
            	 System.out.println("Enter the appliance ID:");
                 String delApp = scnr.next();
                 
                 for (int i = 0; i < applianceList.size(); i++) {
                	 if (applianceList.get(i).getAppID().equals(delApp)) {
                		 applianceList.remove(i);
                		 break;
                	 }
                 }

            	 
            	 
             }
             
             
             if (menuInput.equals("L")) {
            	 for (int i = 0; i < applianceList.size(); i++) {
            		 
            		 System.out.println("Location ID: " 
            		 + applianceList.get(i).getLocationID()
            		 + ", "
            		 + applianceList.get(i).getDescription()
            		 + ", On Wattage: "
            		 + applianceList.get(i).getOnWattage()
            		 + ", Probability of Being On "
            		 + applianceList.get(i).getOnProbability()
            		 + ", Is Smart?: "
            		 + applianceList.get(i).getSmartStatus()
            		 + ", Percent Power Reduction "
            		 + applianceList.get(i).getPercentPowerReduction()
            		 + ", Appliance ID: "
            		 + applianceList.get(i).getAppID()
            		);
            		 
            		 
            	 }
             }
             
             
             
             if(menuInput.equals("A")) {
            	
                
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
             
        } // end of while loop
        
        
        
        
        
        
        // Timestep Algorithm Part 1: 
        // 
        
        
        
 
        for (int t = 1; t < timeSteps + 1; t++) {
        	
        	for(int k = 0; k < applianceList.size(); k++) {
        		
        		
        		if (applianceList.get(k).getSmartStatus()) {
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
        
        
        
	        // Sort appliances in descending order 
	        Collections.sort(applianceList, new Comparator<Appliance>(){
	        	public int compare(Appliance a1, Appliance a2) {
	        		return Integer.valueOf(a2.onWattage).compareTo(a1.onWattage);
	        	}
	        });
	        
	        
	        //printStuff(applianceList);
	        
	        int totalPowerConsumption = 0;
	        for (int j = 0; j < applianceList.size(); j++) {
	        	if (applianceList.get(j).getOnStatus()) {
	        		totalPowerConsumption += applianceList.get(j).getOnWattage();
	        	}
	        	
	        }
	        //test
	        /*
	        for (int a = 0; a < 5; a++ ) {
	        	System.out.print(applianceList.get(a).getSmartStatus() + " ");
	        	System.out.println(applianceList.get(a).getOnWattage());
	        }
	        */
	        
	        
	        lowCount = 0;
	        
	
	        // setting smart appliances to low if allowed wattage is exceeded
	        if (totalPowerConsumption > totalAllowedWattage){
	        	for(int k = 0; k < applianceList.size(); k++) {
	        		
	        		
	        		if (applianceList.get(k).getSmartStatus()) {
	        			Appliance lowAppliance = new Appliance(applianceList.get(k).getLocationID(),
									        					applianceList.get(k).getDescription(),
									        					applianceList.get(k).getOnWattage(),
									        					applianceList.get(k).getOnProbability(), 
									        					applianceList.get(k).getSmartStatus(), 
									        					applianceList.get(k).getPercentPowerReduction(),
									        					applianceList.get(k).getAppID(),
									        					applianceList.get(k).getOnStatus());
	        													
						lowAppliance.setLowStatus();			        			
	        			
	        			applianceList.set(k,lowAppliance);
	        			
	        			lowCount++;
	        			
	        		}
	        		if(totalPowerConsumption <= totalAllowedWattage) {
	        			break;
	        		}
	        	}
	        }
	        
	        // brown out if total allowed wattage is still exceeded
	        
	        
	        ArrayList<location> locArray = new ArrayList<>();
	        ArrayList<String> effectedArray = new ArrayList<>();
	        
	        if (totalPowerConsumption > totalAllowedWattage) {
	        	
	        	
	        	
	        	
		        
		        /*
		        //test after
		        System.out.println("\nAfter");
		        for (int a = 0; a < 5; a++ ) {
		        	System.out.print(applianceList.get(a).getSmartStatus() + " ");
		        	System.out.println(applianceList.get(a).getOnWattage());
		        }
		        */
		        
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
		        
		        /*
		        for (int a = 0; a <10 ; a++) {
		        	System.out.println(locationList.get(a) + ": "+ locationCounterList.get(a));
		        }
		        */
		        
		        System.out.println();
		        
		        

		
		        
		        for (int i = 0; i < locationList.size(); i++) {
		        	location loc = new location(locationList.get(i), locationCounterList.get(i));
		        	locArray.add(loc);
		        }
		        
		        
		        Collections.sort(locArray, new Comparator<location>(){
		        	public int compare(location a1, location a2) {
		        		return Integer.valueOf(a1.numApp).compareTo(a2.numApp);
		        	}
		        });
		        
		        /*
		        for (int i = 0; i < locArray.size(); i++) {
		        	System.out.println(locArray.get(i).getlocID() + ": " + locArray.get(i).getNumApp());
		        }
		        */
		        
		        System.out.println();
		        
		        totalPowerConsumption = 0;
		        
		        
		        
		        brownCount = 0;
		        
		        
		        for (int i = 0; i < locArray.size(); i++) {
		        	
		        	for (int j = 0; j < applianceList.size(); j++) {
		        		
		        		if(applianceList.get(j).getLocationID().equals(locArray.get(i).getlocID())) {
		        			Appliance brownApp = new Appliance(applianceList.get(j).getLocationID(),
		        					applianceList.get(j).getDescription(),
		        					applianceList.get(j).getOnWattage(),
		        					applianceList.get(j).getOnProbability(), 
		        					applianceList.get(j).getSmartStatus(), 
		        					applianceList.get(j).getPercentPowerReduction(),
		        					applianceList.get(j).getAppID(),
		        					applianceList.get(i).getOnStatus());
		        					brownApp.setOnStatus(false); 			        			
		        					applianceList.set(j,brownApp);
		        		}
		        		
		        		
		        	}
		        	
		        	
		        	
		            for (int j = 0; j < applianceList.size(); j++) {
		            	if (applianceList.get(j).getOnStatus()) {
		            		totalPowerConsumption += applianceList.get(j).getOnWattage();
		            	}
		            	
		            }
		            
		            brownCount++;
		            
		            effectedArray.add(locArray.get(i).getlocID());
		            
		            
		            if(totalPowerConsumption <= totalAllowedWattage) {
		    			break;
		    		}
		           
		            
		        }
		        
		        
		        
		        /*
		        for (int i = 0; i < locArray.size(); i++) {
		        	System.out.println(locArray.get(i).getlocID() + ": " + locArray.get(i).getNumApp());
		        }
		        
		        for (int i = 0; i < applianceList.size(); i++) {
		        	System.out.println(applianceList.get(i).getLocationID() + " " + applianceList.get(i).getOnStatus());
		        }
		        */
	        
	        } // end of brown if statement
	        
	     
	        
	        
	        
	        int smartAppCounter = 0;
	        
	        for (int i = 0; i < applianceList.size(); i++) {
	        	if (applianceList.get(i).getSmartStatus()) {
	        		smartAppCounter++;
	        		effectedArray.add(applianceList.get(i).getLocationID());
	        	}
	        }
	        
	        
	        
		    ArrayList <String> locationArray = new ArrayList<>();
	
	        
	        for (int i = 0; i < effectedArray.size(); i++) {
		         
		         for (int j = i + 1; j < effectedArray.size(); j++) {
		             if (effectedArray.get(j).equals(effectedArray.get(i))) {
		                 effectedArray.remove(j);
		             }
		         }
	
		         locationArray.add(effectedArray.get(i));
		         //System.out.println(locationArray.get(i));
		     }
	        
	        int uniqueLoc = locationArray.size();
	        
	        
	        System.out.println("Timestep " + t + " Report");
	        
	        System.out.println("Total appliances set to low: " + lowCount);
	        
	        System.out.println("Total browned out locations: " + brownCount);
	        
	        
	        System.out.println("After: ");
	        //printStuff(applianceList);
	        
	        summaryReport(uniqueLoc, t);
	        
	        
	        
	        System.out.println("Total effected locations: " + uniqueLoc);
	        
	        System.out.println();
        
        }
        
        System.out.println(summary_Report);
        
        
        /*
        for (int i = 0; i < applianceList.size(); i++) {
        	System.out.println(applianceList.get(i).getLocationID() + " " + applianceList.get(i).getOnWattage() + " " + applianceList.get(i).getAppID());
        }
        */
        
        
        
       
      
        
        
        
        // for testing
        /*
        for (int i = 0; i < 60; i++) {
        	System.out.println(locArray.get(i).getlocID() + ": " + locArray.get(i).getNumApp());
        }
        */
        
        
       
        
       
     
        
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
    
    
    
    public static void summaryReport(int uniqueLocations, int timeStep) {
    	
    	summary_Report += "TimeStep " + timeStep + ": " + "Total number of locations affected is " + uniqueLocations + "\n";
    }
    
    public static void printStuff(ArrayList<Appliance> applianceList) {
    	for (int i = 0; i < applianceList.size(); i++ ) {
    		System.out.println(applianceList.get(i).getLocationID() + " " + applianceList.get(i).getDescription() + " " + applianceList.get(i).getOnWattage());
    	}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} // end of PowerGrid SimulationClass