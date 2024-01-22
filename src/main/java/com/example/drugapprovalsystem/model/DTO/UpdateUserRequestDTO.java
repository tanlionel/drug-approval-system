package com.example.drugapprovalsystem.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserRequestDTO {
    @Nullable
    private String fullName;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @Nullable
    private LocalDate dayOfBirth;
    @Nullable
    private Integer gender;
}
