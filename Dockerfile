FROM openjdk:22-jdk

COPY target/ExampleDeploy-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]