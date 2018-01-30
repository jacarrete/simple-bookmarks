package com.simple.bookmarks.controller;

import com.simple.bookmarks.model.User;
import com.simple.bookmarks.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * Created by jcarretero on 23/01/2018.
 */
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private MessageSource messageSource;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @Value("${web.mail}")
    private String webMail;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET, headers = "Accept=application/json")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public String addUser(@ModelAttribute("user") User user) {
        user.setEnable(false);
        userRepository.save(user);
        sendSimpleMessage(user);
        return "redirect:/registration";
    }

    private void sendSimpleMessage(User user){
        log.info("Spring Mail - Sending Email for approval");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(messageSource.getMessage("approval.account", null, Locale.UK));
        message.setText(messageSource.getMessage("new.user.to.ldap", null, Locale.UK) + System.lineSeparator() +  System.lineSeparator() +
                "email: " + user.getEmail() + System.lineSeparator() +
                "username: " + user.getUsername() + System.lineSeparator() +
                "password: " + user.getPassword());
        message.setTo(webMail);
        message.setFrom(webMail);
        try {
            emailSender.send(message);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

}