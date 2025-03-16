# Build Stage - Uses Eclipse Temurin JDK with Maven
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Install Maven (if needed)
RUN apt update && apt install -y maven

# Copy Maven project files
COPY . .

# Run Maven clean install to build the JAR file
RUN mvn clean install -DskipTests

# Runtime Stage - Uses a lightweight Eclipse Temurin JDK
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
