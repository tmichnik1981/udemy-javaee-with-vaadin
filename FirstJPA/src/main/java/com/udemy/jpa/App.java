package com.udemy.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class App {

	public static void main(String[] args) {
		
	/*	will look for persistence-unit with a name ="com.balazsholczer.jpa"*/
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.balazsholczer.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
	/*	Student s = new Student();
		s.setAge(30);
		s.setName("Daniel");
		
		Student s2 = new Student();
		s2.setAge(312);
		s2.setName("Adam");*/
		
		/*Person p1 = new Person("Kevin", "kevin@gmail.com");
		Person p2 = new Person("Joe", "joe@gmail.com");
		entityManager.persist(p1);
		entityManager.persist(p2);*/
	/*	Person p1 = new Person("Joe", 18);
		Person p2 = new Person("Joel", 34);
		Person p3 = new Person("Adam", 55);
		Person p4 = new Person("Nicola", 26);
		
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.persist(p3);
		entityManager.persist(p4);*/
		
		//entityManager.getTransaction().commit();
	
		//Query query = entityManager.createQuery("SELECT p FROM Person p ORDER BY p.age DESC");
		//Query query = entityManager.createNativeQuery("SELECT * FROM PERSON_TABLE", Person.class);
		
		TypedQuery query = entityManager.createNamedQuery("Person.getPersonByName", Person.class);
		query.setParameter("name", "Adam");
		List<Person> people = (List<Person>)query.getResultList();
		
		people.stream().forEach(person -> System.out.println(person));

		
		entityManager.close();
		entityManagerFactory.close();
		
	}
}
