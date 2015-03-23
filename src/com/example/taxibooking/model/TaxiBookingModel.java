package com.example.taxibooking.model;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextArea;

import com.example.taxibooking.infos.Passenger;
import com.example.taxibooking.interfaces.Observer;
import com.example.taxibooking.interfaces.Subject;
import com.example.taxibooking.view.TaxiBookingGUI;

public class TaxiBookingModel  {
	
	private List<String> taxis = new LinkedList<String>();
	private List<Passenger> passengers = new LinkedList<Passenger>();
	
	
	public TaxiBookingModel(List<String> taxis, List<Passenger> passengers)
	{
		this.taxis = taxis;
		this.passengers = passengers;
	}
	
	public void StartBooking(JTextArea[] workerwindow, JTextArea[] inventory)
	{
		DataQueue obs = new DataQueue();
		Thread window1 = new Thread(new Worker("Worker1", obs, workerwindow, inventory));
		window1.start();
		Thread window2 = new Thread(new Worker("Worker2", obs, workerwindow, inventory));
		window2.start();
	}
	
	
}
