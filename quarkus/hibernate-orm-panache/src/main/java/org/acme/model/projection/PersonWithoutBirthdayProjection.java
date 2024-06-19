package org.acme.model.projection;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonWithoutBirthdayProjection {

    Long id;

    String givenName;

    String familyName;
}
