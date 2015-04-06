/**  
 * Reads data from Taxis and Passengers
 * Implements the method of the abstract Subject class of the Observer Pattern 
 */
package com.example.taxibooking.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.example.taxibooking.infos.PassengerGroup;
import com.example.taxibooking.infos.Taxi;
import com.example.taxibooking.interfaces.Observer;
import com.example.taxibooking.interfaces.Subject;

public class DataQueue implements Subject{

	List<Taxi> taxis = new LinkedList<Taxi>();
	// All the registered observers are stored in a LinkedList for easy addition
	// and deletion
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	List<PassengerGroup> passengerGroups = new LinkedList<PassengerGroup>();
	Scanner sc;
	int unAllocatePassengerGroups=0;

	public List<Taxi> getTaxis() {
		return taxis;
	}

	public void setTaxis(List<Taxi> taxis) {
		this.taxis = taxis;
	}

	public List<PassengerGroup> getPassengers() {
		return passengerGroups;
	}

	public void setPassengers(List<PassengerGroup> passengerGroups) {
		this.passengerGroups = passengerGroups;
	}

	/**
	 * Reads data from Taxis and Passenger Groups and stores each data set in a
	 * different linked list
	 */
	public DataQueue() throws FileNotFoundException {
		sc = new Scanner(new FileInputStream("data/Taxis.txt"), "UTF-8");

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] data = line.split("\t");
			taxis.add(new Taxi(data[0], Integer.parseInt(data[1])));
		}

		sc = new Scanner(new FileInputStream("data/PassengerGroups.txt"),
				"UTF-8");

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] data = line.split("\t");
			passengerGroups.add(new PassengerGroup(data[0], Integer
					.parseInt(data[1])));
		}
	}

	/**
	 * Implements the registerObserver method of the abstract Subject class of
	 * the Observer Pattern
	 * 
	 */
	@Override
	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);
		
	}

	/**
	 * Implements the removeObserver method of the abstract Subject class of the
	 * Observer Pattern
	 * 
	 */
	@Override
	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);
		
	}

	/**
	 * Implements the notifyObservers method of the abstract Subject class of
	 * the Observer Pattern
	 * 
	 */
	@Override
	public void notifyObservers() {
		for (Observer obs : registeredObservers) {
			obs.update();
		}
		
	}

	/**
	 * Selects a passenger group for booking
	 * 
	 */
	public synchronized PassengerGroup getNextPass() {
		if (passengerGroups.size() == 0)
			return null;
		return passengerGroups.remove(0);
	}

	/**
	 * Checks if the Taxi has the capacity to carry the passenger group
	 * 
	 */
	public Taxi searchForTaxi(int numberofPassengers) {
		if (taxis.size() == 0)
			return null;
		for (Taxi taxi : taxis) {
			if (taxi.getCapacity() >= numberofPassengers)
				return taxi;
		}
		unAllocatePassengerGroups++;
		return new Taxi("Taxi unavailable for passenger group", 0);
	}

	/**
	 * 1. Assigns the Taxi to a passenger group 2. Removes the taxi from the
	 * Taxi Linked lost 3. Call the notifyObservers method which updates the
	 * screen
	 */
	public void assignTaxi(Taxi taxi) {
		if (!taxis.isEmpty()) {
			taxis.remove(taxi);
			notifyObservers();
		}
	}
		
	/**
	 * Returns the unallocated PassengerGroups in the queue
	 * 
	 */
	public int getUnallocatedPassengerGroups() {		
		return unAllocatePassengerGroups;
	}
	
	/**
	 * Progress
	 * 
	 */
	public String getProgress() {		
		
		return ".";
	}
	
	/**
	 * Displays the remaining Taxis in the queue
	 * 
	 */
	public String getTaxiQueue() {
		StringBuilder taxiQueue = new StringBuilder(" REMAINING ("
				+ taxis.size() + ")\n");
		taxiQueue.append(String.format("\n %-10s%-3s", "Reg No.", "Seats"));
		taxiQueue.append(String.format("\n %-10s%-3s", "-------", "-----"));
		for (Taxi taxi : taxis) {
			taxiQueue.append(String.format("\n %-10s%-3s",
					taxi.getRegistrationNum(), taxi.getCapacity()));
		}
		return taxiQueue.toString();
	}

	/**
	 * Displays the remaining Passenger groups in the queue
	 * 
	 */
	public String getPassengerGroupsQueue() {
		StringBuilder passengerQueue = new StringBuilder(" REMAINING ("
				+ passengerGroups.size() + ")\n");
		passengerQueue.append(String.format("\n %-20s%-3s", "Destination",
				"Passenger(s)"));
		passengerQueue.append(String.format("\n %-20s%-3s", "-----------",
				"------------"));
		for (PassengerGroup passenger : passengerGroups) {
			passengerQueue.append(String.format("\n %-20s%-3s",
					passenger.getDestination(), passenger.getCount()));
		}
		return passengerQueue.toString();
	}
	
	/**
	 * Checks if further processing is required
	 * 
	 */
	public boolean isBookingProcessFinished() {		
		if(taxis.size()<=0 || passengerGroups.size()<=0)
			return true;
		return false;
	}
}
