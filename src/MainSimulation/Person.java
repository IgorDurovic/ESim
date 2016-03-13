package MainSimulation;

import java.util.ArrayList;
import java.util.Random;

import People.*;

public abstract class Person extends Node{
	
	public enum Gender{
		MALE, FEMALE;
	}
	
	public enum Race{
		WHITE, BLACK, HISPANIC, ASIAN, MIX;
	}
	
	public static final double[] ageDistro = new double[]{0.0666, 0.1378, 0.209, 0.2788, 0.3448, 0.413, 0.4854
		, 0.5664, 0.6468, 0.7194, 0.7832, 0.8326, 0.8728, 0.9092, 0.9444, 0.9754, 0.9976, 1.019};
	
	public static final int[] ageValue = new int[]{2, 7, 12, 17, 22, 27, 32, 37, 42, 
		47, 52, 57, 62, 67, 72, 77, 82, 87};
	
	public static final double[] raceDistro = new double[]{0.62, 0.74, 0.92, 0.98, 1.00};
	
	public static final Race[] raceValue = new Race[]{Race.WHITE, Race.BLACK, Race.HISPANIC, Race.ASIAN, Race.MIX};
	
	private int age;
	private Gender gender;
	private Race race;
	
	private boolean immune;
	
	public Person(String s, Gender g, int a, Race r) {
		super(s);
		gender = g;
		age = a;
		race = r;
		immune = false;
	}
	
	public static ArrayList<Person> generatePopulation(int n){
		ArrayList<Person> temp = new ArrayList<>();
		Random rand = new Random();
		
		for(int i = 0; i < n; i++){
			Gender gender = rand.nextBoolean() ? Gender.MALE : Gender.FEMALE;
			Double d = rand.nextDouble();
			int age = -1;
			for(int j = 0; j < ageDistro.length; j++){
				if(ageDistro[j] > d){
					age = ageValue[j];
					break;
				}
			}
			
			d = rand.nextDouble();
			Race race = Race.WHITE;
			for(int j = 0; i < raceDistro.length; j++){
				if(raceDistro[j] > d){
					race = raceValue[j];
					break;
				}
			}
			
			temp.add(new SusceptiblePerson(i + "", gender, age, race));
		}
		
		return null;
	}
	
}
