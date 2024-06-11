package com.example;

import io.quarkus.smallrye.openapi.OpenApiFilter;
import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.info.Info;

@OpenApiFilter(OpenApiFilter.RunStage.RUN)
public class MyBuildTimeFilter implements OASFilter {

    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        Info info = OASFactory.createInfo();
        info.setDescription("Created from annotated OpenAPI filter");
        openAPI.setInfo(info);
    }
}
