package com.example;

import jakarta.annotation.Priority;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@LoggedToUser
@Priority(100)
public class UserLoggerInterceptor {

    @Inject
    Event<AlignmentSubOperation> event;

    @AroundInvoke
    public Object interfere(InvocationContext context) throws Exception {
        event.fire(context.getMethod().getAnnotation(LoggedToUser.class).value());
        return context.proceed();
    }
}
