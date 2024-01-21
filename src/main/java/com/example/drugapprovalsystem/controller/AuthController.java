package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.model.DTO.LoginRequestDTO;
import com.example.drugapprovalsystem.model.DTO.RegisterRequestDTO;
import com.example.drugapprovalsystem.model.DTO.ResponseObjectDTO;
import com.example.drugapprovalsystem.model.Message;
import com.example.drugapprovalsystem.service.ServiceInterface.JwtService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDTO loginUser) throws Exception {

        User user = userService.login(loginUser.getEmail(), loginUser.getPassword());


        String refreshToken = jwtService.generateRefreshToken(user);
        String accessToken = jwtService.generateAccessToken(refreshToken);

        return ResponseEntity.ok(
                ResponseObjectDTO.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registeredUser) throws Exception{

        userService.registerUser(registeredUser);
        return ResponseEntity.status(HttpStatus.OK).body(Message.msgSuccess);
    }

}
