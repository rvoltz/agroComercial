


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

TABLESPACE pg_default;

ALTER TABLE public.componente_preco
    OWNER to postgres;

-- Table: public.item_componente_precodto

-- DROP TABLE public.item_componente_precodto;

CREATE TABLE IF NOT EXISTS public.item_componente_precodto
(
    id uuid NOT NULL,
    codigo character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT item_componente_precodto_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.item_componente_precodto
    OWNER to postgres;


-- Table: public.componente_preco_componentes

-- DROP TABLE public.componente_preco_componentes;

CREATE TABLE IF NOT EXISTS public.componente_preco_componentes
(
    componente_preco_id uuid NOT NULL,
    componentes_id uuid NOT NULL,
    CONSTRAINT uk_ijujbewf9ygcjh5mc3x10wspt UNIQUE (componentes_id),
    CONSTRAINT fki13dj5jomaqxmyfeq0x1b7479 FOREIGN KEY (componentes_id)
        REFERENCES public.item_componente_precodto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkm7nyu307uypb00f8gjah4liu5 FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.componente_preco_componentes
    OWNER to postgres;




-- Table: public.componente_preco_tipo_fretes

-- DROP TABLE public.componente_preco_tipo_fretes;

CREATE TABLE IF NOT EXISTS public.componente_preco_tipo_fretes
(
    componente_preco_id uuid NOT NULL,
    tipo_frete_id integer,
    CONSTRAINT fkehtdl28j1owijqyaywur803ic FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.componente_preco_tipo_fretes
    OWNER to postgres;

DROP TABLE public.componente_preco_tipo_fretes;


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




-- Table: public.componente_preco_itens

-- DROP TABLE public.componente_preco_itens;

CREATE TABLE IF NOT EXISTS public.componente_preco_itens
(
    componente_preco_id uuid NOT NULL,
    item_id integer,
    CONSTRAINT fkikpcabaoegow282b6tox8oxum FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.componente_preco_itens
    OWNER to postgres;


-- Table: public.componente_preco_finalidades

-- DROP TABLE public.componente_preco_finalidades;

CREATE TABLE IF NOT EXISTS public.componente_preco_finalidades
(
    componente_preco_id uuid NOT NULL,
    finalidades_id integer,
    CONSTRAINT fkpmc0ie6y95kuvofjg9y30if0d FOREIGN KEY (componente_preco_id)
        REFERENCES public.componente_preco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;



DROP TABLE public.componente_preco_finalidades;

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

ALTER TABLE public.componente_preco_finalidades
    OWNER to postgres;


 DROP TABLE public.componente_preco_itens;

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


ALTER TABLE public.componente_preco_itens
    OWNER to postgres;

INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe010-0e29-11eb-adc1-0242ac120002', 'INDUSTRIALIZACAO' );
INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe38a-0e29-11eb-adc1-0242ac120002', 'EXPORTACAO DIRETA' );
INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe484-0e29-11eb-adc1-0242ac120002', 'COMPRA MATERIA-PRIMA' );

INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a812216-0e2a-11eb-adc1-0242ac120002', 'MILHO' );
INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a81248c-0e2a-11eb-adc1-0242ac120002', 'SOJA' );
INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a8125ea-0e2a-11eb-adc1-0242ac120002', 'TRIGO' );

INSERT INTO TIPOS_FRETE (ID, DESCRICAO) VALUES('8a8127ca-0e2a-11eb-adc1-0242ac120002', 'CIF' );
INSERT INTO TIPOS_FRETE (ID, DESCRICAO) VALUES('8a812900-0e2a-11eb-adc1-0242ac120002', 'FOB' );