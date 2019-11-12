package com.app.remicall.services.implementations;

import com.app.remicall.domain.User;
import com.app.remicall.repositories.UserRepository;
import com.app.remicall.services.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeOperator implements SubscribeService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepository.save(user);
    }

    @Override
    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        userRepository.save(user);
    }
}
