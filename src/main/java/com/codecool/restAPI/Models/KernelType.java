package com.codecool.restAPI.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KernelType {

    @Id
    @Column(name = "kernel_type_id")
    private String id;

    @Column(name = "kernel_type_name")
    private String name;
}
