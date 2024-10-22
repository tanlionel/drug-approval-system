package com.example.drugapprovalsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "pharmacogenomic")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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