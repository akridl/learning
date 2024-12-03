# MDC propagations

Sample application showing the MDC propagation through HTTP headers.

## Client

Puts into MDC 4 key-value pairs:
```java
MDC.put("foo", "bar");
MDC.put("bar", "baz");

MDC.put(MDCHeaderKeys.TMP.getHeaderName(), "tmp-foo");
MDC.put(MDCHeaderKeys.PROCESS_CONTEXT.getHeaderName(), "process-foo");
```

All MDC values are propagated through HTTP Headers:
```java
HttpRequest request = HttpRequest.newBuilder()
        .POST(HttpRequest.BodyPublishers.noBody())
        .uri(URI.create("http://localhost:8090/hello"))
        .headers(putMdcIntoHeaders())
        .build();
```

## Server

Server in its request filter fetches some of the headers (only the headers from `MDCHeaderKeys` are taken) and updates
its MDC:
```java
MDCUtils.setMDCFromRequestContext(requestContext);
```
