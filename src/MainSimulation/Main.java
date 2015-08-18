package MainSimulation;
import java.util.ArrayList;


import processing.core.*;

public class Main extends PApplet{
	
	public final static int cellDim = 25;
	
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
	
	public WorldGraph world = new WorldGraph();
	public WorldGraph tempWorld = new WorldGraph();
	
	public int lastTime; //saves the time of the most recent cycle.
	
	public void setup(){
		//call size(int width, int height) to set dimensions. Default is 100px by 100px
		
		stroke(48);
		
	}
	
	public void draw(){
		
	}
	
	public static void main(String[] args){
		
	}
}
