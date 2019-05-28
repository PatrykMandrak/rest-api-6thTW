package com.codecool.restAPI.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DefaultDesktopEnvironment {

    @Id
    @Column(name = "default_dekstop_environment_id")
    private String id;

    @Column(name = "default_dekstop_environment_name")
    private String name;

    @OneToMany(mappedBy = "defaultDesktopEnvironment")
    private Set<OperationSystem> operationSystems = new HashSet<>();
}
