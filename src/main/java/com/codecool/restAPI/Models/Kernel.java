package com.codecool.restAPI.Models;

import javax.persistence.*;

@Entity
@Table(name = "Kernel_Table")
public class Kernel {

    @Id
    @Column(name = "kernel_id")
    private String id;

    @Column(name = "kernel_name")
    private String name;

    @Column(name = "kernel_description")
    private String description;

    public Kernel() {

    }

    public Kernel(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Kernel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
