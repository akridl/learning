package com.example.mapper;

import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 *
 */
@org.mapstruct.MapperConfig(
        componentModel = MappingConstants.ComponentModel.CDI,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.WARN
)
public class MapperConfig {
}
