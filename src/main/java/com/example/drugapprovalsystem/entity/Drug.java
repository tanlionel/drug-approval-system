package com.example.drugapprovalsystem.entity;

import io.swagger.models.auth.In;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drug")
public class Drug {
    public Drug(Integer id) {
        this.id = id;
    }
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "drugbank_id")
    private Integer drugbankId;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @Column(name = "description")
    private String description;

    @Column(name = "simple_description")
    private String simpleDescription;

    @Column(name = "clinical_description")
    private String clinicalDescription;

    @Column(name = "approval_status")
    private Integer approvalStatus;

}