
CREATE DATABASE users_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


CREATE SCHEMA IF NOT EXISTS "user"
    AUTHORIZATION postgres;


CREATE TABLE IF NOT EXISTS "user".users
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    mail text COLLATE pg_catalog."default" NOT NULL,
    nick text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    status text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT users_mail_key UNIQUE (mail)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "user".users
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS "user".roles
(
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "user".roles
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS "user".users_roles
(
    user_uuid uuid NOT NULL,
    user_role text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_roles_user_role_fkey FOREIGN KEY (user_role)
        REFERENCES "user".roles (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT users_roles_user_uuid_fkey FOREIGN KEY (user_uuid)
        REFERENCES "user".users (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "user".users_roles
    OWNER to postgres;