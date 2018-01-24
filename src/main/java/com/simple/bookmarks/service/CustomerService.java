package com.simple.bookmarks.service;

import com.simple.bookmarks.dao.CustomerDao;
import com.simple.bookmarks.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
/**
 * Created by jcarretero on 23/01/2018.
 */
@Service("customerService")
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Transactional
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Transactional
    public Customer getCustomer(int id) {
        return customerDao.getCustomer(id);
    }

    @Transactional
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Transactional
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);

    }

    @Transactional
    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }
}