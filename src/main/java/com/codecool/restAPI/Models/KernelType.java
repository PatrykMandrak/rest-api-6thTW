package com.codecool.restAPI.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class KernelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kernel_type_name")
    private String name;

    @Column(name = "kernel_type_description")
    private String description;

/*    @OneToMany(mappedBy = "kernelType")
    private Set<Kernel> kernels = new HashSet<>();*/

/*    public void addKernel(Kernel kernel) {
        kernels.add(kernel);
    }*/

    public KernelType() {

    }

    public KernelType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
