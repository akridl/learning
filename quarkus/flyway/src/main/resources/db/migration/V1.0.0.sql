CREATE TABLE Person
(
    id         BIGINT,
    familyName TEXT,
    age        INTEGER,
    PRIMARY KEY (id)
);

INSERT INTO Person(id, familyName, age) VALUES
(1, 'Doe', 42),
(2, 'Doe', 39);
