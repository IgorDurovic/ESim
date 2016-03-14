package MainSimulation;

public class Link<T extends Node> {
	
	private int weight;
	private T neighbor;
	
	public Link(int w, T n){
		weight = w;
		neighbor = n;
	}
	
	public boolean equals(Object o){
		if(o instanceof Link){
			if(((Link) o).neighbor.equals(this.neighbor) && ((Link) o).weight == this.weight){
				return true;
			}
		}
		
		return false;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(T neighbor) {
		this.neighbor = neighbor;
	}
	
	
}
