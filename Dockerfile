# First stage: build the application
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml ./   
COPY src ./src    
RUN mvn clean package -DskipTests
EXPOSE 8080
# Second stage: create a minimal image
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/target/EventService-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
