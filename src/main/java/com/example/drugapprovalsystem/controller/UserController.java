package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.exception.InvalidateException;
import com.example.drugapprovalsystem.exception.UserDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.UserResponseDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping("/find-by-email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) throws UserDoesNotExistException, InvalidateException {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserResponseDTO.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .dayOfBirth(user.getDayOfBirth())
                        .gender(user.getGender())
                        .roleName(user.getRole().getName())
                        .username(user.getUsername())
                        .fullname(user.getFullname())
                        .build());
    }
    @GetMapping("get-pageable-users")
    public ResponseEntity<?> getUsersPageable(@RequestParam(required = false,defaultValue = "0") Integer pageNo,
                                              @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false,defaultValue = "id") String sortField,
                                              @RequestParam(required = false,defaultValue = "asc") String sortOrder){
        return ResponseEntity.ok(userService.getUserPageable(pageNo,pageSize,sortField,sortOrder));
    }
}
