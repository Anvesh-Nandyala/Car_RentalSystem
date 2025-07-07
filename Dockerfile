# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the jar file into the container (ensure the path is correct)
COPY lib/CarRentalSystem.jar /app/app.jar

# Run the app (no need to expose ports since it's CLI)
CMD ["java", "-jar", "app.jar"]
