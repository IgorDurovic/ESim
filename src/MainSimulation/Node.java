package MainSimulation;

import java.util.ArrayList;

public abstract class Node {

	protected int localPopulation;
	protected int localSusceptible;
	protected int localInfected;
	protected int localRecovered;
	
	protected String name;
	
	protected ArrayList<Node> neighbors;
	
	public Node(String s){
		name = s;
		neighbors = new ArrayList<Node>();
	}
	
	public boolean equals(Object o){
		if(o instanceof Node){
			return ((Node) o).name.equals(this.name);
		}
		
		return false;
	}
	
}
