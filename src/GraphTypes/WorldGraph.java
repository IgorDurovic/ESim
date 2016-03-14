package GraphTypes;

import java.util.ArrayList;
import java.util.Random;

import GraphTypes.LocalGraph;
import GraphTypes.WorldGraph;

import MainSimulation.AbstractGraph;
import MainSimulation.Link;
import MainSimulation.Node;
import MainSimulation.Person;

public class WorldGraph extends AbstractGraph<LocalGraph>{
	
	public WorldGraph(String s, double e){
		super(s, e);
	}
	
	public void generateRandom(ArrayList<Person> people, int limit){
		Random rnd = new Random();
		this.localPopulation = limit;
		this.localSusceptible = limit;
		
		for(int i = 0; i < limit/1000; i++){
			LocalGraph temp = new LocalGraph(i + "", 0.01);
			temp.generateRandom(people, 1000);
			this.nodeList.add(temp);
		}
		
		for(Node n: this.nodeList){
			for(int i = 0; i < 2; i++){
				int r = rnd.nextInt(this.nodeList.size());
				while(n.getNeighbors().contains(new Link<LocalGraph>(1, this.nodeList.get(r)))){
					r = rnd.nextInt(this.nodeList.size());
				}
				n.getNeighbors().add(new Link<LocalGraph>(1, this.nodeList.get(r)));
			}
		}
	}

	@Override
	public void movement() {
		ArrayList<Person> emmigrants;
		
		for(LocalGraph lg: this.nodeList){
			int pop = lg.getLocalPopulation();
			int emmigration = (int)(pop * lg.geteRate());
			emmigrants = new ArrayList<>();
			
			for(int i = 0; i < emmigration; i++){
				if(lg.getNodeList().isEmpty()) break;
				Person p = lg.getNodeList().remove(lg.getNodeList().size() - 1);
				for(Link<Person> l: p.getNeighbors()){
					int index = lg.getNodeList().indexOf(l.getNeighbor());
					if(index == -1) continue;
					lg.getNodeList().get(index).getNeighbors().remove(p); //lol
				}
				
				p.getNeighbors().clear();
				//System.out.println(p.getNeighbors().isEmpty());
				emmigrants.add(p);
			}
			
			double totalCapacity = 0;
			for(Link<Person> l: lg.getNeighbors()){
				totalCapacity += l.getWeight();
			}
			
			int size = emmigrants.size();
			for(Link<Person> l: lg.getNeighbors()){
				int count = (int)Math.ceil(l.getWeight() / totalCapacity * size);
				for(int i = 0; i < count && !emmigrants.isEmpty(); i++){
					Person emmigrant = emmigrants.remove(emmigrants.size() - 1);
					((LocalGraph) l.getNeighbor()).getImmigrants().add(emmigrant);
				}
			}
			
			lg.movement();
		}
	}
}
