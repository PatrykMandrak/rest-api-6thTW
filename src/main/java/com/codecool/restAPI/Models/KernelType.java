package com.codecool.restAPI.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class KernelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "kernelType")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Set<Kernel> kernels = new HashSet<>();

    public KernelType() {

    }

    public KernelType(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public KernelType(String name, String description, Set<Kernel> kernels) {
        this(name, description);
        this.setKernels(kernels);
    }

    public void addKernel(Kernel kernel) {
        kernels.add(kernel);
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

    public Set<Kernel> getKernels() {
        return kernels;
    }

    public void setKernels(Set<Kernel> kernels) {
        this.kernels = kernels;
    }
}
