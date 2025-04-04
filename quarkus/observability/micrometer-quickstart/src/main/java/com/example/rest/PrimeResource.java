package com.example.rest;

import com.example.interceptor.TimeMeasured;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/primes")
@Produces(MediaType.TEXT_PLAIN)
public class PrimeResource {

    @Inject
    MeterRegistry meterRegistry;

    // At metrics, we can find metrics related to this resource under the "primes_number" tag
    private static final String METRICS_NAME = "primes.number";

    @GET
    @Path("/is-prime/{number}")
    @TimeMeasured(name = METRICS_NAME)
    public String isPrime(@PathParam("number") long number) {
        if (number < 0) {
            meterRegistry.counter(METRICS_NAME, "type", "not-natural").increment();
            return "Only natural numbers can be primes.";
        }

        if (number <= 2) {
            final String tag;
            if (number == 0) tag = "zero";
            else if (number == 1) tag = "one";
            else tag = "two";

            meterRegistry.counter(METRICS_NAME, "type", tag).increment();
            return "Number " + number + " is not a prime number.";
        }

        boolean isPrime = testIsPrime(number);
        meterRegistry.counter(METRICS_NAME, "type", (isPrime) ? "prime" : "not-prime").increment();
        return "Number " + number + " is " + (isPrime ? "" : "not ") + "a prime number.";
    }

    private boolean testIsPrime(long number) {
        for (int d = 2; d <= Math.floor(Math.sqrt(number)); d++) {
            if (number % d == 0) {
                return false;
            }
        }

        return true;
    }
}
