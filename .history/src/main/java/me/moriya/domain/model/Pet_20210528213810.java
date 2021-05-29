package me.moriya.domain.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import me.moriya.domain.dto.PetDTO;

@Entity
public class Pet extends PanacheEntity {
    
    private String name;

    public Uni<Void> save()
    {
        return persist(this);
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}
