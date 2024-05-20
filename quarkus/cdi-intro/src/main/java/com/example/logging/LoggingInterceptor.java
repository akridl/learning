package com.example.logging;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

@Logged
@Interceptor
@Slf4j
public class LoggingInterceptor {

    @AroundInvoke
    Object logInvocation(InvocationContext context) throws Exception {
        String methodName = context.getMethod().toString();

        log.debug("{} starting", methodName);
        long startTime = System.nanoTime();
        Object invocationResult = context.proceed();
        long endTime = System.nanoTime();
        log.debug("{} ended", methodName);
        log.info("{} called (lasted {} ms)", methodName, (endTime - startTime) / 1000);

        return invocationResult;
    }
}
