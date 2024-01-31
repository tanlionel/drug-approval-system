package com.example.drugapprovalsystem.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityDTO {
    private String certificateName;
    private Integer countryId;
    private String countryName;
}
