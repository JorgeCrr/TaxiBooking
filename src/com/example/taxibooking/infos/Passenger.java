/**
 * 
 */
package com.example.taxibooking.infos;

/**
 * @author Jorge
 *
 */
public class Passenger {
	
	public Passenger(String dest, String count)
	{
		this.count = count;
		destination = dest;
	}

	private String count;
	
	private String destination;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
