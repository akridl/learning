ALTER SEQUENCE person_seq INCREMENT BY 1;
INSERT INTO Person(id,givenName,familyName,birthday) VALUES
    (NEXTVAL('person_seq'), 'John', 'Doe', '2024-02-02'),
    (NEXTVAL('person_seq'), 'Jane', 'Doe', '2024-02-02');
INSERT INTO Company(name) VALUES ('Google Inc.');
