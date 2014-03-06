package cscie55.hw3;

import java.util.ArrayList;
import java.util.HashSet;

import cscie55.hw3.ElevatorFullException;
import cscie55.hw3.Building;
import cscie55.hw3.Passenger;


/*
 * @author:Eldis Raspopi
 * @version 1.0 Feb 29, 2014
 * Purpose: Floor class that returns floor information for each floor
 */

public class Floor {
	
	int currentFloor;
	Building building;
	
	public HashSet<Passenger> floorResident  = new HashSet<Passenger>();
	public ArrayList<Passenger> waitingUp = new ArrayList<Passenger>();
	public ArrayList<Passenger> waitingDown = new ArrayList<Passenger>();
	/*
	 * @param building the floor constructor return building which is not really necessary
	 * @param floorNumber is the current floor number for the floor class different from elevator's current floor
	 */
	public Floor(Building building, int floorNumber){
		
		this.building = building;
		currentFloor = floorNumber;
	}
	//true if passenger resident of the floor
	public boolean isResident(Passenger passenger){
		if(floorResident.contains(passenger)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void enterGroundFloor(Passenger passenger){
		
		floorResident.add(passenger);
		passenger.currentFloor = currentFloor;
		passenger.destinationFloor = Passenger.UNDEFINED_FLOOR;
	}
	
	/*
	 * handles people waiting for the elevator on each floor. we disregard ground floor and load waiting people on
	 * the elevator for each floor. Since the passenger boarding method throws a full elevator exception we try to
	 * load passengers from each floor then decrementing the passengerswaiting variable.
	 * if the exception is thrown we leave the remainder of passengers to be loaded on the next elevator run
	 */
	public void waitForElevator(Passenger passenger, int destinationFloor){
		
		if(currentFloor>destinationFloor){
			passenger.destinationFloor = destinationFloor;
			waitingDown.add(passenger);
		}
		else if(currentFloor<destinationFloor){
			passenger.destinationFloor = destinationFloor;
			waitingUp.add(passenger);
		}
		else if(destinationFloor == currentFloor){
			floorResident.add(passenger);
			passenger.currentFloor=destinationFloor;
			passenger.destinationFloor = Passenger.UNDEFINED_FLOOR;
		}
		
	}
	

	

}
