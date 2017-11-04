package com.udemy.composition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS_TABLE")
public class Address {

	@Id
	@GeneratedValue
	private int addressId;
	
	@Column(name="address_name")
	private String addressName;
	
	@Column(name="zip_code")
	private String zipCode;
	
	@OneToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	public Address() {
	}

	public Address(String addressName, String zipCode) {
		this.addressName = addressName;
		this.zipCode = zipCode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
