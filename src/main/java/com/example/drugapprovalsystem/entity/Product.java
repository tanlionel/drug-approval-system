package com.example.drugapprovalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "labeller_id")
    private Labeller labeller;

    @Column(name = "name")
    private String name;

    @Column(name = "route")
    private String route;

    @Column(name = "prescription_name")
    private String prescriptionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufactor_id")
    private Manufactor manufactor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacogenomic_id")
    private Pharmacogenomic pharmacogenomic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contraindication_id")
    private Contraindication contraindication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_allergy_details_id")
    private ProductAllergyDetail productAllergyDetails;

}