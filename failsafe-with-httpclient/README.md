# [Failsafe](https://failsafe.dev/) with [HttpClient](https://docs.oracle.com/en/java/javase/21/docs/api/java.net.http/java/net/http/HttpClient.html)

## Description
- sample demo, which uses _HttpClient_ for the communication between microservices (in this case only _client_ -> _server_)
    - in order to increase the resiliency, _HttpClient_ is wrapped around _Failsafe_

## Architecture
- there are 2 modules:
  - client
    - has the only endpoint `POST /adjust`, which receives the request, in which is also the callback
    - this callback is then invoked
    - e.g.
    ```shell
    curl -X 'POST' 'http://localhost:8088/adjust' -H 'Content-Type: application/json' -d '{ "data": "Hello there", "callback": { "method": "POST", "url": "http://localhost:8080/operation/complete" } }'
    ```
  - server
    - has the only endpoint `POST /operation/complete`, which just prints something
