package org.acme.rest;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.Company;
import org.acme.model.Person;
import org.acme.model.projection.PersonWithCompany;
import org.acme.model.projection.PersonWithoutBirthdayProjection;
import org.acme.rest.params.PageParameters;
import org.acme.rest.params.RangeParameters;

import java.util.List;

@Path("/persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class PersonResource {

    @POST
    public Person createPerson(Person person) {
        person.persist();
        return person;
    }

    @GET
    @Path("/{id}")
    public PersonWithCompany getPerson(@PathParam("id") Long id) {
        Person person = Person.findById(id);
        if (person.company != null) {
            Company company = Company.findById(person.company.id);
            var x = company.employees.isEmpty(); // lazy init
        }
        return Person.find("id", id).project(PersonWithCompany.class).firstResult();
    }

    @GET
    @Path("/given-name/{givenName}")
    public PersonWithCompany getPersonByGivenName(@PathParam("givenName") String givenName) {
        return Person.findByGivenName(givenName);
    }

    @GET
    @Path("/given-name/{givenName}/family-name/{familyName}")
    public PersonWithCompany getPersonByGivenName(
            @PathParam("givenName") String givenName,
            @PathParam("familyName") String familyName
    ) {
        return Person.findByWholeName(givenName, familyName);
    }

    @GET
    public List<PersonWithoutBirthdayProjection> getAllPersons() {
        return Person.findAll().project(PersonWithoutBirthdayProjection.class).list();
    }

    @GET
    @Path("/projected")
    public List<PersonWithoutBirthdayProjection> getPersonsProjected() {
        return Person.findAll().project(PersonWithoutBirthdayProjection.class).list();
    }

    @GET
    @Path("/ranged")
    public List<Person> getRanged(@BeanParam RangeParameters rangeParams) {
        return Person.findAll()
                .range(rangeParams.getOffset(), rangeParams.getOffset() + rangeParams.getLimit() - 1)
                .list();
    }

    @GET
    @Path("/paged")
    public List<PersonWithCompany> getPaged(@BeanParam PageParameters pageParams) {
        return Person.findAll()
                .page(pageParams.getPageIndex(), pageParams.getPageSize())
                .project(PersonWithCompany.class)
                .list();
    }

    @PUT
    @Path("/{id}")
    public PersonWithCompany updatePerson(@PathParam("id") Long id, Person person) {
        Person entity = Person.findById(id);
        if (entity == null) {
            throw new NotFoundException("Entity with id=" + id + " was not found.");
        }

        entity.givenName = person.givenName;
        entity.familyName = person.familyName;
        entity.birthday = person.birthday;

        entity.persist();
        return getPerson(id);
    }

    @DELETE
    @Path("/{id}")
    public void deletePerson(@PathParam("id") Long id) {
        Person entity = Person.findById(id);
        if (entity == null) {
            throw new NotFoundException("Entity with id=" + id + " was not found.");
        }

        Person.deleteById(id);
    }

    @POST
    @Path("/{id}/company/assign/{companyId}")
    public PersonWithCompany assignCompany(@PathParam("id") Long id, @PathParam("companyId") Integer companyId) {
        Person person = Person.findById(id);
        if (person == null) {
            throw new NotFoundException("Person with id=" + id + " was not found.");
        }

        Company company = Company.findById(companyId);
        if (company == null) {
            throw new NotFoundException("Company with id=" + companyId + " was not found.");
        }

        person.company = company;
        company.employees.add(person);
        return Person.find("id", id).project(PersonWithCompany.class).firstResult();
    }

    @DELETE
    @Path("/{id}/company/unassign")
    public PersonWithCompany unassignCompany(@PathParam("id") Long id) {
        Person person = Person.findById(id);
        if (person == null) {
            throw new NotFoundException("Person with id=" + id + " was not found.");
        }

        if (person.company == null) {
            throw new IllegalArgumentException("Person with id=" + id + " does not have any company assigned.");
        }

        Company company = Company.findById(person.company.id);
        company.employees.remove(person);
        person.company = null;

        return Person.find("id", id).project(PersonWithCompany.class).firstResult();
    }
}
