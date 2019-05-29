package com.codecool.restAPI.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kernel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private KernelType kernelType;

    @OneToMany(mappedBy = "kernel")
    private Set<OperationSystem> operationSystems = new HashSet<>();


    public Kernel() {

    }

    public Kernel(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public Kernel(String name, String description, Set<OperationSystem> operationSystems) {
        this(name, description);
        this.setOperationSystems(operationSystems);
    }

    public Kernel(String name, String description, KernelType kernelType) {
        this(name, description);
        this.kernelType = kernelType;
    }


    @Override
    public String toString() {
        return "Kernel: " + this.getId() + ", " + this.getName() + ", " + this.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KernelType getKernelType() {
        return kernelType;
    }

    public void setKernelType(KernelType kernelType) {
        this.kernelType = kernelType;
    }

    public Set<OperationSystem> getOperationSystems() {
        return operationSystems;
    }

    public void setOperationSystems(Set<OperationSystem> operationSystems) {
        this.operationSystems = operationSystems;
    }

    public void addOperationSystem(OperationSystem operationSystem) {
        operationSystems.add(operationSystem);
    }
}
