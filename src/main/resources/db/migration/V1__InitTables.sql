CREATE  TABLE  movie(
    id BIGSERIAL PRIMARY KEY ,
    name TEXT NOT NULL ,
    releaseDate DATE NOT NULL,
    unique (name)
);