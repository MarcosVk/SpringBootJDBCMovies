package com.vicky.actor;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDataAccessService implements ActorDao{

    private final JdbcTemplate jdbcTemplate;

    public ActorDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Actor> getActors() {
        String sql= """
                SELECT id,name,movie from actors;
                """;
        return jdbcTemplate.query(sql, new ActorRowMapper());
    }

    @Override
    public Optional<Actor> selectActorById(Integer id) {
        String sql= """
                SELECT id,name,movie from actors
                WHERE id=?;
                """;
        return jdbcTemplate.query(sql,new ActorRowMapper(),id).stream().findFirst();
    }
    @Override
    public int addActor(Actor actor) {
        String sql= """
                INSERT INTO actors(name,movie) VALUES(?,?);
                """;
        return jdbcTemplate.update(sql,actor.name(),actor.movie());
    }
    @Override
    public int updateActor(Integer id, String name,Long movie) {
        String sql= """
                UPDATE actors SET name=? ,movie=?
                Where id=?;
                """;
        return jdbcTemplate.update(sql,name,movie,id);
    }
    @Override
    public int deleteActor(Integer id) {
        String sql= """
                DELETE FROM actors 
                WHERE id=?;
                """;
        return jdbcTemplate.update(sql,id);
    }
}
