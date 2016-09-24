Demo App to demonstrate Java Spring Boot App with Angular JS.

talk at #t3cmallorca barcamp 2016 

To get a better overview, most steps are git tagged. Just use
> git tag -n


## 0. install maven

> brew install maven

on mac .. and maybe you need to install java itself ;)

## 1. Create basic structure of a maven project:

Either use an **archetype** to generate basic folder structure and entrypoints
- http://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html

- https://spring.io/guides/gs/spring-boot/

- https://spring.io/guides/gs/maven/

e.g.

> mvn archetype:generate -DgroupId=com.mkyong.common -DartifactId=SpringExamples  -DarchetypeArtifactId=maven-archetype-quickstart - DinteractiveMode=true


### or Create a pom.xml and respective folder structure manually

> vi pom.xml

> mkdir -p src/main/java/de/davitec/appdemo

etc.

Maven defines dependencies and build lifecycle of your project. The are some alternatives to maven, like gradle etc.

## 2. Add an Java Entry Point for Spring Boot App

> vi src/main/java/de/davitec/appdemo/Application.java

Take care about annotations, we use Spring Boot and AutoConfig (comes with Spring Framework).

At this state, you can already build and run an  appdemo (well, you won't see very much at this state).

> mvn clean install spring-boot:run

This Maven command compile, package and install a the Spring Boot app (defined by pom.xml). Spring Boot runs an embedded Tomcat, thus you will find the app at localhost:8080.

Info: The git tag v0.2 declare also spring-security as a dependency .. thus, localhost:8080 already asks for login. No users are defined,yet. Thus, there will be no access at all.

## 3. Add some basic authentication and a simple Hello World Website

We add some inMemory Authentification. Later on, you might persist user/role data via JPA.
> vi src/main/java/de/davitec/appdemo/WebSecurityConfig.java

To provide a index.html Website, we need a Spring MVC Controller and a Template .. have a look at those files:
> src/main/resources/templates/index.html

> src/main/java/de/davitec/appdemo/controller/HomeController.java

for ServerSide Template Rendering, we use Thymeleaf (http://www.thymeleaf.org/) .. this is included by our pom.xml already.

Depending, on what you are going to do, you might need to add some thymeleaf config as well: http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html

now run again

> mvn spring-boot:run

If your want to try it: Login in as "user"/"password" oder "admin"/"password". You will find differences in the "localhost:8080/" (i.e. index.html), that reflect security roles via thymeleaf.

## 4. Add a customer entity and a customer repository using JPA

We now add a JPA Persistence Layer and configure a local mysql database -> for external database, you must have the database already created manually ! you might also use h2 embedded database .. but we want to check results by sequel pro or something else for testing purpose.

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

Now, it's time to use the rest API via AngularJS in FrontEnd. There, we will do the following steps:

- remove the auth permitAll exception for /customers
> vi src/main/java/de/davitec/appdemo/WebSecurityConfig.java

- allow REST API Role based by PreAuthorize (imagine a rest ressource, that should by only accessible for admins)
> vi src/main/java/de/davitec/appdemo/api/CustomerRestController.java

- add required angularjs webjars via pom.xml

- write you own app in src/main/resources/static/js/app.js

- include angularjs webjar resources as  well as your own app (src/main/resources/static/js/app.js) to your src/main/resources/templates/index.html

- index.html: ng-include your static html views (partials) to the angular app -> we are going to show a list of Customers

- add a controller and a service the consume rest data as static resources (async! broadcast success event and thus, load data by controller)

## 7. Full CRUD AngularJS/RestAPI

Finishing step 6, we got a list of persisted data via anuglar js.

Now we want to do full CRUD with customers, in detail
- add CRUD methods on REST API controller src/main/java/de/davitec/appdemo/api/CustomersRestController.java
- add htttp promises/service at src/main/resources/static/js/customerService.js
- add angular js controller methods src/main/resources/static/js/customerController.js
- add some modal dialogs to enter customer data
- CSRF request for API on POST/PUT/DELETE methods (overwrite spring security defaults)
- additionally, we add a custom login.html with respective backend controller

Have Fun!

## Step 8,9,10,...

check out
- angular ui-routes ! a finite state machine to route states via url, including transitions and nested states ui-views
- maven docker integration to provide mysql as a microservice
- cucumber / selenium  intergration-tests
- jasmine for angular unit tests
- much more maven libs & plugins .. https://mvnrepository.com
