package me.moriya.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
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
    public void save(PetDTO pet) 
    {
        Pet pet = new Pet(pet);
        return pet.s
    }

}
