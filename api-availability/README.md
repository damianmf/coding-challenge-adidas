
# ///Adidas - Spring boot availability-service

Provide an approach to solve the challenge in an scalable architecture

Please address any questions and comments to (dami.fontenla@gmail.com).

## Usage

mvn clean install
docker build -t availability-service . --no-cache

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
  * Mysql-connector (maven dependency: `mysql-connector-java`)

### Sample usage

* Run initializer script to set up the environment

After setting up all services, run CityApplication class
* Making a request to http://localhost:4444/city/init you will jave initial data referred to travels and cities
* Requesting http://localhost:4444/city?page=0&size=10 you will get, cities resources loaded

## Api's Documentation

This project uses Springfox to automate the generation of machine and human readable specifications for JSON APIs written using Spring. 
Springfox works by examining an application, once, at runtime to infer API semantics based on spring configurations, class structure and various compile time java Annotations.

* availability-service: http://localhost:4444/swagger-ui.html

You can change this configuration in SwaggerConfig class.
## CI/CD

### Questions

[dami.fontenla@gmail.com](dami.fontenla@gmail.com)
