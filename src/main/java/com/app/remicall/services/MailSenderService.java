package com.app.remicall.services;

import com.app.remicall.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface MailSenderService {
    boolean sendActivationMessage(User user);
}
