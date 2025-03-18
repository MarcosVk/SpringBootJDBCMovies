package com.vicky.movie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        String sql= """
                Select id,name,releaseDate FROM movie
                LIMIT 100;
                """;
        return jdbcTemplate.query(sql,new MovieRowMapper());
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
                Select * from movie WHERE id=?;
                """;
        return jdbcTemplate.query(sql,new MovieRowMapper(),id).stream().findFirst();
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
