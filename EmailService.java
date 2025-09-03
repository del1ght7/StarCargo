package com.starcargo.service;

import com.starcargo.model.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service for handling email operations
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${starcargo.email.to:info@starcargo.com}")
    private String companyEmail;

    @Value("${starcargo.email.from:noreply@starcargo.com}")
    private String fromEmail;

    /**
     * Send contact form email
     */
    public void sendContactEmail(ContactForm contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(companyEmail);
        message.setFrom(fromEmail);
        message.setSubject("Новое сообщение с сайта StarCargo от: " + contactForm.getName());
        
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Получено новое сообщение с сайта StarCargo:\n\n");
        emailBody.append("Имя: ").append(contactForm.getName()).append("\n");
        emailBody.append("Email: ").append(contactForm.getEmail()).append("\n");
        emailBody.append("Телефон: ").append(contactForm.getPhone() != null ? contactForm.getPhone() : "Не указан").append("\n");
        emailBody.append("Компания: ").append(contactForm.getCompany()).append("\n");
        if (contactForm.getSubject() != null && !contactForm.getSubject().isEmpty()) {
            emailBody.append("Тема: ").append(contactForm.getSubject()).append("\n");
        }
        emailBody.append("Сообщение:\n").append(contactForm.getMessage());

        message.setText(emailBody.toString());
        
        mailSender.send(message);
    }

    /**
     * Send quote request email
     */
    public void sendQuoteEmail(ContactForm contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(companyEmail);
        message.setFrom(fromEmail);
        message.setSubject("Запрос стоимости доставки от: " + contactForm.getName());
        
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Получен запрос на расчет стоимости доставки:\n\n");
        emailBody.append("Контактная информация:\n");
        emailBody.append("Имя: ").append(contactForm.getName()).append("\n");
        emailBody.append("Email: ").append(contactForm.getEmail()).append("\n");
        emailBody.append("Телефон: ").append(contactForm.getPhone() != null ? contactForm.getPhone() : "Не указан").append("\n");
        emailBody.append("Компания: ").append(contactForm.getCompany()).append("\n\n");
        
        emailBody.append("Детали груза:\n");
        if (contactForm.getCargoType() != null && !contactForm.getCargoType().isEmpty()) {
            emailBody.append("Тип груза: ").append(contactForm.getCargoType()).append("\n");
        }
        if (contactForm.getFromLocation() != null && !contactForm.getFromLocation().isEmpty()) {
            emailBody.append("Откуда: ").append(contactForm.getFromLocation()).append("\n");
        }
        if (contactForm.getToLocation() != null && !contactForm.getToLocation().isEmpty()) {
            emailBody.append("Куда: ").append(contactForm.getToLocation()).append("\n");
        }
        if (contactForm.getWeight() != null && !contactForm.getWeight().isEmpty()) {
            emailBody.append("Вес: ").append(contactForm.getWeight()).append("\n");
        }
        if (contactForm.getDimensions() != null && !contactForm.getDimensions().isEmpty()) {
            emailBody.append("Размеры: ").append(contactForm.getDimensions()).append("\n");
        }
        if (contactForm.getDeliveryDate() != null && !contactForm.getDeliveryDate().isEmpty()) {
            emailBody.append("Желаемая дата доставки: ").append(contactForm.getDeliveryDate()).append("\n");
        }
        
        emailBody.append("\nДополнительная информация:\n").append(contactForm.getMessage());

        message.setText(emailBody.toString());
        
        mailSender.send(message);
    }
}
