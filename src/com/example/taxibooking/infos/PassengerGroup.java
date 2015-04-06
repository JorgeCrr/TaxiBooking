package com.example.taxibooking.infos;
/**  
 * Passenger Group Data Access Object
 *
 */

public class PassengerGroup {

	public PassengerGroup(String dest, int count) {
		this.count = count;
		destination = dest;
	}

	private int count;
	
	private String destination;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
