FROM openjdk:21
ADD target/book-backend-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT java -jar /app.jar