Demo App to demonstrate Java Spring Boot App with Angular

most steps are git tagged
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

To provide a index.html Website, we need a Spring MVC Controller and a Template .. for ServerSide Template Rendering, we use Thymeleaf (http://www.thymeleaf.org/)

look at those files:
> src/main/resources/templates/index.html
> src/main/java/de/davitec/appdemo/controller/HomeController.java

run again

> mvn spring-boot:run

## 4. Add an entity/repository with JPA


## 5. Add REST API

## 6. Add Angular Controller/Service to receive data
