package cscie55.hw3;

import cscie55.hw3.Elevator;
import java.util.*;

public class Passenger {
	int iPassID;
	int currentFloor;
	int destinationFloor;
	static final int UNDEFINED_FLOOR=-1;
	Elevator elevator;

	
	
	public Passenger(int pass){
		iPassID = pass;
//		currentFloor=1;
	
	}
	
	public int currentFloor(){
		
		return currentFloor;
	}
	
	public int destinationFloor(){
		
		return destinationFloor;
	}
	
	//set passenger currentFloor to newDestinationFloor
	public void waitForElevator(int newDestinationFloor){
		destinationFloor=newDestinationFloor;
		
	}
	
	//set current floor to be undefined
	public void boardElevator(){
		currentFloor = UNDEFINED_FLOOR;
		
	}
	
	/*
	 * arrives at destination
	 * copy value of destination floor to currentFloor
	 * set destination floor to be undefined
	 */
	public void arrive(){
		//currentFloor = destinationFloor;
		destinationFloor = UNDEFINED_FLOOR;
	}
	
/*	public String toString(){
		
		return "Passenger is on the "+currentFloor+ " floor. Waiting to go "+ elevator.direction+" to the "+destinationFloor+ "floor";
	}
*/
}
