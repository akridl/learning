package com.example.rest;

import com.example.dto.BuildDto;
import com.example.dto.CreateBuildDto;
import com.example.enums.BuildType;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.concurrent.atomic.AtomicInteger;

@Path("/build")
public class BuildResource {

    private final AtomicInteger mvnBuildsCounter = new AtomicInteger();
    private final AtomicInteger gradleBuildsCounter = new AtomicInteger();
    private final AtomicInteger scalaBuildsCounter = new AtomicInteger();

    private final static String BUILD_COUNTS = "build.counts";
    private final static String BUILD_TYPE = "type";

    @Inject
    public BuildResource(MeterRegistry meterRegistry) {
        meterRegistry.gauge(BUILD_COUNTS, Tags.of(BUILD_TYPE, BuildType.MVN.name()), mvnBuildsCounter);
        meterRegistry.gauge(BUILD_COUNTS, Tags.of(BUILD_TYPE, BuildType.GRADLE.name()), gradleBuildsCounter);
        meterRegistry.gauge(BUILD_COUNTS, Tags.of(BUILD_TYPE, BuildType.SCALA.name()), scalaBuildsCounter);
    }

    @POST
    @Path("/trigger")
    public BuildDto startBuild(CreateBuildDto createBuildDto) {
        BuildType buildType = createBuildDto.getBuildType();
        switch (buildType) {
            case MVN -> mvnBuildsCounter.incrementAndGet();
            case GRADLE -> gradleBuildsCounter.incrementAndGet();
            case SCALA -> scalaBuildsCounter.incrementAndGet();
        }

        // building...
        return BuildDto.builder().id("42").buildType(buildType).build();
    }
}
