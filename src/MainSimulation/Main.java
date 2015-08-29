package MainSimulation;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import GraphTypes.WorldGraph;

public class Main{
	
	class Canvas extends JComponent{

		public void paint(Graphics g){
			
			int startX = 100;
			int radius = 300;
			
			//example graph construction, once the Graph class is implemented
			//the graph will be constructed from the world variable
			
			for(int i = 0; i < 50; i++){
				g.drawOval(radius + startX + (int)(radius * Math.cos(2 * Math.PI / 50 * i)), 
						radius + (int)(radius * Math.sin(2 * Math.PI / 50 * i)), 
						cellDim, cellDim);
				for(int j = 0; j < 5; j++){
					int rand = (int)(Math.random() * 50);
					g.drawLine(radius + startX + (int)(radius * Math.cos(2 * Math.PI / 50 * i)) + cellDim/2,
							radius + (int)(radius * Math.sin(2 * Math.PI / 50 * i)) + cellDim/2, 
							radius + startX + (int)(radius * Math.cos(2 * Math.PI / 50 * rand)) + cellDim/2,
							radius + (int)(radius * Math.sin(2 * Math.PI / 50 * rand)) + cellDim/2);
				}
			}
		}
	}
	
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
	
	public WorldGraph world = new WorldGraph();
	public WorldGraph tempWorld = new WorldGraph();
	
	public int lastTime; //saves the time of the most recent cycle.
	
	public static void setup(){
		
	}
	
	public static void main(String[] args){
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(500, 300, width, height);
		window.getContentPane().add(new Main().new Canvas());
		window.setVisible(true);
		
		setup();
	}
}
