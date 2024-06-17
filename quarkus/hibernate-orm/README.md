# hibernate-orm

Tutorial links:
- https://quarkus.io/guides/hibernate-orm
- https://quarkus.io/guides/config-yaml

# Persistence configuration

In order to configure the persistence, we can use 2 approaches:
1) using `quarkus.hibernate-orm.*` in `application.[properties|yaml]` (which is the preferred one)
2) using legacy way through `META-INF/persistence.xml`

## Modern approach
Support from quarkus is a little better in here, e.g. it supports several datasources. Configuration properties can be
found [here](https://quarkus.io/guides/hibernate-orm#hibernate-configuration-properties).

In case one has got defined also `META-INF/persistence.xml`, in order for properties to not collide, we have to restrict
configuration via XML, we do so by:
```bash
quarkus.hibernate-orm.persistence-xml.ignore=true
```

## Legacy approach
Do not forget to:
- comment out all the config properties from `application.yml`
- set the default datasource under `quarkus.datasource` accordingly
- set `jakarta.persistence.schema-generation.database.action` value in both active and inactive PUs accordingly
