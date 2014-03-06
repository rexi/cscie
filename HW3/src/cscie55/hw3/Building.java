package cscie55.hw3;

import cscie55.hw3.Elevator;
import cscie55.hw3.Floor;
import cscie55.hw3.Passenger;
/*
 * @author:Eldis Raspopi
 * @version 1.0 Feb 29, 2014
 * Purpose: Building class that creates elevator and floor objects
 */

public class Building {

	public static final int FLOORS = 7;
	Elevator elevator;
	Passenger passenger;
	Floor [] floor = new Floor[FLOORS];
	
	//creates elevator object, and a floor array to hold each floor
	public Building(){
		elevator = new Elevator(this);	
		for (int i=0; i<FLOORS; i++)
			floor[i] = new Floor(this, i);
	}
	//returns elevator object	
	public Elevator elevator(){
		
		return elevator;
		
	}
	public Passenger passenger(){
		return passenger;
	}
	
	public void enter(Passenger passenger){
		
		floor[0].enterGroundFloor(passenger);
				
	}
	
	/*
	 * @param floorNumber
	 * returns floor object for the given floor. 
	 */
	public Floor floor(int floorNumber){
		
		//substract 1 from floorNumber to satisfy array's index, since we start index at 1
		return floor[floorNumber-1];
	}
}
