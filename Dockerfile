# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the jar file into the container (update the path if different)
COPY out/artifacts/CarRentalSystem_jar/CarRentalSystem.jar app.jar

# Expose app port (you can remove this if it's a CLI app)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
