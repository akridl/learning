package com.example;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@Logged
@Priority(1000)
public class SecondLoggedInterceptor {

    Logger logger = LoggerFactory.getLogger("com.example._mylogger_");

    @AroundInvoke
    public Object interpose(InvocationContext context) throws Exception {
        logger.info("Yep, we doing difficult stuff before actual invocation");

        Object ret = context.proceed();

        logger.info("Result was: {}", ret);

        return ret;
    }
}
