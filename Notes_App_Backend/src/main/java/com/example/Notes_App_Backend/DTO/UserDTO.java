package com.example.Notes_App_Backend.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    String username;
    String password;
    String confirmPassword;



}
