package com.codecool.restAPI.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DefaultDesktopEnvironment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "defaultDesktopEnvironment")
    @JsonManagedReference
    private Set<OperationSystem> operationSystems = new HashSet<>();

    public DefaultDesktopEnvironment() {

    }

    public DefaultDesktopEnvironment(String name) {
        this.name = name;
    }

    public DefaultDesktopEnvironment(String name, Set<OperationSystem> operationSystems) {
        this(name);
        this.operationSystems = operationSystems;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OperationSystem> getOperationSystems() {
        return operationSystems;
    }

    public void setOperationSystems(Set<OperationSystem> operationSystems) {
        this.operationSystems = operationSystems;
    }
}
