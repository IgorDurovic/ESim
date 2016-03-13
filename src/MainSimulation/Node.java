package MainSimulation;

import java.util.ArrayList;

public abstract class Node {

	protected String name;
	
	protected ArrayList<Link> neighbors;
	
	public Node(String s){
		name = s;
		neighbors = new ArrayList<Link>();
	}
	
	public ArrayList<Link> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<Link> neighbors) {
		this.neighbors = neighbors;
	}

	public boolean equals(Object o){
		if(o instanceof Node){
			return ((Node) o).name.equals(this.name);
		}
		
		return false;
	}
	
}
