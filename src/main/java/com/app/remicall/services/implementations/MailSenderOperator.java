package com.app.remicall.services.implementations;

import com.app.remicall.domain.User;
import com.app.remicall.services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MailSenderOperator implements MailSenderService {

    @Autowired
    private JavaMailSender javaMailSendermailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${hostname}")
    private String hostname;

    @Override
    public boolean sendActivationMessage(User user) {
        if(!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hi, %s! \n" +
                            "Welcome to Remicall. \n" +
                            "Follow the link to complete registration: http://" +
                            "%s/activate/%s",
                    user.getUsername(),
                    hostname,
                    user.getActivationCode()
            );
            sendEmail(user.getEmail(),"Activation code", message);
        } else return false;
        return true;
    }

    private void sendEmail(String emailTo, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSendermailSender.send(mailMessage);
    }
}
