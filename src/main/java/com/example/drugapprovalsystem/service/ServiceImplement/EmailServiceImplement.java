package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.service.ServiceInterface.EmailService;
import com.example.drugapprovalsystem.service.ServiceInterface.ThymeleafService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImplement implements EmailService {
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    ThymeleafService thymeleafService;
    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public ResponseEntity<?> sendMail(String to) {
        try {
            Map<String,Object> variables = new HashMap<>();
            variables.put("content","System notification to admin, please log in to the pharmacy system to view prescription management.");
            variables.put("image","https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/330px-Image_created_with_a_mobile_phone.png")   ;
            variables.put("tesText","hehe");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);

            String subject = "test";
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(thymeleafService.createContent("email-template.html",variables),true);

            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Successfully");
    }
}
