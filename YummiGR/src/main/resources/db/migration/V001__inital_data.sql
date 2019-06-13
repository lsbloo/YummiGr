CREATE TABLE public.yummi_user
(
    id bigint NOT NULL,
    actived boolean,
    email character varying(300) COLLATE pg_catalog."default",
    first_name character varying(100) COLLATE pg_catalog."default",
    identifier character varying(255) COLLATE pg_catalog."default",
    last_name character varying(100) COLLATE pg_catalog."default",
    password character varying(200) COLLATE pg_catalog."default",
    username character varying(150) COLLATE pg_catalog."default",
    date date,
    CONSTRAINT yummi_user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.yummi_user
    OWNER to postgres;
	
	
CREATE TABLE public.role
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to postgres;
	
CREATE TABLE public.usuarios_role
(
    usuarios_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT fkb4lgjns7jnrvtimlocbhgu9eu FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fktp8ha2mpniqp2dqs2gqg3dx37 FOREIGN KEY (usuarios_id)
        REFERENCES public.yummi_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuarios_role
    OWNER to postgres;

CREATE TABLE public.privilege
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT privilege_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.privilege
    OWNER to postgres;


