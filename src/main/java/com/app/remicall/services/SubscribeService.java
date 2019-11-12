package com.app.remicall.services;

import com.app.remicall.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface SubscribeService {
    void subscribe(User currentUser, User user);
    void unsubscribe(User currentUser, User user);
}
