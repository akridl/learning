# Kubernetes probes + Quarkus combo
Sample demo using quarkus, in particular [smallrye-health](https://quarkus.io/extensions/io.quarkus/quarkus-smallrye-health/)
extension, see e.g. [Smallrye Health quarkus guide](https://quarkus.io/guides/smallrye-health).

## Quarkus part
From the quarkus perspective, it's sufficient to just add the dependency `smallrye-health`, which will out of the box
expose several paths, which can kubelet then use in order to monitor the container and decide for instance whether to
terminate the pod.

We can configure our own liveness probe like this:
```java
@ApplicationScoped
@Liveness
@Slf4j
public class MyLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        log.info("Returning UP on Liveness check");
        return HealthCheckResponse.up("Liveness");
    }
}
```
Such a probe is by default exposed at `/q/health/live`. However, because of
`quarkus.smallrye-health.root-path=zdravicko` in the [application.yaml](./src/main/resources/application.yaml#L6), this one is exposed at `/q/zdravicko/live`.

**NOTE:** By default Smallrye Health returns at these predefined exposed paths the following DTO (since no checks are
defined yet):
```json
{
    "status": "UP",
    "checks": [
    ]
}
```

## Kubernetes part
Under `Pod.spec.containers`, we can define the probes, see [kubernetes/deployment.yaml](./kubernetes/deployment.yaml#L22).

**NOTE:** Even though we do not define explicitly, kubernetes somehow knows that it should use e.g. `/q/zdravicko/live`.
Hence, it does not kill the container (in this case).

**NOTE 2:** Kubelet will kill the pod in case the `Pod.spec.containers.livenessProbe.httpGet` is incorrectly specified,
or in case we are not using `smallrye-health` at all (quite obviously, since the required endpoints are not exposed at
all).
