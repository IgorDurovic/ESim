package MainSimulation;

import java.util.Random;

import GraphTypes.LocalGraph;
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
	
	public static void setup(){
		
	}
	
	public static void main(String[] args){
		world = new WorldGraph("World");
		tempWorld = new WorldGraph("TempWorld");
		Random rnd = new Random();
		
		for(int i = 0; i < 100; i++){
			world.nodeList.add(new LocalGraph(i+""));
		}
		
		for(Node n: world.nodeList){
			for(int i = 0; i < 3; i++){
				int r = rnd.nextInt(99);
				while(n.neighbors.contains(world.nodeList.get(r))){
					r = rnd.nextInt(99);
				}
				n.neighbors.add(world.nodeList.get(r));
			}
		}
		
		world.display();
	}
}
