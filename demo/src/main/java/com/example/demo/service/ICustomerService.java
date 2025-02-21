package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.Customer;

public interface ICustomerService {
    List<Customer> list();
    void findById(int id);

    void create(Customer customer);

    void update(int id, Customer customer);
    void delete(int id);

}
