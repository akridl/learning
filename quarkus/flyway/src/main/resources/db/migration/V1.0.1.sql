ALTER TABLE person ADD COLUMN givenName TEXT;

UPDATE person SET givenName = 'John' WHERE id = 1;
UPDATE person SET givenName = 'Jane' WHERE id = 2;
