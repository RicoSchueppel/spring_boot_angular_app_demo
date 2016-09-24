Demo App to demonstrate Java Spring Boot App with Angular JS.


To get a better overview, most steps are git tagged. Just use
> git tag - n


## 0. install maven

> brew install maven

on mac

## 1. Create basic structure of maven project:

Either use an **archetype** to generate basic folder structure
http://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html

https://spring.io/guides/gs/spring-boot/
https://spring.io/guides/gs/maven/

e.g.

> mvn archetype:generate -DgroupId=com.mkyong.common -DartifactId=SpringExamples  -DarchetypeArtifactId=maven-archetype-quickstart - DinteractiveMode=true


### or Create a pom.xml and folder structure manually

mkdir -p src/main/java/de/davitec/appdemo

## 2. Add an entry point for Spring Boot App
and annotate it according to Spring + we use AutoConfiguration

> vi src/main/java/de/davitec/appdemo/Application.java

now, you can already build and run the appdemo

> mvn clean install spring-boot:run

this will compile and package a spring boot app and run embedded Tomcat, App is launched at locahost:8080

hint: the git tag v0.2 declare als spring-security as a dependendency .. thus, localhost:8080 asks for login

## 3. Add some basic authentication and a simple Hello World Website

> vi src/main/java/de/davitec/appdemo/WebSecurityConfig.java

To provide a index.html Website, we need a Spring MVC Controller and a Template ..

look at those files:
> src/main/resources/templates/index.html
> src/main/java/de/davitec/appdemo/controller/HomeController.java


for ServerSide Template Rendering, we use Thymeleaf (http://www.thymeleaf.org/) .. this is included by pom.xml

depending, on what you are going to do, you might need to add some thymeleaf config: http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html

now run again

> mvn spring-boot:run

## 4. Add an entity/repository with JPA

We now add a JPA Persistence Layer and configure a local mysql database -> for external database, you must have the database already created manually ! you might also use h2 embedded database .. but we want to check results by sequel pro or something else

Modifiy pom.xml -> add *spring-boot-starter-data-jpa*

add property files to config mysql database (change database settings to your needs!) - we use profile based configuration by adding '-dev'
> src/main/resource/application.yml
> src/main/resource/application-dev.yml

and add mysql-connector-java in pom.xml as dependendency

to check, just run again 'mvn clean install spring-boot:run'

now we add an entity and a repository
> vi src/main/java/de/davitec/appdemo/entities/Customer.java
> vi src/main/java/de/davitec/appdemo/repository/CustomerRepository.java

and we prefill database during config steps
> vi src/main/java/de/davitec/appdemo/PreDefs.java

if you run to app again by mvn clean install spring-boot:run, the table will be created and prefilled. use some database tools like sequel pro to check entries.

## 5. Add REST API

## 6. Add Angular Controller/Service to receive data
