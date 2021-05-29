package me.moriya.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import me.moriya.domain.dto.PetDTO;
import me.moriya.domain.model.Pet;

@Path("/pet")
public class PetResource {
    
    @GET
    public Multi<PanacheEntityBase> pets()
    {
        return Pet.streamAll();
    }

    @POST
    public Uni<Void> save(PetDTO d) 
    {
        Pet pet = new Pet(pet);
        return pet.save();
    }

}