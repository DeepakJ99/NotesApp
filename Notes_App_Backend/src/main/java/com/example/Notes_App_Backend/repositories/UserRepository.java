package com.example.Notes_App_Backend.repositories;

import com.example.Notes_App_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
