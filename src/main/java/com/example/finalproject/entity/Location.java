package com.example.finalproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Location
{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 1)
    private String location;

    @ManyToMany(mappedBy = "locations")
    private Set<Trainer> training;

    @ManyToMany(mappedBy = "locations")
    private Set<Client> clients;

    public Location() {
    }

    public Location(String location) {
        this.location = location;
        this.training=new HashSet<Trainer>();
        this.clients=new HashSet<Client>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Trainer> getTraining() {
        return training;
    }

    public void setTraining(Set<Trainer> training) {
        this.training = training;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
