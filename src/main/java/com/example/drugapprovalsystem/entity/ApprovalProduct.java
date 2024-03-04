package com.example.drugapprovalsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "approval_product")
public class ApprovalProduct {
    public ApprovalProduct(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
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

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "manufactor_id")
    private Manufactor manufactor;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacogenomic_id")
    private Pharmacogenomic pharmacogenomic;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_allergy_details_id")
    private ProductAllergyDetail productAllergyDetails;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contraindication_id")
    private Contraindication contraindication;

    @ManyToOne
    @JoinColumn(name = "product_administration", referencedColumnName = "id")
    private ProductAdministration productAdministration;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image", length = 300)
    private String image;
}