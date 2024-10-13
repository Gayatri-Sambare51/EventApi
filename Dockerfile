# First stage: build the application
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml ./   # Copy the pom.xml first for better caching
COPY src ./src    # Copy the source files
RUN mvn clean package -DskipTests

# Second stage: create a minimal image
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/target/EventApi-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
