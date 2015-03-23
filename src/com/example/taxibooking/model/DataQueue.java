package com.example.taxibooking.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.example.taxibooking.infos.Passenger;
import com.example.taxibooking.interfaces.Observer;
import com.example.taxibooking.interfaces.Subject;

public class DataQueue implements Subject{

	List<String> taxis = new LinkedList<String>();
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	public List<String> getTaxis() {
		return taxis;
	}

	public void setTaxis(List<String> taxis) {
		this.taxis = taxis;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	List<Passenger> passengers = new LinkedList<Passenger>();
	Scanner sc;
	
	public DataQueue()
	{
		try {
			sc = new Scanner(new FileInputStream("data/Taxis.txt"), "UTF-8");
			while (sc.hasNextLine()) {			
				taxis.add(sc.nextLine().trim());
			}
			
			sc = new Scanner(new FileInputStream("data/Customers.txt"), "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split("\t");
				passengers.add(new Passenger(data[0], data[1]));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);
		
	}

	@Override
	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);
		
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : registeredObservers)
		{
			obs.update();
		}
		
	}
	
	public  synchronized Passenger getNextPass()
	{
		if(passengers.size() == 0)
			return null;
		return passengers.remove(0);		
	}
	
	public String searchfortax()
	{
		if(taxis.size() == 0)
			return "";
		return taxis.get(0);
	}
	
	public  void assignTaxi()
	{
		if(!taxis.isEmpty())		
		taxis.remove(0);
		notifyObservers(); 		
	}
	
	public String getTaxiQueue()
	{
		StringBuilder temp = new StringBuilder("Taxis Queue (" + taxis.size() + ")\n\n");
		for(String taxi : taxis)
		{
			temp.append(taxi + "\n");
		}
		return temp.toString();
	}
	
	public String getPassengerQueue()
	{
		StringBuilder temp = new StringBuilder("Passengers Queue (" + passengers.size() + ")\n\n");
		for(Passenger passenger : passengers)
		{
			temp.append(passenger.getDestination() + "   " + passenger.getCount() + "\n");
		}
		return temp.toString();
	}
	
}
