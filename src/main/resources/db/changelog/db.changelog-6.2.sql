--liquibase formatted sql

--changeset jokerSharp:1

ALTER TABLE public.order_entity
ADD COLUMN total_price NUMERIC(11,2) NOT NULL;