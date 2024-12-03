package com.example;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.jboss.pnc.api.constants.MDCKeys;
import org.jboss.pnc.common.log.MDCUtils;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Provider
@Slf4j
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String REQUEST_EXECUTION_START = "request-execution-start";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        MDC.clear();

        log.debug("*** HTTP Header values");
        Set<Map.Entry<String, List<String>>> entries = requestContext.getHeaders().entrySet();
        entries.forEach(entry -> log.debug("[HTTP Header] key={}, value={}", entry.getKey(), entry.getValue()));

        requestContext.setProperty(REQUEST_EXECUTION_START, System.currentTimeMillis());
        MDCUtils.setMDCFromRequestContext(requestContext);

        UriInfo uriInfo = requestContext.getUriInfo();
        Request request = requestContext.getRequest();
        log.info("Requested {} {}.", request.getMethod(), uriInfo.getRequestUri());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        Long startTime = (Long) requestContext.getProperty(REQUEST_EXECUTION_START);

        String took;
        if (startTime == null) {
            took = "-1";
        } else {
            took = Long.toString(System.currentTimeMillis() - startTime);
        }

        try (MDC.MDCCloseable mdcTook = MDC.putCloseable(MDCKeys.REQUEST_TOOK, took);
             MDC.MDCCloseable mdcStatus = MDC
                     .putCloseable(MDCKeys.RESPONSE_STATUS, Integer.toString(responseContext.getStatus()));) {
            log.debug("Completed {}, took: {}ms.", requestContext.getUriInfo().getPath(), took);
        }
    }
}
