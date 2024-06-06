--liquibase formatted sql

--changeset jokerSharp:1

CREATE TABLE IF NOT EXISTS public.order_entity
(
    id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    customer_id BIGINT NOT NULL REFERENCES public.customer(id),
    status INTEGER NOT NULL,
    CONSTRAINT order_entity_pkey PRIMARY KEY (id)
);

ALTER TABLE public.customer
ADD COLUMN order_id BIGINT REFERENCES public.order_entity(id);

CREATE TABLE IF NOT EXISTS public.order_product
(
    order_id BIGINT NOT NULL REFERENCES public.order_entity(id),
    product_id UUID NOT NULL REFERENCES public.product(id),
    CONSTRAINT order_product_pkey PRIMARY KEY (order_id, product_id)
);