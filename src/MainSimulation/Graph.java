package MainSimulation;

import java.util.ArrayList;

public abstract class Graph<T extends Node> extends Node{
	
	protected int max;
	protected int nodes;
	protected int edges;
	
	protected ArrayList<T> nodeList;
	protected ArrayList<Edge<T>> edgeList;
	
	public Object[] toArray(){
		
		return this.nodeList.toArray();
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getNodes() {
		return nodes;
	}

	public void setNodes(int nodes) {
		this.nodes = nodes;
	}

	public int getEdges() {
		return edges;
	}

	public void setEdges(int edges) {
		this.edges = edges;
	}

	public ArrayList<T> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<T> nodeList) {
		this.nodeList = nodeList;
	}

	public ArrayList<Edge<T>> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(ArrayList<Edge<T>> edgeList) {
		this.edgeList = edgeList;
	}
	
}
