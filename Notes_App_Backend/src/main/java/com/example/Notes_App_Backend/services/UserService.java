package com.example.Notes_App_Backend.services;

import com.example.Notes_App_Backend.DTO.UserDTO;
import com.example.Notes_App_Backend.DTO.UserLoginDTO;
import com.example.Notes_App_Backend.configuration.AuthenticationResponse;
import com.example.Notes_App_Backend.configuration.JWTHelper;
import com.example.Notes_App_Backend.entities.User;
import com.example.Notes_App_Backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private  final  UserRepository userRepository;
    private final JWTHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    boolean saveUser(User u){
        User exists = userRepository.findByUsername(u.getUsername());
        if(exists == null){
            userRepository.save(u);
            return true;
        }
        return  false;
    }
    public AuthenticationResponse register(UserDTO request){
        User c =  User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        //returns true if a new customer is created
        if(saveUser(c)){
            return AuthenticationResponse.builder().token(jwtHelper.generateToken(c)).build();
        }
        return null;
    }

    public AuthenticationResponse login(UserLoginDTO request) {
        System.out.println(request);
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));

            User u = userRepository.findByUsername(request.getUsername());
            return AuthenticationResponse.builder().token(jwtHelper.generateToken(u)).build();
        }
        catch(AuthorizationServiceException e){
            return  null;
        }

    }

    public UserDetails findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
