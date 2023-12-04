# Use an official OpenJDK runtime as a base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/dream-score-1.0.0.jar /app/dream-score.jar

# Specify the command to run DreamScore when the container is started
CMD ["java", "-jar", "dream-score.jar","--spring.profiles.active=prod"]

# Expose port 8080 for the application
EXPOSE 8080