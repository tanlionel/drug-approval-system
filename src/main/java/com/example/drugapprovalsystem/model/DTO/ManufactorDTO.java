package com.example.drugapprovalsystem.model.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManufactorDTO {
    private String name;
    private String company;
    private String score;
    private String source;
    private Integer countryId;
}
