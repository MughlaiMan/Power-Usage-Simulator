Power Simulation Instructions 

1. Add your txt or csv file which holds all of the appliances and their info, such as the app.txt
into the project folder. The file may already be in the project folder
This file should have all the appliance details separated by commas- in the following format:
LocationID,ApplianceDescription,OnWattage,ProbabilityOfBeingOn,isSmart(represented by true or false),percentPowerReduction(double between zero and one)

2. The Main.java program needs the Appliance.java and Location.java classes to run.

3. Run Main.java
  
4. Enter the inputs requested by the program. Allowed wattage (int), timesteps (int), and the name of the input file.

5. The user is prompted to select an option from the menu. Follow the directions on screen and select your desired option. 

6. When the simulation is complete there will be some stats outputted to the screen and other stats will be outputted to "output.txt" in the project folder.
   Note: Every time the simulation is ran the output.txt file will be replaced. 

