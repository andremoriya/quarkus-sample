package me.moriya.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;
import me.moriya.domain.model.dto.PetDTO;

@Entity
public class Pet extends PanacheEntityBase {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Pet() {
    }

    private Pet(PetDTO dto) {
        this.name = dto.getName();
    }

    public static Pet from(PetDTO dto) 
    {
        return new Pet(dto);
    }

    public static Uni<Pet> findBy(Long id) {
        return Pet.<Pet>findById(id)
                        .onItem()
                        .ifNotNull()
                        .transform(pet -> pet);
    }
    
    // GETTER AND SETTER
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
}
