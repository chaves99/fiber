FROM eclipse-temurin:17.0.6_10-jdk-focal

COPY .mvn .mvn
COPY ./mvnw .
COPY ./src ./src
COPY ./pom.xml .

RUN ["./mvnw", "install", "package"]

EXPOSE 8989

ENTRYPOINT ["java", "-jar", "./target/fiber-0.0.1-SNAPSHOT.jar"]
