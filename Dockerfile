FROM adoptopenjdk/openjdk11:alpine-slim
VOLUME /tmp
COPY target/hello-world-rest-api-*.jar hello-world-rest-api.jar
ENTRYPOINT ["java","-jar","hello-world-rest-api.jar"]