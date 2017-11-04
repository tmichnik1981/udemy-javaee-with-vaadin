package com.udemy.mapped;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.udemy.inheritace.Bus;
import com.udemy.inheritace.Car;

public class App {

	public static void main(String[] args) {
	
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.balazsholczer.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		StudentPerson studentPerson = new StudentPerson();
		studentPerson.setName("Joe Smith");
		studentPerson.setAge(29);
		studentPerson.setDrivingLicence("Joe's driving licence..");
		
		entityManager.persist(studentPerson);
		
		entityManager.getTransaction().commit();
		
		
		entityManager.close();
		entityManagerFactory.close();

	}

}
