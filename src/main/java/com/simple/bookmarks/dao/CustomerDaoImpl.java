package com.simple.bookmarks.dao;

import com.simple.bookmarks.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by jcarretero on 23/01/2018.
 */
@Repository
public class CustomerDaoImpl implements CustomerDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<Customer> getAllCustomers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Customer>  customerList = session.createQuery("from Customer").list();
        return customerList;
    }

    public Customer getCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Customer customer = (Customer) session.get(Customer.class, id);
        return customer;
    }

    public Customer addCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(customer);
        return customer;
    }

    public void updateCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(customer);
    }

    public void deleteCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Customer p = (Customer) session.load(Customer.class, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
    }
}
