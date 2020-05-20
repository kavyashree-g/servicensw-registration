# Service NSW Registrations - Create and Get API.
This is a Springboot Rest API microservice project to create and get the list of Service NSW vehicle registration Details for a given user.
The flow of the project is that when the API is invoked, controllers hit the service class, which in turn invokes JPA repository to persist data in h2 or mysql database.

Below are the two API's

####Create Registration : User is allowed to register the vehicles in Service NSW.
POST http://localhost:8080/api/v1/registrations

####Get Registration  : Retrieves the list of vehicle registered for the given User.
GET http://localhost:8080/api/v1/registrations/{userId}

####Maven command to run the Application
spring-boot:run

####Postman Collection
ServiceNSW_Registration.postman_collection.json is attached in the project 
