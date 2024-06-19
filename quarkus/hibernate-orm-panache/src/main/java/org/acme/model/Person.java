package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.acme.model.projection.PersonWithCompany;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQueries({
        @NamedQuery(name = "Person.getByGivenName", query = "SELECT p FROM Person p WHERE p.givenName = :givenName"),
        @NamedQuery(name = "Person.getByFullName", query = "SELECT p FROM Person p WHERE p.givenName = :givenName AND p.familyName = :familyName"),
        @NamedQuery(name = "Person.bornBefore", query = "SELECT p FROM Person p WHERE p.birthday < ?1")
})
public class Person extends PanacheEntity {

    public String givenName;

    public String familyName;

    public LocalDate birthday;

    @ManyToOne
    public Company company;

    public static PersonWithCompany findByGivenName(String name) {
        return find(
                "#Person.getByGivenName",
                Parameters.with("givenName", name)
        )
                .project(PersonWithCompany.class)
                .firstResult();
    }

    public static PersonWithCompany findByWholeName(String givenName, String familyName) {
        return find(
                "#Person.getByFullName",
                Parameters.with("givenName", givenName)
                        .and("familyName", familyName)
        )
                .project(PersonWithCompany.class)
                .firstResult();
    }
}
