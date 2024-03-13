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
            variables.put("image","https://scontent.fsgn2-7.fna.fbcdn.net/v/t1.15752-9/423766235_753744409817858_5189640851690714550_n.png?_nc_cat=100&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeGtS-ftZZC4qzXjA1wPmafYfS1NQKsSmKV9LU1AqxKYpUsUx5CgeGANTBlUTVQb9aA1u1_h3xtfEeqUpg44dpk8&_nc_ohc=51-sv17wB0cAX_r9sce&_nc_ht=scontent.fsgn2-7.fna&oh=03_AdQIoEjg_HcN3XSDe02WNvw8Rnhs14Sss4u4CktpgAwE-w&oe=66179DE4");
            variables.put("tesText","Please login to view new Profile product.");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);

            String subject = "NOTIFICATION FROM DRUG APPROVAL SYSTEM!";
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(thymeleafService.createContent("email-template.html",variables),true);

            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Successfully");
    }

    @Override
    public ResponseEntity<?> sendMail(String to, String content, String tesText) {
        try {
            Map<String,Object> variables = new HashMap<>();
            variables.put("content", content);
            variables.put("image","https://scontent.fsgn2-7.fna.fbcdn.net/v/t1.15752-9/423766235_753744409817858_5189640851690714550_n.png?_nc_cat=100&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeGtS-ftZZC4qzXjA1wPmafYfS1NQKsSmKV9LU1AqxKYpUsUx5CgeGANTBlUTVQb9aA1u1_h3xtfEeqUpg44dpk8&_nc_ohc=51-sv17wB0cAX_r9sce&_nc_ht=scontent.fsgn2-7.fna&oh=03_AdQIoEjg_HcN3XSDe02WNvw8Rnhs14Sss4u4CktpgAwE-w&oe=66179DE4");
            variables.put("tesText", tesText);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);

            String subject = "NOTIFICATION FROM DRUG APPROVAL SYSTEM!";
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(thymeleafService.createContent("email-template.html",variables),true);

            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Successfully");
    }
}
