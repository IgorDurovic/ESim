package MainSimulation;

public class Link {
	
	private int weight;
	private Node neighbor;
	
	public Link(int w, Node n){
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

	public void setNeighbor(Node neighbor) {
		this.neighbor = neighbor;
	}
	
	
}
