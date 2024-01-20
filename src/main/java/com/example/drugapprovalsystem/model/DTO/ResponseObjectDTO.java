package com.example.drugapprovalsystem.model.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
public class ResponseObjectDTO {
    private String accessToken;
    private String refreshToken;
}
