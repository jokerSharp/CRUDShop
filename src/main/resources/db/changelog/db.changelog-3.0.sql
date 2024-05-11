--liquibase formatted sql

--changeset jokerSharp:1

INSERT INTO product(
	name, price, description, category, quantity, article, creation_date, quantity_change
	)
	VALUES
	('Couch', 100.0, 'new couch for you', 2, 300, 496549, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('Laptop', 1000.0, 'laptop for programing', 1, 100, 432198, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('Table', 300.0, 'work bench', 2, 50, 852375, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('Armchair', 500.0, 'comfortable armchair', 2, 100, 198432, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('Juice', 15.0, 'orange juice', 0, 100, 438698, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('Apple', 1.25, 'fresh apple', 0, 1000, 759341, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);