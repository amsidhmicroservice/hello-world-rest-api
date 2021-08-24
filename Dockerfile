FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/hello-world-rest-api-*.jar hello-world-rest-api.jar
ENTRYPOINT ["java","-jar","hello-world-rest-api.jar"]