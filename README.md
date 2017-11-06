# Java EE with Vaadin, Spring Boot and Maven

#### Links

#### Notes and Commands

- connect to mysql server

  ```
  mysql -u <MY_USER> -p
  ```

- list mysql databases

  ```shell
  show databases;
  ```

- create a new database

  ```shell
  create dabase udemy;
  ```

- select default database

  ```shell
  use udemy;
  ```

- create table

  ```sql
  CREATE TABLE `udemy`.`students` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    `age` INT NOT NULL,
    PRIMARY KEY (`id`));
  ```

- generate a basic maven project

  ```shell
  mvn archetype:generate -DgroupId=com.balazsholczer.test -DartifactId=test-app 
  -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  ```

- generate web maven project

  ```shell
  mvn archetype:generate -DgroupId=com.balazsholczer -DartifactId=web-test -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
  ```

- maven lifecycles: clean lifecycle, site lifecycle, default lifecycle

- clean:

  - mvn clean
  - removes target folder
  - phases: pre-clean, clean, post-clean

- site:

  - gerates documentation
  - phases: pre-site, site, post-site, site-deploy

- build

  - phases: process-resources - copy resources into the target folder
  - compile - compile the source code
  - test - run the tests
  - package - make a distributable format such as JARs or WARs
  - install - install the package into the local repository
  - deploy - copy the package to the remote repository

- `mvn <plugin-name> : <goal-name>` 

- configure 3rd party repository

  ```xml
  <repositories>
    <repository>
    	<id>remote_lib</id>
      <url>http://download.customurl.com/lib1</url>
    </repository>
  </repositories>  
  ```

- parent project

  - inheriting features from parent

  - packaging jar or pom

  - children must point at parent module

    ```xml
      <parent>
        <groupId>com.udemy</groupId>
        <artifactId>student-app</artifactId>
        <version>0.0.1-SNAPSHOT</version>
      </parent>
    ```

- project agregator

  - no features inheritance
  - only pom packaging
  - child modules do not  point at the parent
  - for building all modules at once

- JPA

  - EntityManager factory - factory for EntityManager 
  - EntityManager  - java interface, defines persistence related operations on java objects
  - Persistence - class containing static methods to obtain EntityManagerFactory instance
  - Entity - persistence objects
  - EntityTransactions - operations are maintained by this class

- EntityManager  CRUD operations

  ```java
  //create
  entityManager.getTransaction().begin();
  Student s = new Student();
  		s.setAge(30);
  		s.setName("Daniel");
  entityManager.getTransaction().commit();

  //find
  Person person = entityManager.find(Person.class, 1);

  //delete
  entityManager.getTransaction().begin();
  Person person = entityManager.find(Person.class, 1);
  entityManager.remove(person);
  entityManager.getTransaction().commit();
  ```

- JPQL

  ```java
  Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.age <= 40");
  Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.name LIKE '%el'");
  Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.age BETWEEN 20 AND 30");
  Query query = entityManager.createQuery("SELECT p FROM Person p ORDER BY p.age DESC");
  Query query = entityManager.createNativeQuery("SELECT * FROM PERSON_TABLE", Person.class);

  //named query
  @Entity
  @Table(name = "PERSON_TABLE")
  @NamedQueries({ @NamedQuery(name="Person.getAll", query="SELECT p FROM Person p"),
  @NamedQuery(name="Person.getPersonById", query="SELECT p FROM Person p WHERE p.id =:id"),
  @NamedQuery(name="Person.getPersonByName", query="SELECT p FROM Person p WHERE p.name =:name") })
  public class Person {}

  TypedQuery query = entityManager.createNamedQuery("Person.getAll", Person.class);
  query.setParameter("name", "Adam");
  		

  List<Person> people = (List<Person>)query.getResultList();

  ```

- Inheritance

  - SINGLE_TABLE
  - JOINED_TABLE
  - TABLE_PER_CONCRETE_CLASS

- SINGLE_TABLE 

  - a class hierach per table
  - a discriminator column
  - cons: NULL values in uncommon fields

  ```java
  //base class
  @Entity
  @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
  @Table(name= "VEHICLE_TABLE")
  @DiscriminatorColumn(name="type")
  public class Vehicle {}

  //child class 1
  @Entity
  @DiscriminatorValue(value = "B")
  public class Bus extends Vehicle{
  	private int numberOfPassengers;
  	public Bus(){}

  	public Bus(String name, int numberOfPassengers) {
  		super(name);
  		this.numberOfPassengers = numberOfPassengers;
  	}
  }
  //child class 2
  @Entity
  @DiscriminatorValue(value = "C")
  public class Car extends Vehicle {
  	private int speed;
  	public Car() {}

  	public Car(String name, int speed) {
  		super(name);
  		this.speed = speed;
  	}
  }
  ```

