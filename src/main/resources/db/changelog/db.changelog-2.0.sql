--liquibase formatted sql

--changeset jokerSharp:1

ALTER TABLE public.product
ALTER COLUMN name TYPE VARCHAR(128),
ADD UNIQUE (name),
ADD description VARCHAR(256),
ALTER COLUMN article SET NOT NULL,
ADD UNIQUE (article);