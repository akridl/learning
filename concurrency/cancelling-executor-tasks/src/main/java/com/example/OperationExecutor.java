package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.context.ManagedExecutor;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
@Slf4j
public class OperationExecutor {

    private final ManagedExecutor executor;
    private final Map<Integer, Future<?>> tasks = new ConcurrentHashMap<>();
    private final static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    @ConfigProperty(name = "app-config.operations.duration.runtime")
    int runtime;

    @ConfigProperty(name = "app-config.operations.duration.schedule-in")
    int scheduleIn;

    @Inject
    public OperationExecutor(ManagedExecutor executor) {
        this.executor = executor;
    }

    Integer start() {
        Integer taskId = TaskIDGenerator.generateTaskID();
        scheduledExecutor.schedule(() -> {
            log.info("Gonna start task with ID={} in {} seconds", taskId, scheduleIn);
            CompletableFuture<Integer> completableFuture = executor.supplyAsync(() -> {
                log.info("Operation with ID={} started", taskId);
                try {
                    Thread.sleep(Duration.of(runtime, ChronoUnit.SECONDS));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 42;
            }).thenApply(res -> {
                tasks.remove(taskId);
                log.info("Operation with ID={} was being completed with the result: {}", taskId, res);
                return res;
            });
            tasks.put(taskId, completableFuture);
        }, scheduleIn, TimeUnit.SECONDS);

        return taskId;
    }

    boolean abort(Integer taskId) {
        Future<?> future = tasks.get(taskId);
        if (future == null) {
            throw new TaskNotRunningException(String.format("Task with ID=%d is not running. Either, it has been already finished, aborted, or it never existed.", taskId));
        }

        future.cancel(true);
        if (future.isCancelled()) {
            tasks.remove(taskId);
            log.info("Operation with ID={} aborted", taskId);
            return true;
        }

        return false;
    }
}
