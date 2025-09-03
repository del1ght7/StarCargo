package com.starcargo.controller;

import com.starcargo.model.ContactForm;
import com.starcargo.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Main controller handling all web requests for StarCargo website
 */
@Controller
public class MainController {

    @Autowired
    private EmailService emailService;

    /**
     * Homepage
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "index";
    }

    /**
     * About us page
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    /**
     * Services page
     */
    @GetMapping("/services")
    public String services() {
        return "services";
    }

    /**
     * Contact page
     */
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    /**
     * Handle contact form submission
     */
    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute ContactForm contactForm, 
                              BindingResult result, 
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "contact";
        }

        try {
            emailService.sendContactEmail(contactForm);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Ваше сообщение отправлено успешно! Мы свяжемся с вами в ближайшее время.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Произошла ошибка при отправке сообщения. Попробуйте еще раз.");
        }

        return "redirect:/contact";
    }

    /**
     * Tracking page
     */
    @GetMapping("/tracking")
    public String tracking() {
        return "tracking";
    }

    /**
     * Quote request page
     */
    @GetMapping("/quote")
    public String quote(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "quote";
    }

    /**
     * Handle quote form submission
     */
    @PostMapping("/quote")
    public String submitQuote(@Valid @ModelAttribute ContactForm contactForm, 
                            BindingResult result, 
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "quote";
        }

        try {
            emailService.sendQuoteEmail(contactForm);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Ваш запрос на расчет стоимости отправлен! Мы подготовим предложение в течение 24 часов.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Произошла ошибка при отправке запроса. Попробуйте еще раз.");
        }

        return "redirect:/quote";
    }
}
