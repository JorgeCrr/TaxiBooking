/**
 * 
 */
package com.example.taxibooking.model;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextArea;

import com.example.taxibooking.infos.Passenger;
import com.example.taxibooking.interfaces.Observer;
import com.example.taxibooking.interfaces.Subject;

/**
 * @author Jorge
 *
 */
public class Worker implements Runnable, Observer {
	
	private JTextArea [] workerwindow ; 
    private JTextArea [] inventory ;
    private String name;
    private Passenger passenger;
	private String taxi = " ";
	private DataQueue obs;
	private boolean workdone = false;
	
	public Worker(String name, DataQueue obs, JTextArea [] workerwindow, JTextArea [] inventory)
	{
		this.workerwindow = workerwindow;
		this.inventory = inventory;	
		this.obs = obs;
		obs.registerObserver(this);
		this.name = name;
	}
	
	
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		passenger = obs.getNextPass();
		while(passenger != null && taxi != "")
		{
			synchronized (obs) {			
			taxi = obs.searchfortax();
			if(taxi != "")
			{
				workdone = true;
				obs.assignTaxi();
			}
			}
			try {				
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			passenger = obs.getNextPass();
		}
	}

	@Override
	public synchronized void update()
	{
		if(workdone)
		{
			String temp = "Destination : "+ passenger.getDestination() + "\n"
					+ "Passengers : " + passenger.getCount() + "\n"
					+ "Taxi : " + taxi;
			if(name.equalsIgnoreCase("Worker1"))
			{				
				workerwindow[0].setText(temp);
			}
			else
			{				
				workerwindow[1].setText(temp);
			}
			
			inventory[0].setText(obs.getPassengerQueue());
			inventory[1].setText(obs.getTaxiQueue());
		}
		workdone =false;
	}
		
	
}
