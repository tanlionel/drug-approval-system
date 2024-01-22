package com.example.drugapprovalsystem.model.DTO;

import com.example.drugapprovalsystem.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private String fullname;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dayOfBirth;
    private Integer gender;
    private String roleName;
    private String isActive;
}
