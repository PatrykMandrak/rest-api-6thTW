package com.codecool.restAPI.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class OperationSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JsonBackReference
    private Kernel kernel;

    @ManyToOne
    @JsonBackReference
    private DefaultDesktopEnvironment defaultDesktopEnvironment;


    public OperationSystem() {

    }

    public OperationSystem(String name) {
        this.setName(name);
    }

    public OperationSystem(String name, Kernel kernel, DefaultDesktopEnvironment defaultDesktopEnvironment) {
        this(name);
        this.setKernel(kernel);
        this.defaultDesktopEnvironment = defaultDesktopEnvironment;
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

    public Kernel getKernel() {
        return kernel;
    }

    public void setKernel(Kernel kernel) {
        this.kernel = kernel;
    }
}
