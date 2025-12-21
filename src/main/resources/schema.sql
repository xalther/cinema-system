DROP TABLE IF EXISTS "movies";

CREATE TABLE "movies" (
    "id" BIGSERIAL PRIMARY KEY,
    "title" text,
    "description" text,
    "genre" text,
    "director" text,
    "production_year" integer
)