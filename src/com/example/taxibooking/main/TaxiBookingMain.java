/**
 * 
 */
package com.example.taxibooking.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.example.taxibooking.controller.TaxiBookingController;
import com.example.taxibooking.infos.Passenger;
import com.example.taxibooking.model.TaxiBookingModel;
import com.example.taxibooking.model.Worker;
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
		
		List<String> taxis = new LinkedList<String>();
		List<Passenger> passengers = new LinkedList<Passenger>();
		Scanner sc;
		
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
		
		TaxiBookingModel model = new TaxiBookingModel(taxis, passengers);   //the model
        TaxiBookingGUI   view  = new TaxiBookingGUI  (model);
        TaxiBookingController controller = new TaxiBookingController(model, view);   
        view.setVisible(true);

	}

}
