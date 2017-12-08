# **Project**

1. Project is using SpringBoot framework,MySQL database and            tools like maven to build and Docker to deploy in cloud.
Springboot makes it easy to create stand-alone applications with minimal configuration. 

2. Testing and Documentation,
Testing is performed using JUnits for controller, services and database and also, to test API postman is used.
You may import the Friends API.postman_collection_Testing collection file for reference.
Interactive API documentation is available at below link.
Swagger UI API :
http://[hostname]:8080/swagger-ui.html

3. Application is developed in layers starting from,
Controllers to Services to Database Layer.
Database is designed to overcome redundancy, using single entry to save a friend connection.
Exception handling, Validations and Logging is also implemented in the application.


4. **Getting Started:**

 These instructions will get you a copy of the project up and running  on your local machine or cloud for development and testing purposes. See notes below on how to deploy the project.

   4.1.    Use terminal,
                           

         cd [to project directory]

   4.2.  Make sure docker is installed and up in local to generate the          docker image.Run,

         mvn clean package docker:build

    Please note the public shared image can be pulled from public repository   as well,
    
 

        docker pull sujamait/spgroup:latest

 4.3. Tag the docker image to docker cloud repository after login from    terminal.

        docker tag [Docker Image] [username]/[repository] 

    For Example,

 

        docker tag spgroup_app_img:latest sujamait/spgroup

 

 4.4.     Run below command to Build, (re)create, start, and attache to  containers for a service.

        docker-compose up

  4.5.  Verify the running process with 

          

        docker ps

    If everything is ok, click below URL to test services.
     http://hostname:8080/swagger-ui.html
     Swagger is used for Documentation, and  changes in API are reflected     automatically.



5. To build and Run the application locally and Execute Junits.
    5.1.  Setup the database locally.
   5.2. Run command, 
   

        mvn install
    5.3. Execute the generated spgroup-0.0.1-SNAPSHOT.jar using command,
 

        java -jar spgroup-0.0.1-SNAPSHOT.jar

   5.4. Set the maven maven.test.skip parameter to false to run junits.




