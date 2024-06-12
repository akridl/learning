-- Person
INSERT INTO Person(name) VALUES
    ('John Doe'),
    ('Jane Doe');

-- Greeting
INSERT INTO Greeting VALUES
    (NEXTVAL('greeting_id_seq'), 'Hello'),  -- Apparently, sequence generator does not create default values out of the box
    (NEXTVAL('greeting_id_seq'), 'Čahoj');
