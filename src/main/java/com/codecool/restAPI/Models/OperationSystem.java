package com.codecool.restAPI.Models;

import javax.persistence.*;

@Entity
public class OperationSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_system_name")
    private String name;

    public OperationSystem() {

    }

    public OperationSystem(String name) {
        this.name = name;
    }
}
