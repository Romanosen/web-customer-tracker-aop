package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {



    //need to inject customer service
    @Autowired
    private CustomerService customerService;
    @GetMapping("/list")
    public String listCustomers(Model theModel){


        //get customers from the service
        List<Customer> theCustomers =customerService.getCustomers();

        //add customers to the spring mvc model
        theModel.addAttribute("customers",theCustomers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //create model attribute for customer
        Customer theCustomer=new Customer();
        theModel.addAttribute("customer",theCustomer);


        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
    {
        //save the customer
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel)
    {
        // get customer from service
        Customer theCustomer=customerService.getCustomer(theId);
        //set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer",theCustomer);
        //send over to our form
        return "customer-form";

    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId)
    {
        //delete customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }


}