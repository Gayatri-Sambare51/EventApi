# Use the official Maven image to build the app
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use the official OpenJDK 17 image to run the app
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/EventApi-0.0.1-SNAPSHOT.jar /usr/local/lib/EventApi.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/EventApi.jar"]
