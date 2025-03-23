package com.vicky.actor;

import com.vicky.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorDao actorDao;

    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }
    public List<Actor> getActors() {
        return actorDao.getActors();
    }
    public Actor getActorById(Integer id) {
        return actorDao.selectActorById(id).orElseThrow(()->new NotFoundException(String.format("Actor with id %s not found", id)));
    }
    public void insertActor(Actor actor) {
        int result = actorDao.addActor(actor);
        if(result!=1){
            throw new NotFoundException(String.format("ops something went wrong"));
        }
    }
    public void updateActor(Integer id,String name,Long movie) {
        Optional<Actor> actors=actorDao.selectActorById(id);
        actors.ifPresentOrElse(actor -> {
            int result = actorDao.updateActor(id, name,movie);
            if(result!=1){
                throw new NotFoundException(String.format("ops something went wrong"));
            }
        },()->{
            throw new NotFoundException(String.format("Actor with id %s not found", id));
        });

    }
    public void deleteActor(Integer id) {
        Optional<Actor> actors=actorDao.selectActorById(id);
        actors.ifPresentOrElse(actor -> {
            int result = actorDao.deleteActor(id);
            if(result!=1){
                throw new NotFoundException(String.format("ops something went wrong"));
            }
        },()->{
            throw new NotFoundException(String.format("Actor with id %s not found", id));
        });
    }
}
