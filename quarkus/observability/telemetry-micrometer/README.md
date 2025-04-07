# Telemetry Micrometer

## Counter
- used for a value which over time only increases

## Gauge
- unlike a counter, can also decrease
- handy e.g. for observing how a value evolves over time, for instance size of scheduler queue

## Distribution summary
- Timers and DSs store at least three values internally:
  - the aggregation of all recorded values as a sum
  - the number of values that have been recorded (a counter)
  - the highest value seen within a decaying time window (a gauge).
    - so in case we do define expiration of DS, it's applied only to this value, i.e. aggregation and number of values
      recorded will **NOT** be affected

- Example: let's have the following DS in which we are going to record sizes of responses:
```java
responseSizesSummary = DistributionSum        responseSizesSummary.record(response.length());
        return response;
mary.builder(DISTRIBUTION_SUMMARY_NAME)
                .description("Distribution summary for response sizes")
                .tag("protocol", "http")
                .register(micrometerRegistry);
```

- adding a record into the DS:
```java
String response = String.format("Hello %s!", name);
responseSizesSummary.record(response.length());
return response;
```
- for the whole example, see [DistributionSummaryResource](./src/main/java/com/example/DistributionSummaryResource.java)

- Example invocations:
```shell
╰─❯ curl -X POST http://localhost:8080/distribution-summary/hello\?name\=Joe
Hello Joe!%
╰─❯ curl -X POST http://localhost:8080/distribution-summary/hello\?name\=Jack
Hello Jack!%
╰─❯ curl -X POST http://localhost:8080/distribution-summary/hello\?name\=Genovieve
Hello Genovieve!%
```

- Results:
```shell
# TYPE my_ds_response_size_max gauge
# HELP my_ds_response_size_max Distribution summary for response sizes
my_ds_response_size_max{protocol="http"} 16.0
# TYPE my_ds_response_size summary
# HELP my_ds_response_size Distribution summary for response sizes
my_ds_response_size_count{protocol="http"} 3.0
my_ds_response_size_sum{protocol="http"} 37.0
```

## Timer
- it's similar to DS: also those 3 internal values are stored
- Example of creation of Timer:
```java
timerForDummy = Timer.builder(TIMER_NAME)
                .description("Timer for summers")
                .tag("type", "dummy")
                .register(micrometerRegistry);
```
- example usage:
```java
int sum = timerForDummy.record(() -> computeSumDummy(n));
return "dummy: " + sum;
```
- for the complete example, see [TimerResource](./src/main/java/com/example/TimerResource.java)

- Example invocations:
```shell
╰─❯ curl http://localhost:8080/timer/sum/dummy/42    
dummy: 903%
╰─❯ curl http://localhost:8080/timer/sum/dummy/42
dummy: 903%
╰─❯ curl http://localhost:8080/timer/sum/wise/42 
wisey: 903%
```

- Results:
```shell
# TYPE timer_sum_seconds_max gauge
# HELP timer_sum_seconds_max Timer for summers
timer_sum_seconds_max{type="wise"} 3.46131E-4
timer_sum_seconds_max{type="dummy"} 4.22497E-4
# TYPE timer_sum_seconds summary
# HELP timer_sum_seconds Timer for summers
timer_sum_seconds_count{type="wise"} 1.0
timer_sum_seconds_sum{type="wise"} 3.46131E-4
timer_sum_seconds_count{type="dummy"} 2.0
timer_sum_seconds_sum{type="dummy"} 7.74738E-4
```

## Automatically generated metrics
- Micrometer extension automatically creates Timer for HTTP server requests, i.e. there are automatic metrics for:
  - number of requests called (`http_server_requests_seconds_count`)
  - total sum (in seconds) of request time (`http_server_requests_seconds_sum`)
  - time of the longest execution (`http_server_requests_seconds_max`)
- dimensions are created based on HTTP methods, reponse status code, or HTTP URI path
- one can configure what's being measured and what not, e.g. `quarkus.micrometer.binder.http-server.ignore-patterns`

## Customizing Micrometer from Quarkus

### Use [MeterFilter](https://javadoc.io/doc/io.micrometer/micrometer-core/1.14.5/io/micrometer/core/instrument/config/MeterFilter.html) to customize emitted tags and metrics
- we can think of these filters as handlers per loggers: they filter emitted log events, modify them (e.g. formatting), etc.
- similarly, `MeterFilter` acts to metrics, it can:
  1) Deny (or Accept) meters being registered.
  2) Transform meter IDs (for example, changing the name, adding or removing tags, and changing the description or base units).
  3) Configure distribution statistics for some meter types.
- Micrometer extension will automatically detect `MeterFilter` CDI beans and use them when initializing `MeterRegistry`

- Example:
```java
@Singleton
public class CustomMeterRegistryConfig {

  @Produces
  @Singleton
  @MeterFilterConstraint(applyTo = PrometheusMeterRegistry.class)
  public MeterFilter automaticallyApplyPrometheusTags() {
    return MeterFilter.commonTags(Tags.of("registry", "prometheus"));
  }

  @Produces
  @Singleton
  public MeterFilter ignoreTags() {
    return MeterFilter.ignoreTags("ignored-tag");
  }
}
```

- Usage affected by both custom configurations:
```java
@Path("/ignored")
public class IgnoredTagResource {

  private final Counter myCounter;

  @Inject
  public IgnoredTagResource(MeterRegistry micrometerRegistry) {
    myCounter = Counter.builder("ignored-counter")
            .description("This counter has 1 tag ignored, search 'ignored-tag' in the project why :/")
            .tags(Tags.of("ignored-tag", "whatever-its-ignored-anyway", "non-ignored-tag", "wohooo"))
            .register(micrometerRegistry);
  }

  @GET
  public String doSomething() {
    myCounter.increment();
    return "done";
  }
}
```

- Invocation:
```shell
╰─❯ curl http://localhost:8080/ignored
done%
```

- Result:
```shell
# TYPE ignored_counter counter
# HELP ignored_counter This counter is ignored, search 'ignored-tag' in the project why :/
ignored_counter_total{non_ignored_tag="wohooo",registry="prometheus"} 1.0
```

- **Note:** tag `registry=prometheus` was automatically added, and `ignored-tag` is not present, even though present as one of the counter's tags

#### Use [MeterRegistryCustomizer](https://javadoc.io/doc/io.quarkus/quarkus-micrometer/3.21.1/io/quarkus/micrometer/runtime/MeterRegistryCustomizer.html) for arbitrary customizations to meter registries
- done by creating `MeterRegistryCustomizer` CDI beans, example:
```java
  @Produces
  @Singleton
  public MeterRegistryCustomizer customizeMicrometerRegistry() {
      return new MeterRegistryCustomizer() {
          @Override
          public void customize(MeterRegistry registry) {
              registry.config().meterFilter(MeterFilter.ignoreTags("yet-another-ignored-tag"));
          }

          @Override
          public int priority() {
              return MeterRegistryCustomizer.DEFAULT_PRIORITY;
          }
      };
  }
```
(from [CustomMeterRegistryCustomizers](./src/main/java/com/example/CustomMeterRegistryCustomizers.java))
