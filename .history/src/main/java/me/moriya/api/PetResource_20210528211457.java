package me.moriya.api;

import javax.ws.rs.Path;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import me.moriya.domain.model.Pet;

@Path("/pet")
public class PetResource {
    
    public Multi<PanacheEntityBase> pets()
    {
        return Pet.streamAll();
    }
}
