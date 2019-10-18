package com.app.remicall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    private String username;
    private String password;
    private boolean active;

    //forms a table for storing roles
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    //the field will be stored in a another table for which we did not describe mapping
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    //we want to store enum in String
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
