package org.acme.model.projection;

import io.quarkus.hibernate.orm.panache.common.NestedProjectedClass;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class PersonWithCompany {

    public final Long id;
    public final String givenName;
    public final String familyName;
    public final CompanyProjected company;

    /**
     * {@link NestedProjectedClass does not support {@link jakarta.persistence.OneToMany} (at least at the this time)}
     */
    @NestedProjectedClass
    @RequiredArgsConstructor
    public static class CompanyProjected {
        public final Integer id;
        public final String name;
    }
}