- JOINED_TABLE

  - different tables connected by id. Table for the base class and separate tables for child classes.
  - table per subclass

  ```java
  //base class
  @Entity
  @Inheritance(strategy=InheritanceType.JOINED)
  @Table(name= "VEHICLE_TABLE")
  public class Vehicle {}

  //child class 1
  @Entity
  @PrimaryKeyJoinColumn(referencedColumnName="id")
  public class Bus extends Vehicle{}

  //child class 2
  @Entity
  @PrimaryKeyJoinColumn(referencedColumnName="id")
  public class Car extends Vehicle {}
  ```

- TABLE_PER_CONCRETE_CLASS

  ```java
  //base class
  @Entity
  @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
  public abstract class Vehicle {
  	
  	@Id
  	@GeneratedValue(strategy=GenerationType.TABLE)
  	private int id;
  }

  //child class 1
  @Entity
  public class Bus extends Vehicle{}

  //child class 2
  @Entity
  public class Car extends Vehicle {}
  ```

- Inhereritance with MappedSuperclass

  - there wont be tables created  for the base class

  ```java
  //base class
  @MappedSuperclass
  public abstract class AbstractStudentPerson {
  	protected String drivingLicence;
  }
  //child class 1
  @Entity
  @Table
  public class StudentPerson extends AbstractStudentPerson {
  	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
  	private int id;
  }
  ```


- Compositions: OnetoOne, OneToMany, ManyToOne, ManyToMany

- OneToOne

  - `@OneToOne`

  ```java
  @Entity
  @Table(name="EMPLOYEE_TABLE")
  public class Employee {
  	
  	@Id
  	@GeneratedValue
  	private int employeeId;
  	
    //class Address owns the relation in an employee field
  	@OneToOne(mappedBy="employee")
  	private Address address;  
  }

  @Entity
  @Table(name="ADDRESS_TABLE")
  public class Address {
    	@Id
  	@GeneratedValue
  	private int addressId;
    
    	@OneToOne
    	//refers to a column in Employee (table)
  	@JoinColumn(name="employeeId")
  	private Employee employee;
  }
  ```


- ManyToOne

  - annotations: `@OneToMany, @ManyToOne`

  ```java
  @Entity
  public class University {
  	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
  	private int universityId;
  	
  	@Column(name="university_name")
  	private String universityName;
  	//Student owns the relation
  	@OneToMany(mappedBy="university")
  	private List<Student> students;
  }

  @Entity
  public class Student {
  	@Id
  	@Column(name="id")
  	@GeneratedValue(strategy=GenerationType.AUTO)
  	private int studentId;
  	
  	@Column(name="student_name")
  	private String studentName;
  	
  	@ManyToOne
    	//id column from University
  	@JoinColumn(name="universityId")
  	private University university;
  }
  ```

- ManyToMany

  - annotation: `@ManyToMany`

  ```java
  @Entity
  @Table(name="PROFESSOR")
  public class Professor {
  	
  	@Id
  	@Column(name="id")
  	@GeneratedValue
  	private int professorId;
  	
  	@Column(name="professor_name")
  	private String professorName;
  	
  	@ManyToMany(mappedBy="professors")
  	private List<ResearchProject> projects;
  }

  @Entity
  @Table(name = "PROJECTS")
  public class ResearchProject {

  	@Id
  	@Column(name = "id")
  	@GeneratedValue
  	private int projectId;

  	@Column(name = "project_name")
  	private String projectName;

  	@ManyToMany
    	//mapping table
  	@JoinTable(name = "PROF_PROJECT",
      //id fields in: ResearchProject, Professor         
  	joinColumns = @JoinColumn(name = "projectId"), 
  	inverseJoinColumns = @JoinColumn(name = "professorId"))
  	private List<Professor> professors;
  }
  ```

- fetching

  - lazy (`FetchType.LAZY`) - a related collection is retrieved when we call get..
  - eager (`FetchType.EAGER`) - a related collection is retrieved with the main object

