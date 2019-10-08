FROM openjdk:8

WORKDIR /usr/app

ADD target/lagoon-backend.jar lagoon-backend.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","lagoon-backend.jar"]

# Make port 8080 available to the world outside this container
EXPOSE 8080