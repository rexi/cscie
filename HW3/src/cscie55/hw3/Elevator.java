package cscie55.hw3;

import java.util.*;

import cscie55.hw3.ElevatorFullException;
import cscie55.hw3.Building;
/*
 * @author:Eldis Raspopi
 * @version 1.0 Feb 29, 2014
 * Purpose: Simulate elevator operations. The elevator travels up to the 7th floor stopping only on floors that passengers
 * are destined to take off, then comes back down to the first floor
 * Exception is thrown if people on floors waiting to embark the elevator exceed elevator's capacity. The rest of them
 * will be picked up on the next run
 */


public class Elevator {

	private int currentFloor;
	public static final int CAPACITY = 10;
	int buildingNumber = 1;
	Building building;
	//Passenger pass;
	Floor floor;
	//int idestinationFloor;
	Direction direction;
	public HashSet<Passenger> passengers  = new HashSet<Passenger>();
	//private int[] peoplePerFloor;
	private enum Direction {
		UP, DOWN
	};


	/*
	 * class constructor to initiate the array. Also it sets the array size to the maximum number of
	 * floors from the building class
	 * starting the array at 1 since that is our first floor
	 */

	public Elevator(Building building) {
		
		this.building = building;
		currentFloor = 1;
		/*peoplePerFloor = new int[Building.FLOORS];
		for (int i=0; i<Building.FLOORS; i++)
			peoplePerFloor[i] = 0;
			*/
		direction = Direction.UP;
	}
	
	public boolean goingUp(){
		
		if(direction == Direction.UP){
			return true;
		}
			
		else {
			return false;
		}
		
	}
	
	public boolean goingDown(){
		
		if(direction == Direction.DOWN){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	//return elevator's current floor
	public int currentFloor(){
		
		return currentFloor;
	}
	//returns all passengers objects in the elevator
	public Set<Passenger> passengers(){
		
		return passengers;
		
	}

	/*
	 * @param direction move method modifies elevator's state it changes its direction to move up
	 * when on the first floor then down when it reaches the top floor. it
	 * increments or decrements the floors based on the direction its moving it
	 * checks to see if it has to stop on each floor
	 */

	public void move() {
		
		//System.out.println("Calling move...");
		if (currentFloor == Building.FLOORS) {
			direction = Direction.DOWN;

		} else if (currentFloor == 1) {
			direction = Direction.UP;
			
		}
		if (direction == Direction.DOWN) {
			if(building.floor(currentFloor).waitingDown.size()>0)
				passengers.addAll(building.floor(currentFloor).waitingDown);
			currentFloor--;
		}
		else {
			if(building.floor(currentFloor).waitingUp.size()>0)
				passengers.addAll(building.floor(currentFloor).waitingUp);
			currentFloor++;
		}
		
//CHECKING PASSENGERS INSIDE THE ELEVATOR
	/*	
		Iterator<Passenger> pass =  passengers.iterator();
		
		while(pass.hasNext()){
			
			Passenger p = pass.next();
			p.currentFloor = currentFloor;

			
			if(p.destinationFloor() == currentFloor)
			{
			    building.floor(currentFloor).floorResident.add(p);
			    p.arrive();
			    //System.out.println("pass size: " + passengers.size());
			    pass.remove();
			    System.out.println("pass size after: " + passengers.size());
			    
			  
			 //   System.out.println("Dropping: " + p.iPassID);
			}
			
		}
		*/
		
		for(Iterator<Passenger> pass = passengers.iterator(); pass.hasNext();){
			Passenger p = pass.next();
			p.currentFloor = currentFloor;
			//p.arrive();
			if(p.destinationFloor()==currentFloor){
				building.floor(currentFloor).floorResident.add(p);
			   // p.arrive();
			    //System.out.println("pass size: " + passengers.size());
			    pass.remove();
			}
		}
		

//Checking for passengers on each floor waiting to go UP
		if(goingUp()==true){
			Iterator<Passenger> goingUp =  building.floor(currentFloor).waitingUp.iterator();
			
			while(goingUp.hasNext()){
				
				Passenger u = goingUp.next();
				
				if(u.currentFloor() == currentFloor)
				{
					try{
						boardPassenger(u.destinationFloor);
						
					}
					catch(ElevatorFullException e){
						
					}
					
				}
				
				
			}
		}
		
		if(goingDown() == true){

			Iterator<Passenger> goingDown =  building.floor(currentFloor).waitingDown.iterator();
			
			while(goingDown.hasNext()){
				
				Passenger d = goingDown.next();
				
				if(d.currentFloor() == currentFloor)
				{
					try{
						boardPassenger(d.destinationFloor);
						
					}
					catch(ElevatorFullException e){
						
					}
					
				}
				
				
			}
		}
		
		
        
	} 
	

	/*
	 * passengers destined for indicated floor. 
	 * @param destinationFloorNumber is the accepted argument for
	 * which a passenger wishes to stop at. it increments the total number of
	 * people in the elevator as well as the number of people in the array.
	 * @throws ElevatorFullException if number of people waiting at a floor (calling the elevator)
	 * is greater then the elevator capacity
	 */
		
	public void boardPassenger(int destinationFloorNumber) throws ElevatorFullException {
			
		if((goingUp() == true)&&(passengers().size() >= 0)){
			
			while( passengers.size()>0){
				floor.waitForElevator(passengers.iterator().next(), destinationFloorNumber);
			}
		
			throw new ElevatorFullException();
		}
		else {
			
		//embarking waiting people to the elevator
			passengers.addAll(floor.waitingUp);
			
			}
		if((goingDown() == true)&&(passengers().size() >= 0)){
			
			while( passengers.size()>0){
				floor.waitForElevator(passengers.iterator().next(), destinationFloorNumber);
			}
		
			throw new ElevatorFullException();
		}
		else {
			
		//embarking waiting people to the elevator
			passengers.addAll(floor.waitingDown);
			
			}
			
				 
		}


}

