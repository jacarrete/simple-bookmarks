package com.simple.bookmarks.controller;

import com.simple.bookmarks.model.Customer;
import com.simple.bookmarks.model.User;
import com.simple.bookmarks.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

@Controller
public class DashboardController {

	@Autowired
	private CustomerService customerService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); //get logged in username
		ModelAndView model = new ModelAndView();
    	model.addObject("users", getUsers());
		model.addObject("username", name);
		List<Customer> listOfCustomers = customerService.getAllCustomers();
		model.addObject("customer", new Customer());
		model.addObject("listOfCustomers", listOfCustomers);
		model.setViewName("dashboard");
    	return model;
    }

	private List<User> getUsers() {
		User user = new User();
		user.setEmail("johndoe123@gmail.com");
		user.setName("John Doe");
		user.setAddress("Bangalore, Karnataka");
		User user1 = new User();
		user1.setEmail("amitsingh@yahoo.com");
		user1.setName("Amit Singh");
		user1.setAddress("Chennai, Tamilnadu");
		User user2 = new User();
		user2.setEmail("bipulkumar@gmail.com");
		user2.setName("Bipul Kumar");
		user2.setAddress("Bangalore, Karnataka");
		User user3 = new User();
		user3.setEmail("prakashranjan@gmail.com");
		user3.setName("Prakash Ranjan");
		user3.setAddress("Chennai, Tamilnadu");
		return Arrays.asList(user, user1, user2, user3);
	}

}
