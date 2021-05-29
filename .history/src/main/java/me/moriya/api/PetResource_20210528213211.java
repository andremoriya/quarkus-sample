package me.moriya.api;

import java.net.URISyntaxException;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import me.moriya.domain.model.Pet;

@Path("/pet")
public class PetResource {
    
    @GET
    public Multi<PanacheEntityBase> pets()
    {
        return Pet.streamAll();
    }

    @POST
    @Transactional
    public Response save(Pet pet) throws URISyntaxException 
    {
        pet.id = null;
        pet.persistAndFlush();
        return Panache.withTransaction(pet::persist)
        .replaceWith(Response.ok(pet).status(Status.CREATED)::build);
    }

}
