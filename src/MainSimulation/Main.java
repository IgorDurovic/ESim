package MainSimulation;

import java.util.Scanner;

import GraphTypes.WorldGraph;

public class Main{
	
	public final static int cellDim = 25, width = 800, height = 800;
	
	/*	Keeping track of different demographics.
		The separate groups are based on the SIR model demographic distinction.
		At the beginning of each simulation the majority of the population is part of the susceptible group.
		After each cycle the groups are updated to account for the spread of the demographic.
		If an individual has been exposed to the pathogen for a specified period they are added to the recovered
		group. An individual can only be part of one group at any given time in the simulation.
	*/
	
	public int totalPopulation; //total amount of people in the scope of the simulation
								//updated for deaths, births, immigration, and emigration
	public int susceptible; 	
	public int infected;
	public int recovered;
	
	public int deaths; //keeps track of the death count throughout the simulation, used in data
					   //visualization after the simulation is complete.
	
	public static WorldGraph world;
	public static WorldGraph tempWorld;
	
	public int lastTime; //saves the time of the most recent cycle.
	
	public static void setup() throws Exception{
		world = new WorldGraph("World", 0);
		tempWorld = new WorldGraph("TempWorld", 0);
		
		world.generateRandom(100000);
		
		Pathogen mainPathogen = Pathogen.constructPathogen();
		startSim(mainPathogen);
			
	}
	
	public static void startSim(Pathogen p){
		for(int i = 0; i < 1000000; i++){
			movement(); //movement phase
			infection(); //infection phase;
		}
	}
	
	public static void movement(){
		world.movement();
	}
	
	public static void infection(){
		
	}
	
	public static void main(String[] args) throws Exception{
		
		setup();
		
		world.display();
	}
}
