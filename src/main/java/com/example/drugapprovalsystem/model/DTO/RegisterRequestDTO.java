package com.example.drugapprovalsystem.model.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegisterRequestDTO {
    private String email;
    private String username;
    private String fullName;
    private LocalDate dob;
    private Integer gender;
    private Integer roleID;
}
