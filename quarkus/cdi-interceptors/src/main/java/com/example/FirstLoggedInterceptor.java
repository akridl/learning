package com.example;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

@Interceptor
@Logged
@Priority(100)
@Slf4j
public class FirstLoggedInterceptor {

    @AroundInvoke
    Object interpose(InvocationContext context) throws Exception {
        log.info("[FirstLoggedInterceptor] doing stuff before actual invocation takes place");

        Object ret = context.proceed();

        log.info("[FirstLoggedInterceptor] return value was: {}", ret);
        return ret;
    }
}
