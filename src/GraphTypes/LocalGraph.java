package GraphTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import MainSimulation.*;
import People.SusceptiblePerson;

public class LocalGraph extends AbstractGraph<Person>{

	public enum Place{
		SCHOOL, OFFICE, HOSPITAL, OTHER
	}
	
	private Place type;
	
	public LocalGraph(String s, double e) {
		super(s, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateRandom(int limit) {
		Random rnd = new Random();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("ContactData.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		this.localSusceptible = limit;
		this.localPopulation = limit;
		
		for(int i = 0; i < limit; i++){
			this.nodeList.add(new SusceptiblePerson(i + ""));
		}
		
		for(Node n: this.nodeList){
			for(int i = 0; i < 2; i++){
				int r = rnd.nextInt(149);
				while(n.getNeighbors().contains(new Link(1, this.nodeList.get(r)))){
					r = rnd.nextInt(149);
				}
				n.getNeighbors().add(new Link(1, this.nodeList.get(r)));
			}
		}
		
		try {
			br.close();
		} catch (IOException e) {
			return;
		}
	}

	@Override
	public void movement() {
		// TODO Auto-generated method stub
		
	}
	
	
}
