# Micrometer quickstart

- tutorial link: https://quarkus.io/guides/telemetry-micrometer-tutorial

## Default metrics
- when we use the dependency `io.quarkus:quarkus-micrometer-registry-prometheus`, default metrics are exposed at `/q/metrics`

## Own metrics
- in addition to default metrics, we can add our own metrics by using `MeterRegistry`
- see an example in [PrimeResource](./src/main/java/com/example/rest/PrimeResource.java)
- then, we can see our configured metrics:
```shell
curl http://localhost:8080/primes/is-prime/-42
curl http://localhost:8080/primes/is-prime/0
curl http://localhost:8080/primes/is-prime/1
curl http://localhost:8080/primes/is-prime/2
curl http://localhost:8080/primes/is-prime/5
curl http://localhost:8080/primes/is-prime/10
curl http://localhost:8080/primes/is-prime/41
curl http://localhost:8080/primes/is-prime/42

curl http://localhost:8080/q/metrics | grep primes_number
# TYPE primes_number counter
# HELP primes_number  
primes_number_total{type="prime"} 2.0
primes_number_total{type="not-prime"} 2.0
primes_number_total{type="one"} 1.0
primes_number_total{type="two"} 1.0
primes_number_total{type="not-natural"} 1.0
primes_number_total{type="zero"} 1.0
```

## Gauges
- gauges are values which can increase and decrease over time, e.g.
  - types of builds triggered over time (hence, just increasing)
  - number of running builds (i.e. watching some size of the queue let's say)
  - see example usage in [BuildResource](./src/main/java/com/example/rest/BuildResource.java)
