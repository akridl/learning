package com.example;

import com.example.common.CallbackSender;
import com.example.dto.AdjustRequest;
import com.example.facade.AdjustProvider;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/adjust")
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class AdjustResource {

    private final ManagedExecutor executor;
    private final AdjustProvider provider;
    private final CallbackSender callbackSender;

    @Inject
    public AdjustResource(ManagedExecutor executor, AdjustProvider provider, CallbackSender callbackSender) {
        this.executor = executor;
        this.provider = provider;
        this.callbackSender = callbackSender;
    }

    @POST
    @ResponseStatus(202)
    public void adjust(AdjustRequest request) {
        log.info("Starting adjustment..");
        executor.execute(
                () -> {
                    log.info(provider.adjust(request.getData()));
                    callbackSender.sendCallback(request.getCallback());
                }
        );
    }
}
