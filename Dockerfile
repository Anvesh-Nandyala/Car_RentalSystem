FROM openjdk:24-ea-jdk

WORKDIR /app

# Copy all required jars
COPY lib/CarRentalSystem.jar /app/app.jar
COPY lib/postgresql-42.7.7.jar /app/postgresql-42.7.7.jar
COPY lib/java-dotenv-3.0.0.jar /app/java-dotenv-3.0.0.jar
COPY lib/kotlin-stdlib-1.3.72.jar /app/kotlin-stdlib-1.3.72.jar

COPY .env /app/.env

# Run the app with proper classpath
CMD ["java", "-cp", "app.jar:postgresql-42.7.7.jar:java-dotenv-3.0.0.jar:kotlin-stdlib-1.3.72.jar", "Controller.Main"]
