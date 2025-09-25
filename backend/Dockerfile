FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests package || true
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/doctor-booking-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV DB_URL=jdbc:postgresql://localhost:5432/doctor_booking DB_USER=postgres DB_PASS=postgres
CMD ["java", "-jar", "app.jar"]