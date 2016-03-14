package MainSimulation;

import java.util.ArrayList;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import GraphTypes.WorldGraph;

public abstract class AbstractGraph<T extends Node> extends Node{
	
	protected int max;
	
	protected int localPopulation;
	protected int localSusceptible;
	protected int localInfected;
	protected int localRecovered;
	
	protected double eRate;
	
	protected ArrayList<T> nodeList;

	protected AbstractGraph(String s, double e){
		super(s);
		eRate = e;
		nodeList = new ArrayList<T>();
	}
	
	public void display(){
		
		Graph graph = new SingleGraph("World");
		
		for(Node n: this.nodeList){
			graph.addNode(n.name);
		}
		
		for(Node from: this.nodeList){
			for(Link to: from.neighbors){
				try{
					graph.addEdge(from.name + " " + to.getNeighbor().name, from.name, to.getNeighbor().name);
				}
				catch(EdgeRejectedException e){
					continue;
				}
			}
			System.out.println();
		}
		
		graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
		graph.display();
	}
	
	public Object[] toArray(){
		
		return this.nodeList.toArray();
	}
	
	abstract public void generateRandom(ArrayList<Person> people, int limit);
	
	abstract public void movement();
	
	abstract public void infection();

	public int getLocalPopulation() {
		return localPopulation;
	}

	public void setLocalPopulation(int localPopulation) {
		this.localPopulation = localPopulation;
	}

	public double geteRate() {
		return eRate;
	}

	public void seteRate(double eRate) {
		this.eRate = eRate;
	}
	
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public ArrayList<T> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<T> nodeList) {
		this.nodeList = nodeList;
	}

}