- cascading

  - propagating  the effect of an operation to associated entities
  - used in parent - child relationships or in cases of composition

- cascade types

  - CascadeType.PERSIST
  - CascadeType.REMOVE
  - CascadeType.MERGE
  - CascadeType.REFRESH
  - CascadeType.ALL

- merge vs persist

  - persist - future changes will be tracked
  - merge - new instance is created with a copied state and become a part of a transaction

  ```java
  @Entity
  @Table(name="PROFESSOR")
  public class Professor {

  //changes made on professor object will be propagated to projects  
  @ManyToMany(mappedBy="professors", fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
  private List<ResearchProject> projects;
  }
  ```

- Callback and listeners

  ```
  @Entity
  public class Article {
  	@PrePersist
  	public void beforePersist(){
  		System.out.println("Before persisting article...");
  	}
  	
  	@PostPersist
  	public void AfterPersist(){
  		System.out.println("After persisting article...");
  	}
  }
  ```

- EntityListeners - a cleaner way to to use listeners. Operations are handled by a separate class

  ```java
  @EntityListeners(value={ArticleListener.class})
  @Entity
  public class Article {}

  public class ArticleListener {
  	@PrePersist
  	public void beforePersist(Article article){
  		article.setDate(new Date());
  		System.out.println("Before persisting article...");
  	}	
  	@PostPersist
  	public void AfterPersist(Article article){
  		System.out.println("After persisting article...");
  	}
  }
  ```

  ​ 

- Locking: pessimistic, optimistic

- Pessimistic locking

  - we assume there will be collisions so we lock the given record

- Optimistic locking

  - we assume there will **no** be collisions so we just lock while commiting the changes
  - we annotate a field with `@Version` annotation which stores the version of entity. Could be long , int, short.
  - OptimisticLockException is thrown when object has been changed by another user/thread

#### Instructions

###### Integrating SpringBoot with Vaading

1. Add Maven dependency

   ```xml
   <dependency>
   	<groupId>com.vaadin</groupId>
   	<artifactId>vaadin-spring-boot-starter</artifactId>
   	<version>2.0.1</version>
   </dependency>
   ```

2. Create a class forming Gui  

   ```java
   /**tells Spring to call this when /ui is requested*/
   @SpringUI(path="/ui")
   @Title("This is the title")
   /**default vaadin theme*/ 
   @Theme("valo")
   /**must extends com.vaadin.ui.UI*/
   public class MainView extends UI{

   	@Override
   	protected void init(VaadinRequest request) {
   		VerticalLayout verticalLayout = new VerticalLayout();
   		
   		verticalLayout.addComponent(new Label("Welcome to spring boot woth Vaadin..."));
   		Button button = new Button("Click me!");
   		verticalLayout.addComponent(button);
   		button.addClickListener(new Button.ClickListener() {		
   			@Override
   			public void buttonClick(ClickEvent event) {
   				verticalLayout.addComponent(new Label("Button has been clicked..."));
   				
   			}
   		});
   		setContent(verticalLayout);
   	}
   }
   ```

###### Deploying Spring-Boot-Vaadin project to the external Wilfly server 

1. Configure maven plugins: spring-boot-maven-plugin, maven-war-plugin.

   ```xml
   <plugins>
   	<plugin>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-maven-plugin</artifactId>
   		<configuration>
   			<executable>true</executable>
   		</configuration>
   	</plugin>
   	<plugin>
   		<artifactId>maven-war-plugin</artifactId>
   		<configuration>
   			<failOnMissingWebXml>false</failOnMissingWebXml>
   		</configuration>
   	</plugin>
   </plugins>
   ```

2. Remove the embedded Tomcat from deployment

   ```xml
   <dependency>
   	<groupId>org.springframework.boot</groupId>
   	<artifactId>spring-boot-starter-tomcat</artifactId>
   	<scope>provided</scope>
   </dependency>
   ```

3. Configure a starting-application class.

   ```java
   @SpringBootApplication
   /**extending SpringBootServletInitializer is necessary to get access to configure() method*/
   public class App extends SpringBootServletInitializer{

   	/**overwrite configure() method*/
   	@Override
   	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
   		return builder.sources(App.class);
   	}
   	
   	public static void main(String[] args) {
   		SpringApplication.run(App.class, args);
   	}	
   }
   ```

   ​