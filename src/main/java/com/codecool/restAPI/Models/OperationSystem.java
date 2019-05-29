package com.codecool.restAPI.Models;

import javax.persistence.*;

@Entity
public class OperationSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Kernel kernel;

    @ManyToOne
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
