package com.simple.bookmarks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Created by jcarretero on 23/01/2018.
 */
@Entity
@Table(name="CUSTOMER")
public class Customer{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="customerName")
    String customerName;

    @Column(name="email")
    String email;

    public Customer() {
        super();
    }
    public Customer(String customerName,String email) {
        super();
        this.customerName=customerName;
        this.email=email;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
