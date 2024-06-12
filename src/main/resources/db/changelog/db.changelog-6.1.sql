--liquibase formatted sql

--changeset jokerSharp:1

CREATE TABLE IF NOT EXISTS public.order_total
(
    order_id BIGINT NOT NULL REFERENCES public.order_entity(id),
    product_id UUID NOT NULL REFERENCES public.product(id),
    quantity INTEGER CHECK(quantity > 0) NOT NULL,
    subtotal NUMERIC(11,2) NOT NULL,
    CONSTRAINT order_product_pkey PRIMARY KEY (order_id, product_id)
);