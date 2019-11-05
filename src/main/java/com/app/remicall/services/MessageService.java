package com.app.remicall.services;

import com.app.remicall.domain.Message;
import com.app.remicall.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (uploadDir.exists()) uploadDir.mkdir();
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            message.setFilename(resultFileName);
        }
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public Iterable<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public Iterable<Message> findMessageByTag(String filter) {
        return messageRepository.findByTag(filter);
    }
}
