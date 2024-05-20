package com.example;

import com.example.annotations.MyPrimary;
import com.example.translator.Translator;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Path("/translate")
@Produces(MediaType.TEXT_PLAIN)
public class TranslatorResource {

    private final Translator translator;

    @Inject
    // Note:
    // public TranslatorResource(Translator translator) {
    // would **NOT** work, since it defaults to '@Default Translator translator', which results
    // in ambiguous bean injection (DummyTranslator and SuperIntelligentTranslator)
    public TranslatorResource(@MyPrimary Translator translator) {
        this.translator = translator;
    }

    @POST
    @Path("/{language}")
    @Operation(summary = "Translate a word from Slovak language")
    public String translate(@PathParam("language") Language language, @Schema(example = "Ahoj") String text) {
        return translator.translate(text, language);
    }

    @GET
    public String doNothing() {
        translator.doNothing();
        return "Did nothing";
    }
}
