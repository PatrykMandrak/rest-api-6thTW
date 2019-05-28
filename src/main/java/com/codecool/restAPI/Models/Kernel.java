package com.codecool.restAPI.Models;

import javax.persistence.*;

@Entity
@Table(name = "Kernel_Table")
public class Kernel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kernel_name")
    private String name;

    @Column(name = "kernel_description")
    private String description;

/*    @ManyToOne
    private KernelType kernelType;*/

    public Kernel() {

    }

    public Kernel(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "Kernel: " + this.id + ", " + this.name + ", " + this.description;
    }
}
