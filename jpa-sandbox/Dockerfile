FROM maven:3.9.9 as build
WORKDIR /app
COPY . .
RUN mvn clean package
RUN mv target/*.jar app.jar

FROM eclipse-temurin:21-jre
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/app.jar .
COPY target/jpa-sandbox.jar /app/app.jar
RUN mkdir config/
COPY etc/ config/
COPY entrypoint.sh .
ENTRYPOINT ["./entrypoint.sh"]
