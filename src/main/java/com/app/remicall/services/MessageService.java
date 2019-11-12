package com.app.remicall.services;

import com.app.remicall.domain.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface MessageService {
    void saveFile(Message message, MultipartFile file) throws IOException;
    void saveMessage(Message message);
    Iterable<Message> findAllMessages();
    Iterable<Message> findMessageByTag(String filter);
}
