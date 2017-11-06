package com.balazsholczer.spring;

import org.springframework.stereotype.Component;

@Component
public class Address {

	private String address = "Wall Street 34";
	
	public String getAddress(){
		return this.address;
	}
}
