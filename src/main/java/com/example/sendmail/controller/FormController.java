package com.example.sendmail.controller;

import com.example.sendmail.model.Customer;
import com.example.sendmail.repo.CustomerRepository;
import com.example.sendmail.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/form")
public class FormController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping
    public String submitForm(Customer customer) {
        customerRepository.save(customer);
        try {
            emailService.sendEmail(customer.getEmail(), "Submission Successful", "Thank you for your submission!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "redirect:/form?error";
        }
        return "redirect:/form?success=true";
    }
}
