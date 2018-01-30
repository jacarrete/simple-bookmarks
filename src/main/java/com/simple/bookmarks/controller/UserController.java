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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
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
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        boolean error = false;
        user.setEnable(false);
        if (StringUtils.isEmpty(user.getUsername())) {
            result.rejectValue("username", "error.empty.username");
            error = true;
        } else if(userRepository.findUserByUsername(user.getUsername()) != null) {
            result.rejectValue("username", "error.username");
            error = true;
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            result.rejectValue("password", "error.empty.password");
            error = true;
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            result.rejectValue("email", "error.empty.email");
            error = true;
        } else if(userRepository.findUserByEmail(user.getEmail()) != null) {
            result.rejectValue("email", "error.email");
            error = true;
        } else if (!isValidEmailAddress(user.getEmail())) {
            result.rejectValue("email", "error.format.email");
            error = true;
        }
        if(error) {
            return "registration";
        }
        userRepository.save(user);
        sendSimpleMessage(user);
        return "registrationSuccess";
    }

    @RequestMapping(value = "/registrationSuccess", method = RequestMethod.GET, headers = "Accept=application/json")
    public String registrationSuccess(Model model) {
        return "registrationSuccess";
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

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}