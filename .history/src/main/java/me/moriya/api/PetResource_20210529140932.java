package me.moriya.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.reactive.RestPath;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import me.moriya.domain.model.Pet;
import me.moriya.domain.model.dto.PetDTO;

@Path("/pet")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PetResource {
    
    @GET
    public Multi<Pet> pets() {
        return Pet.<Pet>streamAll();
    }

    @POST
    public Uni<Response> save(PetDTO dto) {
        Pet pet = Pet.from(dto);
        return Panache.withTransaction(pet::persist)
                        .replaceWith(Response.ok(pet).status(Status.CREATED)::build);
    }
    
    @PUT
    @Path("/{id}")
    public Uni<Response> update(@RestPath Long id, PetDTO dto) {
        return Panache.withTransaction(
            () -> Pet.<Pet>findById(id)
                        .onItem()
                        .ifNotNull()
                        .invoke(pet -> pet.setName(dto.getName()))
        ).onItem()
            .ifNotNull()
            .transform(pet -> Response.ok(pet).build())
        .onItem().ifNull().continueWith(Response.ok().status(Status.NOT_FOUND)::build);

    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@RestPath Long id) {
        return Panache.withTransaction(() -> Pet.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(Status.NO_CONTENT).build()
                        : Response.ok().status(Status.NOT_FOUND).build());
    }


}
