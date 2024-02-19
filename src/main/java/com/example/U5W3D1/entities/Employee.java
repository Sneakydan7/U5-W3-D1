package com.example.U5W3D1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String image;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    private Set<Device> devices = new LinkedHashSet<>();

    public Employee(String username, String name, String surname, String email, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
