package com.vicky.movie;

import com.vicky.actor.ActorRowMapper;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MovieRowMapper implements RowMapper<Movie> {
    private final ActorRowMapper actorRowMapper;
    public MovieRowMapper(ActorRowMapper actorRowMapper) {
            this.actorRowMapper = actorRowMapper;
    }

    @Override
    public Movie mapRow(ResultSet resultSet,int i) throws SQLException {
            return new Movie(
                    resultSet.getInt("movie_id"),
                    resultSet.getString("movie_name"),
                    List.of(),
                LocalDate.parse(resultSet.getString("releaseDate"))
            );
    }
}
