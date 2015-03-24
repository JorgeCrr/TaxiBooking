/**
 * 
 */
package com.example.taxibooking.logging;

import java.io.FileWriter;
import java.io.IOException;



/**
 * @author Jorge
 *
 */
public class TaxiBookingLogger {

	private static TaxiBookingLogger instance = new TaxiBookingLogger();
	private FileWriter filewriter = null;
		
	private TaxiBookingLogger()
	{
		try {
			filewriter = new FileWriter("logs\\Application Log.txt");			
		} catch (IOException e)
		{			
			e.printStackTrace();
		}
	}

	public static TaxiBookingLogger getInstance()
	{
		return instance;
	}
	
	public void writeToLog(String line)
	{		
		try {			
			filewriter.append(line + "\n");
		} catch (IOException e)
		{			
			e.printStackTrace();
		}		
	}
	
	public void closeLog()
	{
		if(filewriter != null)
		{
			try {
				filewriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
