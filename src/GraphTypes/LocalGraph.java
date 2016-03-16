package GraphTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import MainSimulation.*;

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
		this.localRecovered = 0;
		
		for(int i = 0; i < limit; i++){
			Person p = people.remove(people.size() - 1);
			if(p.getStatus() == Person.Status.INFECTED){
				this.localInfected++;
			}
			this.nodeList.add(p);
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
		Random rnd = new Random();
		for(Person p: this.nodeList){
			if(p.getStatus() == Person.Status.RECOVERED) continue;
			if(p.getStatus() == Person.Status.INFECTED){
				double prob = rnd.nextDouble();
				if(prob < 0.05){
					this.localInfected--;
					this.localRecovered++;
					p.setStatus(Person.Status.RECOVERED);
				}
				continue;
			}
			for(Link<Person> l: p.getNeighbors()){
				double prob = rnd.nextDouble();
				if(((Person)(l.getNeighbor())).getStatus() == Person.Status.INFECTED && prob < 0.2){
					this.localSusceptible--;
					this.localInfected++;
					p.setStatus(Person.Status.INFECTED);
				}
			}
		}
	}
	
}
