package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerService implements ICustomerService {
    private static List<Customer> customers = new ArrayList<>();

    static{
        customers.add(new Customer(1,"son","sm@gmail.com","HN"));
        customers.add(new Customer(2,"son1","sm1@gmail.com","HN1"));
        customers.add(new Customer(3,"son2","sm2@gmail.com","HN2"));
        customers.add(new Customer(4,"son3","sm3@gmail.com","HN3"));
    }

    @Override
    public List<Customer> list() {

        return customers;
    }

    @Override
    public void findById(int id) {

    }

    @Override
    public void create(Customer customer) {
    customers.add(customer);
    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void delete(int id) {

    }
}
