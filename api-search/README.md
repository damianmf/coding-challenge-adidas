
# ///Adidas - Spring boot availability-service

Provide an approach to solve the challenge in an scalable architecture

Please address any questions and comments to (dami.fontenla@gmail.com).

## Usage

mvn clean install
docker build -t search-service . --no-cache

Docker container will be created. Take into account, if it is not run in an integration context, you will have
to provide all services
      
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

### Sample usage

* Run initializer script to set up the environment

After setting up all services, run SearchApplication class, if availability-service is up and loaded, this application
will sync its search resolver with that information in order to resolve queries

Ex: http://localhost:4000/search/BUE/ROS will make the serching for travels from BUE to ROS

## Api's Documentation

This project uses Springfox to automate the generation of machine and human readable specifications for JSON APIs written using Spring. 
Springfox works by examining an application, once, at runtime to infer API semantics based on spring configurations, class structure and various compile time java Annotations.

* availability-service: http://localhost:4000/swagger-ui.html

You can change this configuration in SwaggerConfig class.
## CI/CD

### Questions

[dami.fontenla@gmail.com](dami.fontenla@gmail.com)
