package MainSimulation;

public abstract class Person extends Node{
	
	public Person(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	public enum Gender{
		MALE, FEMALE;
	}
	
	public enum Race{
		WHITE, BLACK, NATIVE, PACIFIC, ASIAN, MIX;
	}
	
	private int age;
	private Gender gender;
	private Race race;
	
}
