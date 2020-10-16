-- Table: public.componente_preco

-- DROP TABLE public.componente_preco;

CREATE TABLE IF NOT EXISTS public.componente_preco
(
    id uuid NOT NULL,
    aplicacao integer,
    ativo boolean,
    codigo character varying(255) COLLATE pg_catalog."default",
    codigo_externo character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    hedge boolean,
    moeda integer,
    tabela_preco integer,
    tipo integer,
    unidade_medida integer,
    CONSTRAINT componente_preco_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.componente_preco
    OWNER to postgres;

-- Table: public.componente_preco_componentes

-- DROP TABLE public.componente_preco_componentes;

CREATE TABLE IF NOT EXISTS public.componente_preco_componentes
(
    componente_preco_id uuid NOT NULL,
    componentes_id uuid NOT NULL,
    CONSTRAINT fkgdvgsm0bsn9i5f0uxbwxor2vs FOREIGN KEY (componentes_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkm7nyu307uypb00f8gjah4liu5 FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.componente_preco_componentes
    OWNER to postgres;

-- Table: public.componente_preco_tipos_frete

-- DROP TABLE public.componente_preco_tipos_frete;

CREATE TABLE IF NOT EXISTS public.componente_preco_tipos_frete
(
    componente_preco_id uuid NOT NULL,
    tipos_frete_id uuid NOT NULL,
    CONSTRAINT componente_preco_tipos_frete_pkey PRIMARY KEY (componente_preco_id, tipos_frete_id),
    CONSTRAINT fk8l47esyejyviv9p4psedjwrqu FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkrrdbynmrk8yo0u90clylu38oa FOREIGN KEY (tipos_frete_id)
        REFERENCES public.tipos_frete (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.componente_preco_tipos_frete
    OWNER to postgres;

-- Table: public.finalidade

-- DROP TABLE public.finalidade;

CREATE TABLE IF NOT EXISTS public.finalidade
(
    id uuid NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT finalidade_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.finalidade
    OWNER to postgres;

-- Table: public.item

-- DROP TABLE public.item;

CREATE TABLE IF NOT EXISTS public.item
(
    id uuid NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT item_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.item
    OWNER to postgres;

-- Table: public.tipos_frete

-- DROP TABLE public.tipos_frete;

CREATE TABLE IF NOT EXISTS public.tipos_frete
(
    id uuid NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT tipos_frete_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tipos_frete
    OWNER to postgres;

INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe010-0e29-11eb-adc1-0242ac120002', 'INDUSTRIALIZACAO' );
INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe38a-0e29-11eb-adc1-0242ac120002', 'EXPORTACAO DIRETA' );
INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe484-0e29-11eb-adc1-0242ac120002', 'COMPRA MATERIA-PRIMA' );

INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a812216-0e2a-11eb-adc1-0242ac120002', 'MILHO' );
INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a81248c-0e2a-11eb-adc1-0242ac120002', 'SOJA' );
INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a8125ea-0e2a-11eb-adc1-0242ac120002', 'TRIGO' );

INSERT INTO TIPOS_FRETE (ID, DESCRICAO) VALUES('8a8127ca-0e2a-11eb-adc1-0242ac120002', 'CIF' );
INSERT INTO TIPOS_FRETE (ID, DESCRICAO) VALUES('8a812900-0e2a-11eb-adc1-0242ac120002', 'FOB' );