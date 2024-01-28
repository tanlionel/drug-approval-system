package com.example.drugapprovalsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_product_id")
    @JsonIgnore
    private ApprovalProduct approvalProduct;

    @Column(name = "strength")
    private String strength;

    @Column(name = "strength_number")
    private String strengthNumber;

    @Column(name = "strength_unit")
    private String strengthUnit;

    @Column(name = "clinically_relevant")
    private String clinicallyRelevant;
}