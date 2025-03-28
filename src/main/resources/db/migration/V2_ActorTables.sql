CREATE TABLE actors(
    id BIGSERIAL primary key,
    name TEXT NOT NULL,
    movie bigint REFERENCES movie(id),
    unique (name,movie)
);