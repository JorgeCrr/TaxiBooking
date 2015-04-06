/**
 * 1. Log the booking progress to a text file
 * 2. Implemented using singleton pattern
 */
package com.example.taxibooking.logging;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	private static Logger instance = new Logger();
	private FileWriter filewriter = null;
		
	/**
	 * Private construction to ensure a single instantiation of the class
	 * 
	 */
	private Logger() {
		try {
			filewriter = new FileWriter("logs\\ApplicationLog.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Logger getInstance() {
		return instance;
	}

	/**
	 * Writes to the log file
	 * 
	 */
	public void writeToLog(String line) {
		try {
			filewriter.append(line.replaceAll("\n", "\r\n") + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Closes the log file
	 * 
	 */
	public void closeLog() {
		if (filewriter != null) {
			try {
				filewriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
