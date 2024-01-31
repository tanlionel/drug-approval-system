package com.example.drugapprovalsystem.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String countryName;
}
