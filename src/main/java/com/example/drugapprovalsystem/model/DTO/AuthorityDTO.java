package com.example.drugapprovalsystem.model.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityDTO {
    private String certificateName;
    private Integer countryId;
    private String countryName;
}
