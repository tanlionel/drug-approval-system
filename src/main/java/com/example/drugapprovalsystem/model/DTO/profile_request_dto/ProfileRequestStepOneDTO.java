package com.example.drugapprovalsystem.model.DTO.profile_request_dto;

import com.example.drugapprovalsystem.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequestStepOneDTO {
    private String title;
    private String imageURL;
    private String status;
}
