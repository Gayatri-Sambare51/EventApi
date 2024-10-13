# Use the official Maven image with OpenJDK 17
FROM maven:3.9.9-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Use a lighter OpenJDK image to run the application
FROM openjdk:17-slim
COPY --from=build /app/target/EventApi-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
