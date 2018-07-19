
# Problem Statement
The transactions are posted in unix timestamp in epoch millis with some amount at endpoint **/transactions**. The timestamp can be as old as possible.The statistics are fetched for timestamp with last 60 seconds from the current time of the endpoint **/statistics** called.
# Prerequisite

* Java-8
* Maven 3.x

# Building
    mvn clean install
  
# Running
      1. mvn clean spring-boot:run 
      2. cd web.controllers/target && java -jar web.controllers-1.0-SNAPSHOT.jar
 Run with either Option 1 **OR** Option 2
  
# Technology Stack
* Java-8
* Spring-Boot
* Mockito

# Design
* The project is self explainatory and follows standard as per development process. 
* Eventually every maven module can be made as SpringBoot Application enabled.
* A Scheduler run every 5 seconds to clean (transactions above 60 seconds timestamp) the Inmemory store to keep it optimum.



# Code Completion/Testing Criteria

* Unit Tested
* Basic Validations which returns 400 Bad Request as Status codes and verbose message. Note that this is just vanilla error message and not meant be considered as for production level code.



