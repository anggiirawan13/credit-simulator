FROM openjdk:17-jdk-slim

LABEL authors="Anggi Irawan a.k.a 0xGong"

WORKDIR /app

COPY target/*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
