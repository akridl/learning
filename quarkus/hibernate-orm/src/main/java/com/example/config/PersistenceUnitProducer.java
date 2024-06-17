package com.example.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceUnit;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hibernate.Session;

import java.util.List;

public class PersistenceUnitProducer {

    private final static String POSTGRES_PERSISTENCE_UNIT_NAME = "pg";
    private final static String H2_PERSISTENCE_UNIT_NAME = "h2";

    @ConfigProperty(name = "my-config.persistence-unit.available")
    List<String> availablePersistenceUnitsNames;

    @ConfigProperty(name = "quarkus.profile")
    public String persistenceUnitName;

    @Inject
    @PersistenceUnit(unitName = POSTGRES_PERSISTENCE_UNIT_NAME)
    Session pgSessionBean;

    @Inject
    @PersistenceUnit(unitName = H2_PERSISTENCE_UNIT_NAME)
    Session h2SessionBean;

    @Produces
    @ApplicationScoped
    public Session session() {
        if (persistenceUnitName.equals(POSTGRES_PERSISTENCE_UNIT_NAME)) {
            return pgSessionBean;
        } else if (persistenceUnitName.equals(H2_PERSISTENCE_UNIT_NAME)) {
            return h2SessionBean;
        }
        throw new IllegalArgumentException("Does not know persistence unit with name " + persistenceUnitName + ".\n" +
                "Available names are: " + String.join(", ", availablePersistenceUnitsNames));
    }
}
