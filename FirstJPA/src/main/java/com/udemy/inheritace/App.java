package com.udemy.inheritace;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

	public static void main(String[] args) {
		/*
		 * will look for persistence-unit with a name ="com.balazsholczer.jpa"
		 */
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.balazsholczer.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Car car = new Car("name of car", 250);
		Bus bus = new Bus("bus name", 200);
		
		entityManager.persist(car);
		entityManager.persist(bus);
		
		entityManager.getTransaction().commit();
		
		
		entityManager.close();
		entityManagerFactory.close();

	}

}
