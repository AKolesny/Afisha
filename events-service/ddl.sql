
CREATE DATABASE events_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


CREATE SCHEMA IF NOT EXISTS event
    AUTHORIZATION postgres;


CREATE TABLE IF NOT EXISTS event.events
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    dt_event timestamp(3) without time zone NOT NULL,
    dt_end_of_sale timestamp(3) without time zone NOT NULL,
    type character varying COLLATE pg_catalog."default" NOT NULL,
    status character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT events_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS event.events
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS event.concerts
(
    uuid uuid NOT NULL,
    category uuid NOT NULL,
    CONSTRAINT concerts_pkey PRIMARY KEY (uuid),
    CONSTRAINT concerts_uuid_fkey FOREIGN KEY (uuid)
        REFERENCES event.events (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS event.concerts
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS event.films
(
    uuid uuid NOT NULL,
    country uuid NOT NULL,
    release_year integer NOT NULL,
    release_date date NOT NULL,
    duration integer NOT NULL,
    CONSTRAINT films_pkey PRIMARY KEY (uuid),
    CONSTRAINT films_uuid_fkey FOREIGN KEY (uuid)
        REFERENCES event.events (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS event.films
    OWNER to postgres;