# /// Adidas coding challenge

Provide interface to load availability resources (City/Travel)

Please address any questions and comments to (dami.fontenla@gmail.com).

## Usage

  * Running ./docker-run provides a full environment including two spring boot microservices, a mysql db, zookeper/kafka 
  service will be provided in order to resolve a path connections between two loaded cities.
  * Request /search/{SOURCE}/{DESTINY}
    - SOURCE and DESTINY path params, reference the city code name Ex. BUE
      
### SCOPE

Environment variable spring.profile.active is setting to the application scope in order to resolve correct properties

Docker compose integration is set as `spring.profiles.active=integration`

* Possible profiles:
    - local
    - integration

### Web Server

Each Spring Boot web application includes an embedded web server. Microservice stack is composed by:
  * Spring Boot (Rest/JPA) (maven dependency: `org.springframework.boot`
  * Swagger2 (maven dependency: `springfox-swagger2`)
  * Spring-Kafka (maven dependency: `spring-kafka`)
  * Mysql-connector (maven dependency: `mysql-connector-java`)
  * ...

### Sample usage

* Run initializer script to set up the environment

        ./run-docker.sh
* Making a request to http://localhost:4444/city/init you will jave initial data referred to travels and cities
* Requesting http://localhost:4444/city?page=0&size=10 you will get, cities resources loaded
* http://localhost:4000/search/BUE/ROS will make the serching for travels from BUE to ROS

## Api's Documentation

This project uses Springfox to automate the generation of machine and human readable specifications for JSON APIs written using Spring. 
Springfox works by examining an application, once, at runtime to infer API semantics based on spring configurations, class structure and various compile time java Annotations.

* availability-service: http://localhost:4444/swagger-ui.html
* search-service: http://localhost:4000/swagger-ui.html

You can change this configuration in SwaggerConfig class.

More information on AdidasChallengeArchitecture.pdf

## CI/CD

### Questions

[dami.fontenla@gmail.com](dami.fontenla@gmail.com)