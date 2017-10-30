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

#### Instructions