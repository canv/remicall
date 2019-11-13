package com.app.remicall.services;

import com.app.remicall.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService extends UserDetailsService {
    boolean addUser(User user);
    boolean activateUser(String code);
    void updateUserRoles(User user, String username, Map<String, String> form);
    void updateUserProfile(User user, String password, String newEmail);
    List<User> findAllUsers();
}
