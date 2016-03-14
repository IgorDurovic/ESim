package GraphTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import MainSimulation.*;
import People.SusceptiblePerson;

public class LocalGraph extends AbstractGraph<Person>{
	
	public enum Place{
		SCHOOL, OFFICE, HOSPITAL, OTHER
	}
	
	private Place type;
	private ArrayList<Person> immigrants;
	
	public LocalGraph(String s, double e) {
		super(s, e);
		immigrants = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Person> getImmigrants() {
		return immigrants;
	}
	
	public void setImmigrants(ArrayList<Person> immigrants) {
		this.immigrants = immigrants;
	}
	
	@Override
	public void generateRandom(ArrayList<Person> people, int limit) {
		Random rnd = new Random();
		/*BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("ContactData.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}*/
		
		this.localSusceptible = limit;
		this.localPopulation = limit;
		
		for(int i = 0; i < limit; i++){
			this.nodeList.add(people.remove(people.size() - 1));
		}
		
		for(Node n: this.nodeList){
			for(int i = 0; i < 2; i++){
				int r = rnd.nextInt(this.nodeList.size());
				while(n.getNeighbors().contains(new Link<Person>(1, this.nodeList.get(r)))){
					r = rnd.nextInt(this.nodeList.size());
				}
				n.getNeighbors().add(new Link<Person>(1, this.nodeList.get(r)));
			}
		}
		
		/*try {
			br.close();
		} catch (IOException e) {
			return;
		}*/
	}
	
	@Override
	public void movement() {
		Random rnd = new Random();
		while(!this.immigrants.isEmpty()){
			Person p = this.immigrants.remove(this.immigrants.size() - 1);
			this.nodeList.add(p);
			for(int i = 0; i < 2; i++){
				int r = rnd.nextInt(this.nodeList.size());
				while(p.getNeighbors().contains(new Link<Person>(1, this.nodeList.get(r)))){
					r = rnd.nextInt(this.nodeList.size());
					//System.out.println(this.nodeList.size());
				}
				//System.out.println();
				p.getNeighbors().add(new Link<Person>(1, this.nodeList.get(r)));
			}
		}
	}

	@Override
	public void infection() {
		// TODO Auto-generated method stub
		
	}
	
}
