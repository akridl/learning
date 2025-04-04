package com.example.interceptor;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@TimeMeasured
@Priority(100)
public class TimeMeasuredInterceptor {

    @Inject
    MeterRegistry meterRegistry;

    @AroundInvoke
    Object interpose(InvocationContext context) throws Exception {
        Timer.Sample sample = Timer.start(meterRegistry);
        Object ret = context.proceed();
        String name = context.getMethod().getAnnotation(TimeMeasured.class).name();
        sample.stop(meterRegistry.timer(name, Tags.empty()));
        return ret;
    }
}
