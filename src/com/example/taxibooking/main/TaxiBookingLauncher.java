/**
 * Application launcher class 
 * Implements MVC pattern
 */
package com.example.taxibooking.main;

import com.example.taxibooking.controller.Controller;
import com.example.taxibooking.model.Model;
import com.example.taxibooking.view.AppGUI;

public class TaxiBookingLauncher {

	public static void main(String[] args) {

		// Object of the Model class
		Model model = new Model();
		// Object of GUI class
		AppGUI view = new AppGUI(model);
		// Passes the GUI and Model to the Controller class
		new Controller(model, view);
		view.setVisible(true);
	}

}
