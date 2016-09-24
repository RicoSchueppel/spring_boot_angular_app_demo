Demo App to demonstrate Java Spring Boot App with Angular JS.


To get a better overview, most steps are git tagged. Just use
> git tag -n


## 0. install maven

> brew install maven

on mac .. and maybe java itself ;)

## 1. Create basis structure of a maven project:

Either use an **archetype** to generate basic folder structure and entrypoints
- http://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html

- https://spring.io/guides/gs/spring-boot/

- https://spring.io/guides/gs/maven/

e.g.

> mvn archetype:generate -DgroupId=com.mkyong.common -DartifactId=SpringExamples  -DarchetypeArtifactId=maven-archetype-quickstart - DinteractiveMode=true


### or Create a pom.xml and folder structure manually

> touch pom.xml

> mkdir -p src/main/java/de/davitec/appdemo

etc.

Maven defined dependencies and build lifecycle of your project. Several alternatives, e.g. gradle.

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

so far, we have
- a homecontroller, that delivers a thymeleaf rendered index.html
- a persistence layer to store data in mysql

we are going to use static Angular view and want to request data by REST API

### **2 Options** to access REST API

#### A) generic full HATEOAS REST API (generated from repository interface)
  - declare dependency in pom.xml for spring-boot-starter-data-rest
  - for testing purpose, declare the respecitive ressource to be accessible without security
> vi src/main/java/de/davitec/appdemo/WebSecurityConfig.java

now u can access JPA persisted data via REST
> curl -i http://localhost:8080/customers

> curl -i http://localhost:8080/customers/search/findByName\?name=Meier

you find this in git tag version 0.5

#### B) declare a REST API by yourself via RestController

- remove pom.xml dependency 'spring-boot-starter-data-rest'
- path /customers will not be found anymore
- thus, we add our own api controller for /customers ressource
> vi src/main/java/de/davitec/appdemo/api/CustomersRestController.java

since we did not deactive the permitAll() config for customers, we can again test this via (after rebuild)

> curl -i http://localhost:8080/customers

btw, we did not define a rule for /login ressource, so even login was not accessible without registration - that makes no sense at all ;)

## 6. Add Angular Controller/Service to receive data

now its time to us the rest API via AngularJS in FrontEnd
there we will do the following steps
- remove the auth permitAll exception for /customers
> vi src/main/java/de/davitec/appdemo/WebSecurityConfig.java

- allow REST API Role based by PreAuthorize (imagine a rest ressource, that should by only accessible for admins)
> vi src/main/java/de/davitec/appdemo/api/CustomerRestController.java

- add required angularjs webjars via pom.xml

- write you own app in src/main/resources/static/js/app.js

- include angularjs webjar resources as  well as your own app (src/main/resources/static/js/app.js) to your src/main/resources/templates/index.html

- index.html: ng-include your static html views (partials) to the angular app -> we are going to show a list of Customers

- add a controller and a service the consume rest data as static resources (async! broadcast success event and thus, load data by controller)
