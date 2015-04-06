/**
 * 1. Log the booking progress to a text file
 * 2. Implemented using singleton pattern
 */
package com.example.taxibooking.logging;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	private static Logger instance = new Logger();
	private StringBuilder buffer = new StringBuilder("");
	private FileWriter filewriter = null;
		
	/**
	 * Private construction to ensure a single instantiation of the class
	 * 
	 */
	private Logger() {
		
	}

	public static Logger getInstance() {
		return instance;
	}

	/**
	 * Writes to the log buffer
	 * 
	 */
	public void addtoLog(String line) {
		buffer.append(line.replaceAll("\n", "\r\n") + "\n");
	}

	/**
	 * Writes the buffer to file
	 * 
	 */
	public void commitLog() {
		try {
			filewriter = new FileWriter("logs\\ApplicationLog.txt");
			filewriter.append(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		if (filewriter != null) {
			try {
				filewriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
