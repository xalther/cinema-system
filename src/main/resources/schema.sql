DROP TABLE IF EXISTS "movies";

CREATE TABLE "movies" (
    "id" bigint DEFAULT nextval('movies_id_seq') NOT NULL,
    "title" text,
    "description" text,
    "genre" text,
    "director" text,
    "production_year" integer,
    CONSTRAINT "movies_pkey" PRIMARY KEY ("id")
)