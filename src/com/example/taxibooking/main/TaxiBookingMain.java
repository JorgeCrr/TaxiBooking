/**
 * 
 */
package com.example.taxibooking.main;

import com.example.taxibooking.controller.TaxiBookingController;
import com.example.taxibooking.model.TaxiBookingModel;
import com.example.taxibooking.view.TaxiBookingGUI;


/**
 * @author Jorge
 *
 */
public class TaxiBookingMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
				
		TaxiBookingModel model = new TaxiBookingModel();   
        TaxiBookingGUI   view  = new TaxiBookingGUI  (model);
        TaxiBookingController controller = new TaxiBookingController(model, view);   
        view.setVisible(true);

	}

}
