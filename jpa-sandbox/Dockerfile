FROM maven:3.9.6-eclipse-temurin-21 as build
WORKDIR /app
COPY . .
RUN mvn clean package
RUN mv target/*.jar app.jar

FROM eclipse-temurin:21-jre
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/app.jar .
ENTRYPOINT ["java", "-jar", "app.jar"]
