package com.example.providers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@ApplicationScoped
@Provider
@Slf4j
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String REQUEST_EXECUTION_START = "request-execution-start";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.setProperty(REQUEST_EXECUTION_START, System.currentTimeMillis());
        log.info("Requested {}", getMethodAndPath(requestContext));
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        Long startTime = (Long) requestContext.getProperty(REQUEST_EXECUTION_START);

        String took;
        if (startTime == null) {
            took = "-1";
        } else {
            took = Long.toString(System.currentTimeMillis() - startTime);
        }

        // If we wanted to have time metrics at this point, it would require parsing of the path
        // which wouldn't be that complicated by itself (requestContext.getUriInfo().getRequestUri().path)
        // but often, there are path parameters, and that would be hard to ignore from the path in a generic way
        // (since we want to measure time for e.g. /primes/is-prime as one unit, not /primes/is-prime/42, /primes/is-prime/5, etc.)
        log.info("Completed {}, took: {}ms", getMethodAndPath(requestContext), took);
    }

    private String getMethodAndPath(ContainerRequestContext requestContext) {
        return requestContext.getRequest().getMethod() + " " + requestContext.getUriInfo().getRequestUri();
    }
}
