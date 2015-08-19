package MainSimulation;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import GraphTypes.WorldGraph;

public class Main{
	
	class Canvas extends JComponent{

		public void paint(Graphics g){
			
			List<Node> temp = world.getNodeList();
			
			for(int i = 0; i < width/cellDim; i++){
				for(int j = 0; j < height/cellDim; j++){
					g.drawRect(i * cellDim, j * cellDim, cellDim, cellDim);
				}
			}
		}
	}
	
	public final static int cellDim = 25, width = 100, height = 100;
	
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
		window.setBounds(500, 300, 100, 140);
		window.getContentPane().add(new Main().new Canvas());
		window.setVisible(true);
		
		setup();
	}
}
