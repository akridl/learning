package com.example;

import com.example.common.CallbackSender;
import com.example.dto.AdjustRequest;
import com.example.facade.AdjustProvider;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.context.ManagedExecutor;

@Path("/adjust")
@Slf4j
public class AdjustResource {

    private final AdjustProvider provider;
    private final ManagedExecutor executor;
    private final CallbackSender callbackSender;

    @Inject
    public AdjustResource(AdjustProvider provider, ManagedExecutor executor, CallbackSender callbackSender) {
        this.provider = provider;
        this.executor = executor;
        this.callbackSender = callbackSender;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void adjust(AdjustRequest request) {
        log.info("Starting adjust operation, got request: {}", request);
        executor.execute(() -> {
            String adjustedText = provider.adjust(request.getText());
            callbackSender.sendCallback(request.getCallback(), adjustedText);
        });
    }
}
