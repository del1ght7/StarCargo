package com.starcargo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Model class for contact and quote forms
 */
@Data
public class ContactForm {
    
    @NotBlank(message = "Имя обязательно для заполнения")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    private String name;
    
    @NotBlank(message = "Email обязателен для заполнения")
    @Email(message = "Введите корректный email адрес")
    private String email;
    
    @Size(max = 20, message = "Номер телефона не должен превышать 20 символов")
    private String phone;
    
    @NotBlank(message = "Компания обязательна для заполнения")
    @Size(min = 2, max = 100, message = "Название компании должно содержать от 2 до 100 символов")
    private String company;
    
    private String subject;
    
    @NotBlank(message = "Сообщение обязательно для заполнения")
    @Size(min = 10, max = 1000, message = "Сообщение должно содержать от 10 до 1000 символов")
    private String message;
    
    // Дополнительные поля для запроса стоимости
    private String cargoType;
    private String fromLocation;
    private String toLocation;
    private String weight;
    private String dimensions;
    private String deliveryDate;
    
    public ContactForm() {}
}
