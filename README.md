# Property Rental Application
This application is my build for september. It represents a market place where property brokers and buyers can carry out their transaction.
This application exposes APIs for user registration, authentication which is opened to all and property transactions API (either as a buyer or a broker) which is only accessible if a user is registered and correctly authenticated and has the rights to access the services offered by the application


## Project Dependency
This application is been built with Springboot 3.1.3 as at the time of development.
This project have the following dependencies
1. Spring web for building Restful APIs
2. Spring Security with Oauth resource server for access control with JWT (Json web Token for authentication)
3. Spring Data Jpa for data base manipulation
4. Flyway for Database migration and version
5. Mysql connector to connect with mysql data base
6. lombok for reducing boiler plate codes in my POJOs

## Use Cases of this application
This application has the following use cases:
1. Authentication and authorization of users of the application
2. Property Brokers can upload properties
3. Property buyers can buy properties
4. Application users can switch from broker to buyers if the need exists.


   
