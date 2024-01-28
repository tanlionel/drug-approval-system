package com.example.drugapprovalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "approval_product")
public class ApprovalProduct {
    public ApprovalProduct(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "labeller_id")
    private Labeller labeller;

    @Column(name = "name")
    private String name;

    @Column(name = "route")
    private String route;

    @Column(name = "prescription_name")
    private String prescriptionName;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

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

    @Column(name = "is_active")
    private Boolean isActive;
}