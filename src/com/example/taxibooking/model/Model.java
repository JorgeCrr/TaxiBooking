/**
 * 1. Model
 * 
 */
package com.example.taxibooking.model;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.example.taxibooking.logging.Logger;
import com.example.taxibooking.view.AppGUI;

public class Model {

	public Model() {

	}
	/**
	 * Start the Booking process
	 * 
	 */
	public void StartBooking(AppGUI view) {
		DataQueue obs;
		try {
			obs = new DataQueue();
			// Update passenger groups display area automatically when items are
			// removed(Using observer pattern)
			view.getTaxisNPassengerGroups()[0].setText(obs
					.getPassengerGroupsQueue());
			view.getTaxisNPassengerGroups()[0].setCaretPosition(0);
			// Update taxis display area automatically when items are
			// removed(Using observer pattern)
			view.getTaxisNPassengerGroups()[1].setText(obs.getTaxiQueue());
			view.getTaxisNPassengerGroups()[1].setCaretPosition(0);
			// Disable certain form elements to prevent user from clicking them
			view.disableStartBooking();
			view.disableCbWorkerWindows();
			view.disableCbProcessingSpeed();
			view.disableSimulationLogButton();
			// Write status to the application log
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			view.setProgressStatus("  Processing bookings - started at "
					+ dateFormat.format(date));
			Logger.getInstance().writeToLog(
					"==== Booking Simulation Started at "
							+ dateFormat.format(date) + " ====\n");
			view.setProgressTotalTaxis("  Total Taxi(s): " + obs.taxis.size());
			view.setProgressTotalPassengerGroups("  Total Passenger Group(s): "
					+ obs.passengerGroups.size());
			view.setProgressStatusEnd("");
			// Start the worker windows in different threads
			for (int i = 0; i < view.getNoOfWorkerWindows(); i++) {
				Thread workerWindow = new Thread(new Worker(view, "Window "
						+ Integer.toString(i + 1), obs));
				workerWindow.start();
			}

			// Monitor the progress of booking simulation using Timer
			new ProgressTimer(obs, view);

		} catch (Exception ex) {
			final JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, ex.getMessage(),
					"Taxi Booking System", JOptionPane.ERROR_MESSAGE);
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
		}
	}

	/**
	 * Show the Booking simulation log
	 * 
	 */
	public void ShowBookingSimulationLog() throws IOException {
		File file = new File("logs/ApplicationLog.txt");
		java.awt.Desktop.getDesktop().edit(file);
	}

}
