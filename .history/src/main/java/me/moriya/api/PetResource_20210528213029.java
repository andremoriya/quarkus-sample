package me.moriya.api;

import java.net.URI;
import java.net.URISyntaxException;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
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
        return Response.created(new URI("/pet/"+pet.id)).build();
    }

}
