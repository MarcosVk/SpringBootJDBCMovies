package com.vicky.actor;

import java.util.List;
import java.util.Optional;

public interface ActorDao {
    List<Actor> getActors();
    int addActor(Actor actor);
    int updateActor(Integer id,String name,Long movie );
    int deleteActor(Integer id);
    Optional<Actor> selectActorById(Integer id);

}
