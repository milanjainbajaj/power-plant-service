FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine as builder
ARG JAR_FILE=target/power-1.0.0.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "/app.jar"]