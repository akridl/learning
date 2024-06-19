package org.acme.rest.params;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import org.jboss.resteasy.reactive.RestQuery;

@Getter
public class RangeParameters {

    @RestQuery
    @Positive
    int limit;

    @RestQuery
    @PositiveOrZero
    int offset;
}
