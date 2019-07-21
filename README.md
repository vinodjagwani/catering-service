# Getting Started - Caterwings catering service

### Reference Documentation

Catering service is basically provides crud apis for registering catering product and performing search,
This service is basically cloud compatible which is highly scalable and using non blocking reactive module from
spring. For Api documentation you can use following url.

#### Api Documentation

URL: http://localhost:8080/catering-restdoc.html

#### Technologies used

1. Java 8
2. Spring
3. Spring WebFlux
4. Spring Rest Docs
5. Docker
6. MongoDB
7. Dozer Mapper
8. Maven

#### How to run locally

Using following commands

1. mvn clean package
2. mvn spring-boot:run

or

if you have docker installed

1. mvn clean package
2. docker-compose up   (on the root folder of service)


Note: you should have mongodb to connect with it.



