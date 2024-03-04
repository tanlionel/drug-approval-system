package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.model.DTO.UserResponseDTO;

public class UserMapper {
    public static final UserResponseDTO mapToUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .dayOfBirth(user.getDayOfBirth())
                .gender(user.getGender())
                .roleName(user.getRole().getName())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .isActive(user.getIsActive())
                .avatar(user.getAvatar())
                .build();
    }
}
