package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.service.ServiceInterface.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplement implements EmailService {
    @Value("${spring.mail.username}")
    private String fromEmail;


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public ResponseEntity<?> sendMail(String to) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);

            String subject = "test";
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText("test body");

            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Successfully");
    }
}
