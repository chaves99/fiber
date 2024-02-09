FROM eclipse-temurin:17.0.6_10-jdk-focal

ENV base_url=$base_url \
    username=$username \
    password=$password \
    base_platform=$base_platform

COPY .mvn .mvn
COPY ./mvnw ./mvnw
COPY ./src ./src
COPY ./pom.xml .

RUN ["./mvnw", "install", "package"]

EXPOSE 8989

ENTRYPOINT ["java", "-jar", "./target/fiber-0.0.1-SNAPSHOT.jar"]
