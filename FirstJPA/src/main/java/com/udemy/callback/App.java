package com.udemy.callback;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.balazsholczer.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

	
		Article article = new Article("Albert Einstein - Relativity");
		entityManager.persist(article);
		
		entityManager.getTransaction().commit();
		
		entityManager.persist(article);
		
		entityManager.close();
		entityManagerFactory.close();

	}

}
