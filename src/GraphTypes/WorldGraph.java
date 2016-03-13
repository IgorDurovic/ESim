package GraphTypes;

import java.util.Random;

import GraphTypes.LocalGraph;
import GraphTypes.WorldGraph;

import MainSimulation.AbstractGraph;
import MainSimulation.Link;
import MainSimulation.Node;

public class WorldGraph extends AbstractGraph<LocalGraph>{
	
	public WorldGraph(String s, double e){
		super(s, e);
	}
	
	public void generateRandom(int limit){
		Random rnd = new Random();
		this.localPopulation = limit;
		this.localSusceptible = limit;
		
		for(int i = 0; i < limit/100; i++){
			LocalGraph temp = new LocalGraph(i + "", 0.01);
			temp.generateRandom(100);
			this.nodeList.add(temp);
		}
		
		for(Node n: this.nodeList){
			for(int i = 0; i < 2; i++){
				int r = rnd.nextInt(149);
				while(n.getNeighbors().contains(new Link(1, this.nodeList.get(r)))){
					r = rnd.nextInt(149);
				}
				n.getNeighbors().add(new Link(1, this.nodeList.get(r)));
			}
		}
	}

	@Override
	public void movement() {
		for(LocalGraph lg: this.nodeList){
			int pop = lg.getLocalPopulation();
		}
	}
}
