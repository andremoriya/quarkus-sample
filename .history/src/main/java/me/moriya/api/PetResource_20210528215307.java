package me.moriya.api;

import java.net.URISyntaxException;
import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.reactive.RestPath;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import me.moriya.domain.model.Pet;

@Path("/pet")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PetResource {
    
    @GET
    public Multi<PanacheEntityBase> pets()
    {
        return Pet.streamAll();
    }

    @POST
    public Uni<Response> save(Pet pet)
    {
        return Panache.withTransaction(pet::persist)
                        .replaceWith(Response.ok(pet).status(Status.CREATED)::build);
    }
    
    public void update(@RestPath Long id, Pet pet)
    {
        Consumer<Fruit> = ();
        Panache.withTransaction(
            () -> Pet.findById(id).onItem().ifNotNull().invoke(entity -> entity = pet.name)
        ).onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
        .onItem().ifNull().continueWith(Response.ok().status(Status.NOT_FOUND)::build);

    }
}
