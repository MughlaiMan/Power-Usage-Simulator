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
        

        
        for (int i = 0; i < applianceList.size(); i++) {
        	
        	System.out.println(applianceList.get(i).getLocationID() + " " + applianceList.get(i).getOnWattage());
        	
        }
        

    } // end of main 

} // end of PowerGrid SimulationClass
