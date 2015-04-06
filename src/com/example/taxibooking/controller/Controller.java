/**
 * 1. Controller class
 * 2. Passes the view from GUI to the model class
 */
package com.example.taxibooking.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.example.taxibooking.model.Model;
import com.example.taxibooking.view.AppGUI;



public class Controller {

	 private Model model;
		private AppGUI view;
		
		public Controller(Model a, AppGUI v) {
			model = a;
			view = v;
			view.addButtonListener(new ProcessBookingsController());
		}			
	/**
	 * Process bookings when the start bookings button is clicked
	 * 
	 */
	    class ProcessBookingsController  implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().trim().toUpperCase()
						.equals("START BOOKING")) {
					// Start booking
					model.StartBooking(view);

				} else if (e.getActionCommand().trim().toUpperCase()
						.equals("SHOW SIMULATION LOG")) {
					// Show simulation log
					model.ShowBookingSimulationLog();
				}
			} catch (Exception ex) {
				 JPanel panel = new JPanel();
				JOptionPane.showMessageDialog(panel, ex.getMessage(),
						"Taxi Booking System", JOptionPane.ERROR_MESSAGE);
				view.dispatchEvent(new WindowEvent(view,
						WindowEvent.WINDOW_CLOSING));
			}

		}
	    }
}
