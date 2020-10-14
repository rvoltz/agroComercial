CREATE TABLE public.componente_preco_tipo_fretes
(
    componente_preco_id uuid NOT NULL,
    tipo_frete_id integer NOT NULL,
    CONSTRAINT uk_componente_preco_tipo_fretes UNIQUE (componente_preco_id, tipo_frete_id),
    CONSTRAINT fk_componente_preco_tipo_fretes FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public.componente_preco_finalidades
(
    componente_preco_id uuid NOT NULL,
    finalidades_id integer NOT NULL,
    CONSTRAINT uk_componente_preco_finalidades UNIQUE (componente_preco_id, finalidades_id),
    CONSTRAINT fk_componente_preco_finalidades FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public.componente_preco_itens
(
    componente_preco_id uuid NOT NULL,
    item_id integer NOT NULL,
    CONSTRAINT uk_componente_preco_itens UNIQUE (componente_preco_id, item_id),
    CONSTRAINT fk_componente_preco_itens FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);
