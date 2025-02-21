package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerForm;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ICustomerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@PropertySource("classpath:upload_file.properties")
@RequestMapping("/customers")
public class CustomerController {
//    @RequestMapping("")
//    public String index() {
//        return "index";
//    }

    private final ICustomerService customerService = new CustomerService();
//    @RequestMapping("/list")
//    public String list(Model model) {
//        List<Customer>  customerList = customerService.list();
//        System.out.println(customerList);
//        model.addAttribute("customers", customerList);
//        return "list";
//    }

    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("list");
        List<Customer> customerList = customerService.list();
        mv.addObject("customerList", customerList);
        return mv;
//        List<Customer> customerList = new ArrayList<>();

//        customerList.add(new Customer(1, "son", "sm@gmail.com", "HN"));
//        customerList.add(new Customer(2, "son1", "sm1@gmail.com", "HN1"));
//        customerList.add(new Customer(3, "son2", "sm2@gmail.com", "HN2"));
//        customerList.add(new Customer(4, "son3", "sm3@gmail.com", "HN3"));
//        mv.addObject("customerList", customerList);
//        mv.addObject("customers", new Customer());
//        return mv;
    }

    @Value("${file_upload}")
    private String upload;

    @PostMapping("/save")
    public String save(CustomerForm customerForm) {
        System.out.println(customerForm);
//        LAY ANH
        MultipartFile file = customerForm.getImg();
        String fileName = file.getOriginalFilename();
//        SAO CHEP ANH VAO THU MUC
        byte a[];

        try {
            a = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileCopyUtils.copy(a, new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Customer customer = new Customer();
        customer.setImg(fileName);
        customer.setId(customerForm.getId());
        customer.setAddress(customerForm.getAddress());
        customer.setEmail(customerForm.getEmail());
        customer.setName(customerForm.getName());



//        ModelAndView mv = new ModelAndView("list");
//        mv.addObject("customers", customerService.list());
        customerService.create(customer);
//        return mv;
        return "redirect:/customers/list";
    }


    @GetMapping("create")
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("create");
        mv.addObject("customer", new Customer());
        return mv;
    }


}
