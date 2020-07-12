package com.sda.secure.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;
    private String role;

    public Role() {
    }
}
