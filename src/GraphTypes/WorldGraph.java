package GraphTypes;

import java.util.ArrayList;
import java.util.Random;

import GraphTypes.LocalGraph;
import GraphTypes.WorldGraph;

import MainSimulation.*;

public class WorldGraph extends AbstractGraph<LocalGraph>{
	
	public WorldGraph(String s, double e){
		super(s, e);
	}
	
	public void generateRandom(ArrayList<Person> people, int limit){
		Random rnd = new Random();
		this.localPopulation = limit;
		this.localSusceptible = limit;
		this.localRecovered = 0;
		
		for(int i = 0; i < limit/500; i++){
			LocalGraph temp = new LocalGraph(i + "", 0.1);
			temp.generateRandom(people, 500);
			this.localInfected += temp.getLocalInfected();
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
			int pop = lg.getNodeList().size();
			int emmigration = (int)(pop * lg.geteRate());
			emmigrants = new ArrayList<>();
			
			//System.out.print(lg.getNodeList().size() + " ");
			
			for(int i = 0; i < emmigration; i++){
				if(lg.getNodeList().isEmpty()) break;
				Person p = lg.getNodeList().remove(lg.getNodeList().size() - 1);
				if(p.getStatus() == Person.Status.INFECTED){
					lg.setLocalInfected(lg.getLocalInfected() - 1);
				}
				if(p.getStatus() == Person.Status.SUSCEPTIBLE){
					lg.setLocalSusceptible(lg.getLocalSusceptible() - 1);
				}
				if(p.getStatus() == Person.Status.RECOVERED){
					lg.setLocalRecovered(lg.getLocalRecovered() - 1);
				}
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
					LocalGraph to = ((LocalGraph) l.getNeighbor()); 
					if(emmigrant.getStatus() == Person.Status.INFECTED){
						to.setLocalInfected(to.getLocalInfected() + 1);
					}
					if(emmigrant.getStatus() == Person.Status.SUSCEPTIBLE){
						to.setLocalSusceptible(to.getLocalSusceptible() + 1);
					}
					if(emmigrant.getStatus() == Person.Status.RECOVERED){
						to.setLocalRecovered(to.getLocalRecovered() + 1);
					}
					to.getImmigrants().add(emmigrant);
				}
			}
			
			if(!emmigrants.isEmpty()){
				//System.out.println("here");
				lg.getImmigrants().addAll(emmigrants);
				emmigrants.clear();
			}
			
			//System.out.println(lg.getNodeList().size());
			
			lg.movement();
		}
	}
	
	@Override
	public void infection(){
		//System.out.println(this.localInfected);
		this.localInfected = 0;
		this.localRecovered = 0;
		this.localSusceptible = 0;
		for(LocalGraph lg: this.nodeList){
			lg.infection();
			this.localInfected += lg.getLocalInfected();
			this.localSusceptible += lg.getLocalSusceptible();
			this.localRecovered += lg.getLocalRecovered();
		}
	}
}
