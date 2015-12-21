package MainSimulation;

import java.util.ArrayList;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import GraphTypes.WorldGraph;

public abstract class AbstractGraph<T extends Node> extends Node{
	
	protected int max;
	protected int nodes;
	protected int edges;
	
	protected ArrayList<T> nodeList;
	
	protected AbstractGraph(String s){
		super(s);
		nodeList = new ArrayList<T>();
	}
	
	public void display(){
		
		Graph graph = new SingleGraph("World");
		
		for(Node n: this.nodeList){
			graph.addNode(n.name);
		}
		
		for(Node from: this.nodeList){
			for(Node to: from.neighbors){
				try{
					graph.addEdge(from.name + " " + to.name, from.name, to.name);
				}
				catch(EdgeRejectedException e){
					continue;
				}
			}
		}
		
		graph.display();
	}
	
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

}
