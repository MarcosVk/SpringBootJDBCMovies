package com.vicky.actor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/actors")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> getActors() {
        return actorService.getActors();
    }
    @GetMapping(path = "{id}")
    public Actor getActorById(@PathVariable Integer id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public void addActor(@RequestBody Actor actor) {
        actorService.insertActor(actor);
    }

    @PutMapping(path = "{id}")
    public void updateActor(@PathVariable Integer id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) Long movie) {
        actorService.updateActor(id, name,movie);
    }

    @DeleteMapping(path = "{id}")
    public void deleteActor(@PathVariable Integer id) {
        actorService.deleteActor(id);
    }
}
