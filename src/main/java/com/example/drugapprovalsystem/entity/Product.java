package com.example.drugapprovalsystem.entity;

import com.example.drugapprovalsystem.model.Mapper.ProductMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    public Product(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
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

    //USA
    @Column(name = "is_approved_by_fda")
    private boolean isApprovedByFDA;
    //FRANCE
    @Column(name = "is_approved_by_ansm")
    private boolean isApprovedByANSM;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image", length = 300)
    private String image;
}