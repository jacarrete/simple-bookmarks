package com.simple.bookmarks.dao;

import com.simple.bookmarks.model.Customer;

import java.util.List;

/**
 * Created by jcarretero on 23/01/2018.
 */
public interface CustomerDao {
    List<Customer> getAllCustomers();

    Customer getCustomer(int id);

    Customer addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int id);

}