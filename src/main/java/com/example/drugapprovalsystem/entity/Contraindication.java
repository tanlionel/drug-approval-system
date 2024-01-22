package com.example.drugapprovalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contraindication")
public class Contraindication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "value")
    private String value;

    @Column(name = "is_active")
    private Boolean isActive;

}