package MainSimulation;

import java.util.Scanner;
import Pathogens.NonVectored.Bacteria.*;
import Pathogens.NonVectored.Virus.*;

public class Pathogen {
	private int incubationPeriod;
	private double mortalityRate;
	private boolean symptomatic;
	private boolean immunity;
	
	public Pathogen(int a, double b, boolean c, boolean d){
		incubationPeriod = a;
		mortalityRate = b;
		symptomatic = c;
		immunity = d;
	}
	
	public static Pathogen constructPathogen() throws Exception{
		
		Pathogen p; //pathogen to construct
		int ip; //incubation period
		double mr; //mortality rate
		boolean s, i; //symptomatic & immunity
		
		Scanner scn = new Scanner(System.in);
		
		System.out.println("IncubationPeriod: ");
		ip = scn.nextInt();
		
		System.out.println("Mortality Rate: ");
		mr = scn.nextDouble();
		
		System.out.println("Sympotic [0|1]: ");
		s = scn.nextBoolean();
		
		System.out.println("Potential Natural Immunity [0|1]: ");
		i = scn.nextBoolean();
		
		System.out.println("Mode of transmission: \n1. Airborne\n2. Body Fluid\n3. Food\n4. Water");
		int option = scn.nextInt();
		
		switch(option){
		case 1:
			System.out.println("Pathogen type: \n1. Bacteria\n2. Virus");
			option = scn.nextInt();
			
			if(option == 1){
				p = new AirborneBacteria(ip, mr, i, s);
			}
			else{
				if(option < 1 || option > 2){
					scn.close();
					throw new Exception();
				}
				else{
					p = new AirborneVirus(ip, mr, i, s);
				}
			}
			break;
		case 2:
			System.out.println("Pathogen type: \n1. Bacteria\n2. Virus");
			option = scn.nextInt();
			
			if(option == 1){
				p = new BodyFluidsBacteria(ip, mr, i, s);
			}
			else{
				if(option < 1 || option > 2){
					scn.close();
					throw new Exception();
				}
				else{
					p = new BodyFluidsVirus(ip, mr, i, s);
				}
			}
			break;
		case 3:
			System.out.println("Pathogen type: \n1. Bacteria\n2. Virus");
			option = scn.nextInt();
			
			if(option == 1){
				p = new FoodBacteria(ip, mr, i, s);
			}
			else{
				if(option < 1 || option > 2){
					scn.close();
					throw new Exception();
				}
				else{
					p = new FoodVirus(ip, mr, i, s);
				}
			}
			break;
		case 4:
			System.out.println("Pathogen type: \n1. Bacteria\n2. Virus");
			option = scn.nextInt();
			
			if(option == 1){
				p = new WaterBacteria(ip, mr, i, s);
			}
			else{
				if(option < 1 || option > 2){
					scn.close();
					throw new Exception();
				}
				else{
					p = new WaterVirus(ip, mr, i, s);
				}
			}
			break;
		default:
			scn.close();
			throw new Exception();
		}
		
		scn.close();
		
		return p;
	}
	
	
	public static Pathogen tType(int ip, double mr, boolean s, boolean i){
		
		return null;
	}
	
}
