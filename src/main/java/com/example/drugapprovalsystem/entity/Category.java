package com.example.drugapprovalsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    public Category(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "drugbank_id")
    private Integer drugbankId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "is_active")
    private Boolean isActive;
}