package me.moriya.domain.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import me.moriya.domain.dto.PetDTO;

@Entity
public class Pet extends PanacheEntity {
    
    private String name;

    public Pet(String name)
    {
        this.name
    }

    public Pet save(PetDTO dto)
    {

        return null;
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
