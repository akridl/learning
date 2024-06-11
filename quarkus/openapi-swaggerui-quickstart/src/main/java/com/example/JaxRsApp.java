package com.example;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(
        info = @Info(
                title = "Person Resource",
                description = "Super-ultra-cool person resource",
                version = "42.42.42",
                contact = @Contact(
                        name = "Random Guy",
                        email = "idc@example.com"
                )
        )
)
public class JaxRsApp extends Application {

}
