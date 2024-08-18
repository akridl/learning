package com.example.demos.highlevel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletionStageDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** CompletionStage demo *****");

        basic();
        intermediate();
    }

    private static void basic() {
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("John Doe").thenApply(name -> "Hello " + name).thenApply(s -> {
            System.out.println("The result is: " + s);
            return s;
        });
        String res = completableFuture.join();
        System.out.println("Obtained result: " + res);
    }

    private static void intermediate() {
        CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture("John")
                .thenApplyAsync(name -> "Hello " + name, CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS))
                // greeting will be "Hi Emily" (since "Hello John" will finish in slightly above 3 seconds)
                .applyToEither(CompletableFuture.completedFuture("Hi Emily"), (greeting) -> { throw new IllegalArgumentException("WTF?! " + greeting); })
                .whenComplete((greeting, exception) -> {
                    if (exception == null) {
                        System.out.println("Successfully completed with the result: " + greeting);
                    } else {
                        System.err.println("Oops! " + exception.getMessage());
                    }
                });
        System.out.println("Future is completed: " + completableFuture.isDone());
        System.out.println("Future completed exceptionally: " + completableFuture.isCompletedExceptionally());
        try {
            completableFuture.join();
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Muhahaaa");
        }
    }
}
