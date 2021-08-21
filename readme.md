# Getting Started

### Prerequisites to run the application on your machine

* Java 8 and Maven

### Run application

* ./mvnw spring-boot:run
  <br /> (or)
* Run GammaProductManagerApplication class as java application from IDE
  <br /> (or)
* docker build using Dockerfile NOTE : Make sure mvn package is executed before docker build  
  docker build --quiet --build-arg ENVIRONMENT=local --tag latest .
  docker run -d -p 8080:8080 latest

### Using the application

* Open http://localhost:8080/ to see home page <br />

Application has all the crud API's to manage Stores

### Users & Roles

* user/user has role normal user role
* admin/admin has role admin role.

* The user with Admin role can perform post, put and delete operations
* The GET APIs are open

### Test API using postman

* Import Postman collection (ProductManagement.postman_collection.json) in postman

### front end pages
* Home Page : http://localhost:8080/
* To see products of a store : http://localhost:8080/products
* To See store details : http://localhost:8080/


### API Swagger documentation

* Link to API documentation (http://localhost:8080/swagger-ui/index.html#/)

### DATABASE

* Application uses in-memory h2 store (http://localhost:8080//h2-console)


