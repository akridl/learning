# Wiremock + Failsafe + HttpClient

## Description

Simple demo using _HttpClient_ for communication between microservices (in this case _client_ -> _server_).
For resiliency, _Failsafe_ is used. For tests, _WireMock_ is used.

## How to run this
- Server: navigate to the directory and `quarkus dev` is enough
- Client: analogous to server one

**Note:** Since _Failsafe_ is used for resiliency, we can play with [configs](client/src/main/java/com/example/config/CallbackConfig.java) and unavailability of the server (it's nice to have the server pre-compiled in such case, and run it simply by `java -jar target/quarkus-app/quarkus-run.jar`).
