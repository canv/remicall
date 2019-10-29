package com.app.remicall.repositories;

import com.app.remicall.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username); //JPA will do it by keywords
    User findByActivationCode(String code); //JPA will do it by keywords
}
