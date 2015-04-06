/**  
 * Worker window class
 * Implements Update method of the abstract Observer class using the Observer pattern
 */
package com.example.taxibooking.model;

import javax.swing.JTextArea;

import com.example.taxibooking.infos.PassengerGroup;
import com.example.taxibooking.infos.Taxi;
import com.example.taxibooking.interfaces.Observer;
import com.example.taxibooking.logging.Logger;
import com.example.taxibooking.view.AppGUI;

/**
 * @author Jorge
 *
 */
public class Worker implements Runnable, Observer {

	// Worker windows display in JTextArea
	private JTextArea[] displayWindows;
	// Passenger Groups and Taxis display in JTextArea
	private JTextArea[] displayTaxisNPassengerGroups;
	private String name;
	private String processingSpeed;
	private PassengerGroup passenger;
	private Taxi taxi = new Taxi("", 0);
	private DataQueue obs;
	private AppGUI view;
	private boolean workdone = false;

	public Worker(AppGUI v, String name, DataQueue obs) {
		this.view = v;
		this.name = name;
		this.obs = obs;
		obs.registerObserver(this);
		this.displayWindows = view.getWorkerwindow();
		this.displayTaxisNPassengerGroups = view.getTaxisNPassengerGroups();
		this.processingSpeed = view.getProcessingSpeed();

	}

	/**
	 * When the thread is executed
	 * 
	 */
	@Override
	public void run() {

		// Logs when the thread has started
		// Logger.getInstance().writeToLog("#  " + name + " is active\n");

		// Gets a passenger from the linked list
		passenger = obs.getNextPass();
		// Checks if the passenger groups and taxis are remaining in the queue
		while (passenger != null && taxi != null) {
			synchronized (obs) {
				// Searches for a Taxi for the matching passenger group count
				taxi = obs.searchForTaxi(passenger.getCount());
				if (taxi != null) {
					workdone = true;
					// Assigns taxi
					obs.assignTaxi(taxi);
				}
			}
			try {
				// Controls the speed of processing based on the selected option
				if (processingSpeed.equals("Slow")) {
					Thread.sleep(2000);
				} else if (processingSpeed.equals("Normal")) {
					Thread.sleep(1000);
				} else if (processingSpeed.equals("Fast")) {
					Thread.sleep(500);
				} else {
					Thread.sleep(200);
				}
			} catch (InterruptedException ex) {
				try {
					throw new InterruptedException();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			passenger = obs.getNextPass();
		}

		// Logger.getInstance().writeToLog("\n#  " + name + " is closing");
	}

	/**
	 * 1. Implements the update method of the abstract observer class 2. Updates
	 * the worker windows, Taxi and Passenger Group queues
	 */
	@Override
	public synchronized void update() {
		if (workdone) {
			String temp = " " + name + "\n ---------\n Destination: "
					+ passenger.getDestination() + "\n" + " Passengers: "
					+ passenger.getCount() + "\n" + " Taxi: "
					+ taxi.getRegistrationNum();
			Logger.getInstance().writeToLog("\n" + temp);

			// Updates the worker windows display
			if (name.equalsIgnoreCase("Window 1")) {
				displayWindows[0].setText(temp);
			} else if (name.equalsIgnoreCase("Window 2")) {
				displayWindows[1].setText(temp);
			} else if (name.equalsIgnoreCase("Window 3")) {
				displayWindows[2].setText(temp);
			} else if (name.equalsIgnoreCase("Window 4")) {
				displayWindows[3].setText(temp);
			} else if (name.equalsIgnoreCase("Window 5")) {
				displayWindows[3].setText(temp);
			} else if (name.equalsIgnoreCase("Window 6")) {
				displayWindows[3].setText(temp);
			}

			// Progress Unallocated Passenger groups
			view.setProgressPassengerGroupsUnAllocated(obs
					.getUnallocatedPassengerGroups());

			// Progress status
			view.setProgressStatusEndMonitor(obs.getProgress());

			// Updates Passenger groups queues
			displayTaxisNPassengerGroups[0].setText(obs
					.getPassengerGroupsQueue());
			displayTaxisNPassengerGroups[0].setCaretPosition(0);

			// Updates Taxis queues
			displayTaxisNPassengerGroups[1].setText(obs.getTaxiQueue());
			displayTaxisNPassengerGroups[1].setCaretPosition(0);
		}
		workdone =false;
	}
		
	
}
