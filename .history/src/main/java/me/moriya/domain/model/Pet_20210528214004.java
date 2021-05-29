package me.moriya.domain.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

@Entity
public class Pet extends PanacheEntityBase {
    

    private String name;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}
