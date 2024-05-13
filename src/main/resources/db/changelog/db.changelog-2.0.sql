--liquibase formatted sql

--changeset jokerSharp:1

ALTER TABLE public.product
ALTER COLUMN name TYPE VARCHAR(128);
ALTER TABLE public.product
ADD UNIQUE (name);
ALTER TABLE public.product
ADD description VARCHAR(256);
ALTER TABLE public.product
ALTER COLUMN article SET NOT NULL;
ALTER TABLE public.product
ADD UNIQUE (article);