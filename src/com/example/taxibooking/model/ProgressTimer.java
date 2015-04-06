/**  
 * Progress timer class
 * Checks if the booking simulation is completed
 */
package com.example.taxibooking.model;

import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.example.taxibooking.logging.Logger;
import com.example.taxibooking.view.AppGUI;

public class ProgressTimer {

	Toolkit toolkit;

	Timer timer;
	DataQueue obs;
	AppGUI view;

	public ProgressTimer(DataQueue obs, AppGUI view) {
		this.obs = obs;
		this.view = view;
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, // initial delay
				1 * 1000); // subsequent rate
	}

	class RemindTask extends TimerTask {

		public void run() {

			// Checks if the booking process is finished
			if (obs.isBookingProcessFinished()) {
				timer.cancel();
				timer.purge();
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				// Writes status to log buffer
				Logger.getInstance().addtoLog(
						"\n\n==== Booking Simulation Closed at "
								+ dateFormat.format(date) + " ====\n");
				// Writes the details of the logger to a text file an closes the
				// file
				Logger.getInstance().commitLog();
				// Updates the GUI controls
				view.enableStartBookingButton();
				view.enableCbProcessingSpeed();
				view.enableSimulationLogButton();
				view.enableCbWorkerWindows();

				view.setProgressStatusEnd("  Processing bookings - Completed at "
						+ dateFormat.format(date));
			}
		}
	}
}
