package com.example.drugapprovalsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne()
    @JoinColumn(name = "created_by", updatable = false)
    private User createdBy;

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @ManyToOne()
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "image", length = 300)
    private String image;

    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private Boolean isActive;
}