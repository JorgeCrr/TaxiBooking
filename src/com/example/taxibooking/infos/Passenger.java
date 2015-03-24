/**
 * 
 */
package com.example.taxibooking.infos;

/**
 * @author Jorge
 *
 */
public class Passenger {
	
	public Passenger(String dest, int count)
	{
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
