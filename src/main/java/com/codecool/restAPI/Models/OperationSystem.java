package com.codecool.restAPI.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OperationSystem {

    @Id
    @Column(name = "operation_system_id")
    private String id;

    @Column(name = "operation_system_name")
    private String name;
}
