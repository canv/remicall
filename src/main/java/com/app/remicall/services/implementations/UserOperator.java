package com.app.remicall.services.implementations;

import com.app.remicall.domain.Role;
import com.app.remicall.domain.User;
import com.app.remicall.repositories.UserRepository;
import com.app.remicall.services.MailSenderService;
import com.app.remicall.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserOperator implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("UserNotFound");
        return user;
    }

    @Override
    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null) return false;

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        mailSenderService.sendActivationMessage(user);
        return true;
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) return false;
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public void updateUserRoles(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    @Override
    public void updateUserProfile(User user, String password, String newEmail) {
        String currentEmail = user.getEmail();

        boolean isEmailChanged = (newEmail != null
                && currentEmail != null
                && !currentEmail.equals(newEmail));

        if (isEmailChanged) {
            user.setEmail(newEmail);
            if (!StringUtils.isEmpty(newEmail))
                user.setActivationCode(UUID.randomUUID().toString());
        }

        if(!StringUtils.isEmpty(password))
            user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        if(isEmailChanged) mailSenderService.sendActivationMessage(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
