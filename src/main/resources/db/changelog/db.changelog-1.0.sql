--liquibase formatted sql

--changeset jokerSharp:1

CREATE TABLE IF NOT EXISTS public.product
(
id uuid NOT NULL DEFAULT gen_random_uuid(),
name character varying NOT NULL,
price numeric(11,2) NOT NULL,
category integer NOT NULL,
quantity integer NOT NULL,
article integer,
creation_date timestamp without time zone NOT NULL,
quantity_change timestamp without time zone NOT NULL,
CONSTRAINT product_pkey PRIMARY KEY (id)
);