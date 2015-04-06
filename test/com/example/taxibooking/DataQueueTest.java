/**
 * 
 */
package com.example.taxibooking;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.taxibooking.infos.PassengerGroup;
import com.example.taxibooking.infos.Taxi;
import com.example.taxibooking.model.DataQueue;

/**
 * @author Jorge
 *
 */
public class DataQueueTest {

	private DataQueue queue;
	private List<PassengerGroup> passengers = new LinkedList<PassengerGroup>();
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
		
		PassengerGroup passenger = queue.getNextPass();
		assertTrue("Wrong next passenger fetched ", passengers.get(0).getDestination().equals(passenger.getDestination()));
		assertTrue("Wrong next passenger fetched ", passengers.get(0).getCount() == passenger.getCount());
	}

	/**
	 * Test method for searchfortaxi
	 */
	@Test
	public void testSearchfortaxi() {
		
		Taxi taxi = queue.searchForTaxi(5);
		assertTrue("Wrong capacity for fetched taxi ", taxis.get(2).getCapacity() == taxi.getCapacity());
		assertTrue("Wrong taxi selected ", taxis.get(2).getRegistrationNum().equals(taxi.getRegistrationNum()));		
	}

	/**
	 * Test method for assignTaxi
	 */
	@Test
	public void testAssignTaxi() {
		
		queue.assignTaxi(taxis.get(2));
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
		assertTrue("Wrong taxi queue returned ", output.equals(" REMAINING (3)\n\n Reg No.   Seats" +
				"\n -------   -----" +
				"\n HJJDHJD   4  " +
				"\n UDSUSJ    3  " +
				"\n JKKJH     5  "));		
	}

	/**
	 * Test method for getPassengerGroupsQueue
	 */
	@Test
	public void testGetPassengerGroupsQueue() {
		
		String output = queue.getPassengerGroupsQueue();
		assertNotNull("No Passenger queue returned ", output);
		assertTrue("Wrong Passenger queue returned ", output.equals(" REMAINING (3)\n\n Destination         Passenger(s)\n" +
																" -----------         ------------" +
																"\n Sharjah             3  " +
																"\n Dubai               5  " +
																"\n Abu Dhabi           4  "));
	}

	/**
	 *  data for the test functions to run independent of the actual data in the files
	 */
	private void initializeTestData()
	{
		passengers.add(new PassengerGroup("Sharjah", 3));		
		passengers.add(new PassengerGroup("Dubai", 5));
		passengers.add(new PassengerGroup("Abu Dhabi", 4));
		taxis.add(new Taxi("HJJDHJD", 4));		
		taxis.add(new Taxi("UDSUSJ", 3));
		taxis.add(new Taxi("JKKJH", 5));
		queue.setTaxis(new LinkedList<Taxi>(taxis));
		queue.setPassengers(new LinkedList<PassengerGroup>(passengers));
	}
}
