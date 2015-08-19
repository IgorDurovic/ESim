package MainSimulation;

import java.util.ArrayList;

public abstract class Node {

	protected int localPopulation;
	protected int localSusceptible;
	protected int localInfected;
	protected int localRecovered;
	
	protected ArrayList<Edge> neighbors;
	
}
