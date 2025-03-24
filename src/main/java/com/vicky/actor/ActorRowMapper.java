package com.vicky.actor;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActorRowMapper implements RowMapper<Actor>{
    @Override
    public Actor mapRow(ResultSet resultSet,int i) throws SQLException {
        return new Actor(
                resultSet.getInt("actor_id"),
                resultSet.getString("actor_name"),
                resultSet.getLong("actor_movie_id")
        );
    }
}
