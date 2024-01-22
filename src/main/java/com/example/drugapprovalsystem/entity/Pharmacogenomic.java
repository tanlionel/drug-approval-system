package com.example.drugapprovalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pharmacogenomic")
public class Pharmacogenomic {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "indication")
    private String indication;

    @Column(name = "pharmacodynamic")
    private String pharmacodynamic;

    @Column(name = "mechanism_of_action")
    private String mechanismOfAction;

    @Column(name = "asorption")
    private String asorption;

    @Column(name = "toxicity")
    private String toxicity;

}