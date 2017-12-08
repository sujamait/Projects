# Projects

1.
Project is using SpringBoot framework,MySQL database and other tools like maven to build and Docker to deploy in cloud.
Springboot makes it easy to create stand-alone applications with minimal configuration. 

2.
Testing and Documentation,
Testing is performed using JUnits and to test API's postman is used.
Import the Friends API.postman_collection_Testing collection for reference.
Also,Can view the API documentation using
Swagger UI API :
http://[hostname]:8080/swagger-ui.html

3.
Application is developed in layers starting from,
Controllers -> Services -> Database Layer.
Database is designed to overcome redundancy,Using single entry for single friend connection.
Exception handling,Validations and Logging is also implemented in the application.
Please,Note API can be accessed over the network and is not private/secured.

4.
How To Run:
4.1. Use terminal,
Maven package and spgroup application docker build:
cd [to project directory]

4.2. Run,make sure docker is installed and up in local to generate the docker image,
mvn clean package docker:build
Please note the public image can be pulled from public repository as well,
docker pull sujamait/spgroup:latest

4.3. Tag the docker image to docker cloud repository,to check view the docker image use docker images command on terminal.Please note to login to docker from terminal.

docker tag [Docker Image] [username docker cloudhub]/[name of repository] 
For Example,
docker tag spgroup_app_img:latest sujamait/spgroup 

4.4. Run,May encounter issues if port is allocated already.
docker-compose up

4.5. Verify the running process with 
docker ps
If everything is Ok,Can Use open below URL to test services.
http://hostname:8080/swagger-ui.html
Swagger is used for Documentation,changes in API are reflected automatically.



5.
In the Case,To build & Run the application locally and to Execute Junits.
-setup the database locally
-Run, mvn install command.
-Execute the generated spgroup-0.0.1-SNAPSHOT.jar using command,
java -jar spgroup-0.0.1-SNAPSHOT.jar
-set the maven maven.test.skip parameter to false to run junits.

