/**
 * 
 */
package com.example.taxibooking;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.taxibooking.infos.Passenger;
import com.example.taxibooking.infos.Taxi;
import com.example.taxibooking.model.DataQueue;
import com.example.taxibooking.model.Worker;

/**
 * @author Jorge
 *
 */
public class DataQueueTest {

	private DataQueue queue;
	private List<Passenger> passengers = new LinkedList<Passenger>();
	private List<Taxi> taxis = new LinkedList<Taxi>();	
	
	/**initialize data for test cases
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		queue = new DataQueue();	
		initializeTestData();
	}

	/** clean up the initialized data
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		queue = null;
		taxis = null;
		passengers = null;
	}

	/**
	 * Test method for DataQueue
	 */
	@Test
	public void testDataQueue() {		
		assertNotNull("DataQueue object is not created. ", queue);
		assertNotNull("Passengers queue not loaded", queue.getPassengers());
		assertNotNull("Passengers queue not loaded", queue.getTaxis());
	}

	
	/**
	 * Test method for getNextPass
	 */
	@Test
	public void testGetNextPass() {
		
		Passenger passenger = queue.getNextPass();
		assertTrue("Wrong next passenger fetched ", passengers.get(0).getDestination().equals(passenger.getDestination()));
		assertTrue("Wrong next passenger fetched ", passengers.get(0).getCount() == passenger.getCount());
	}

	/**
	 * Test method for searchfortax
	 */
	@Test
	public void testSearchfortax() {
		
		Taxi taxi = queue.searchfortax(5);
		assertTrue("Wrong capacity for fetched taxi ", taxis.get(2).getCapacity() == taxi.getCapacity());
		assertTrue("Wrong taxi selected ", taxis.get(2).getRegistrationNum().equals(taxi.getRegistrationNum()));		
	}

	/**
	 * Test method for assignTaxi
	 */
	@Test
	public void testAssignTaxi() {
		
		queue.assignTaxi(taxis.get(2));System.out.print(queue.getTaxiQueue());
		assertTrue("Taxi has not been assigned ", queue.getTaxis().size() == 2);
		for(Taxi taxi : queue.getTaxis())
		{
			if(taxi.getRegistrationNum().equals(taxis.get(2).getRegistrationNum()))
				fail("Wrong taxi has been assigned");
		}
	}

	/**
	 * Test method for getTaxiQueue
	 */
	@Test
	public void testGetTaxiQueue() {
		
		String output = queue.getTaxiQueue();
		assertNotNull("No Taxi queue returned ", output);
		assertTrue("Wrong taxi queue returned ", output.equals("Taxis Queue (3)\n\nHJJDHJD\nUDSUSJ\nJKKJH\n"));
	}

	/**
	 * Test method for getPassengerQueue
	 */
	@Test
	public void testGetPassengerQueue() {
		
		String output = queue.getPassengerQueue();
		assertNotNull("No Passenger queue returned ", output);
		assertTrue("Wrong Passenger queue returned ", output.equals("Passengers Queue (3)\n\nSharjah   3\nDubai   5\nAbu Dhabi   4\n"));
	}

	/**
	 *  data for the test functions to run independent of the actual data in the files
	 */
	private void initializeTestData()
	{
		passengers.add(new Passenger("Sharjah", 3));		
		passengers.add(new Passenger("Dubai", 5));
		passengers.add(new Passenger("Abu Dhabi", 4));
		taxis.add(new Taxi("HJJDHJD", 4));		
		taxis.add(new Taxi("UDSUSJ", 3));
		taxis.add(new Taxi("JKKJH", 5));
		queue.setTaxis(new LinkedList<Taxi>(taxis));
		queue.setPassengers(new LinkedList<Passenger>(passengers));
	}
}
