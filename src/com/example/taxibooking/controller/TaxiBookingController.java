package com.example.taxibooking.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.example.taxibooking.model.DataQueue;
import com.example.taxibooking.model.TaxiBookingModel;
import com.example.taxibooking.model.Worker;
import com.example.taxibooking.view.TaxiBookingGUI;



public class TaxiBookingController {

	 private TaxiBookingModel model;
		private TaxiBookingGUI view;
		
		public TaxiBookingController(TaxiBookingModel a, TaxiBookingGUI v) {
			model = a;
			view = v;
			view.addButtonListener(new ProcessBookingsController());
		}			
	    
	    class ProcessBookingsController  implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e) 
	        { 
	        	DataQueue obs = new DataQueue();
	        	view.getInventory()[0].setText(obs.getPassengerQueue());
	        	view.getInventory()[1].setText(obs.getTaxiQueue());
	        	view.disableProcessButton();
	    		model.StartBooking(view.getWorkerwindow(), view.getInventory());	    		
	        }
	    }
}
