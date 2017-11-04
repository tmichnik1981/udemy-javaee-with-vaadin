package com.udemy.inheritace;

import javax.persistence.Entity;

@Entity
public class Bus extends Vehicle{

	private int numberOfPassengers;
	
	public Bus(){}

	public Bus(String name, int numberOfPassengers) {
		super(name);
		this.numberOfPassengers = numberOfPassengers;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
}
