package com.example.Notes_App_Backend.controllers;

import com.example.Notes_App_Backend.DTO.UserDTO;
import com.example.Notes_App_Backend.DTO.UserLoginDTO;
import com.example.Notes_App_Backend.configuration.AuthenticationResponse;
import com.example.Notes_App_Backend.entities.User;
import com.example.Notes_App_Backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/register",consumes = {MediaType.ALL_VALUE})

    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO userDTO){
        AuthenticationResponse response = userService.register(userDTO);
        if(response == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(AuthenticationResponse
                                                                .builder()
                                                                .token("Username already exists")
                                                                .build());
        }
        return ResponseEntity.of(Optional.of(response));
    }

    @PostMapping(path = "/login", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserLoginDTO userDTO){
        AuthenticationResponse response =
        return  ResponseEntity.of(Optional.of(userService.login(userDTO)));
    }
}
