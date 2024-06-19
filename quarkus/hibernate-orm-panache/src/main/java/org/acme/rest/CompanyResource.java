package org.acme.rest;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.model.Company;
import org.acme.model.projection.PersonWithCompany;

@Path("/companies")
@Transactional
public class CompanyResource {

    @POST
    public Company createCompany(String name) {
        Company company = new Company();
        company.setName(name);
        company.persist();
        return company;
    }

    @GET
    @Path("/{id}")
    public Company getCompanyById(@PathParam("id") Integer id) {
        Company company = Company.findById(id);
        System.out.println(company.employees);  // lazy loading required
        return company;
    }

    @GET
    @Path("/{id}/projected")
    public PersonWithCompany.CompanyProjected getCompanyByIdProjected(@PathParam("id") Integer id) {
        // no lazy loading required (we don't need employees)
        return Company.find("id", id).project(PersonWithCompany.CompanyProjected.class).firstResult();
    }
}
