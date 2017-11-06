package com.balazsholczer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Student {

	@Autowired
	@Qualifier("1")
	private Address address;

	public void showInfo(){
		System.out.println("Student address is: " + address.getAddress());
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
