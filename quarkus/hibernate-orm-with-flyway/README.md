# hibernate-orm-with-flyway

Tutorial link: https://quarkus.io/guides/hibernate-orm#flyway

# What is it about?
Quarkus supports flyway, as one of its migration options. Hibernate ORM together with flyway extension allows us to
bootstrap the project even faster:
1) Configure the datasource
2) Link flyway to use that datasource
3) Go to Dev UI, generate the initial flyway stuff, it creates:
- `resources/db/migration`
- `V1.0.0__<project_name>.sql`, which contains DDL, where it creates tables based on our `@Entity` classes
  (including relationships between them - FKs)
