package com.vicky.movie;

import com.vicky.actor.ActorRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;
    private final ActorRowMapper actorRowMapper;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate, ActorRowMapper actorRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.actorRowMapper = actorRowMapper;
    }

    @Override
    public List<Movie> selectMovies() {
        String sql= """
                SELECT m.id AS movie_id,m.name AS movie_name,m.releaseDate,a.id AS actor_id,a.name AS actor_name,a.movie AS actor_movie_id
                FROM movie m
                LEFT JOIN actors a ON m.id=a.movie
                LIMIT 100
                """;
        return jdbcTemplate.query(sql,new MovieRowMapper(actorRowMapper));
    }

    @Override
    public int insertMovie(Movie movie) {
        String sql= """
                INSERT INTO movie(name,releaseDate) VALUES(?,?)
                """;
        return jdbcTemplate.update(sql,movie.name(),movie.releaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        String sql= """
                DELETE from movie WHERE id=?
                """;
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        String sql= """
                SELECT m.id,m.name,m.releaseDate,a.id,a.name
                FROM movie m
                LEFT JOIN actors a ON m.id=a.movie
                WHERE id=?
                LIMIT 100;
                """;
        return jdbcTemplate.query(sql,new MovieRowMapper(actorRowMapper),id).stream().findFirst();
    }
    public int updateMovie(int id,String name,String releaseDate) {
        String sql= """
                UPDATE movie 
                SET name=?,releaseDate=? 
                WHERE id=?
                """;
        return jdbcTemplate.update(sql,name, LocalDate.parse(releaseDate),id);
    }
}
