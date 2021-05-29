package me.moriya.domain.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

@Entity
@AttributeOverride(column = @Colu, name = )
public class Pet extends PanacheEntity {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

}
