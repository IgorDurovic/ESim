package MainSimulation;

public abstract class Person extends Node{
	
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
