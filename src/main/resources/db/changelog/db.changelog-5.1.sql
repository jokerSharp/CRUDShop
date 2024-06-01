--liquibase formatted sql

--changeset jokerSharp:1

INSERT INTO customer(
	name, email
	)
	VALUES
	('John Doe', 'john.doe@gmail.com'),
	('Christy Richardson', 'c.richardson@mail.com'),
	('Andrew Williams', 'will.and@engineer.com');