package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.exception.InvalidateException;
import com.example.drugapprovalsystem.exception.UserAlreadyActiveException;
import com.example.drugapprovalsystem.exception.UserAlreadyDeactivateException;
import com.example.drugapprovalsystem.exception.UserDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.ChangePasswordRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UpdateUserRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UserResponseDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("/users/email")
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
                        .isActive(user.getIsActive())
                        .avatar(user.getAvatar())
                        .build());
    }
    @GetMapping("/users")
    public ResponseEntity<?> getUsersPageable(@RequestParam(required = false,defaultValue = "0") Integer pageNo,
                                              @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false,defaultValue = "id") String sortField,
                                              @RequestParam(required = false,defaultValue = "asc") String sortOrder,
                                              @RequestParam(required = false,defaultValue = "") String search,
                                              @RequestParam(required = false) String roleName,
                                              @RequestParam(required = false) String status,
                                              @RequestParam(required = false) Integer gender){
        return ResponseEntity.ok(userService.getUserPageable(pageNo,pageSize,sortField,sortOrder,roleName,status,gender,search));
    }

    @PostMapping("/users/activate")
    public ResponseEntity<?> activeUser(@RequestParam String email) throws UserDoesNotExistException, UserAlreadyActiveException {
        User user = userService.activateUser(email);
        return ResponseEntity.ok(UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .dayOfBirth(user.getDayOfBirth())
                .gender(user.getGender())
                .roleName(user.getRole().getName())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .isActive(user.getIsActive())
                .avatar(user.getAvatar())
                .build());
    }
    @PostMapping("/users/deactivate")
    public ResponseEntity<?> deactivateUser(@RequestParam String email) throws UserDoesNotExistException, UserAlreadyDeactivateException {
        User user = userService.deactivateUser(email);
        return ResponseEntity.ok(UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .dayOfBirth(user.getDayOfBirth())
                .gender(user.getGender())
                .roleName(user.getRole().getName())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .isActive(user.getIsActive())
                .avatar(user.getAvatar())
                .build());
    }
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO, @RequestParam String email) throws UserDoesNotExistException {
        User user= userService.updateUser(updateUserRequestDTO,email);
        return ResponseEntity.ok(UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .dayOfBirth(user.getDayOfBirth())
                .gender(user.getGender())
                .roleName(user.getRole().getName())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .isActive(user.getIsActive())
                .avatar(user.getAvatar())
                .build());
    }



}
