package com.assignment.moja_car_wash.services.impl;

import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.services.EmailService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log
public class EmailServiceImpl implements EmailService {
    public static final String SUBJECT = "Moja car-wash appointment";
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final Pattern EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Boolean verifyEmail(String email) {
        Matcher matcher = EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    @Override
    public void notify(CarEntity carOwner) {
        String email = carOwner.getEmail();
        if(verifyEmail(email)) {
            SimpleMailMessage message = new SimpleMailMessage();
            String ownerName = carOwner.getName();
            String preferredWash = carOwner.getPreferredWash();
            String body = generateEmailBody(ownerName, preferredWash);

            message.setFrom(fromEmail);
            message.setTo(email);
            message.setSubject(SUBJECT);
            message.setText(body);

            mailSender.send(message);

            log.info("message sent!");
        }
        // else customer submitted an invalid email...
    }
    private String generateEmailBody(String username, String washType) {
        return String.format("Hola Moja customer: %s \n\n your car has been given the %s it deserves.\n\n you can come collect your car any time.\n\n ensure that you have the car-tag with you\n\n Hola, Moja Team.",username,washType);
    }
}
