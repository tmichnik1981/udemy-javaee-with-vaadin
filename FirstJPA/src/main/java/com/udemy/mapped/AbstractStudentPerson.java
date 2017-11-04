package com.udemy.mapped;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractStudentPerson {

	protected String drivingLicence;

	public AbstractStudentPerson() {
	
	}

	public String getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}
	
	
}
