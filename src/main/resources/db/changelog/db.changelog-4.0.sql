--liquibase formatted sql

--changeset jokerSharp:1

ALTER TABLE public.product
ADD is_available BOOLEAN NOT NULL DEFAULT TRUE;