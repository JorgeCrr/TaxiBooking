package com.example.taxibooking.infos;

public class Taxi {

	private String registrationNum = "";
	private int capacity = 0;
	
	public Taxi(String regNo, int cap)
	{
		registrationNum = regNo;
		capacity = cap;
	}

	public String getRegistrationNum() {
		return registrationNum;
	}

	public void setRegistrationNum(String registrationNum) {
		this.registrationNum = registrationNum;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
