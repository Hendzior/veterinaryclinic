package com.hendzior.web.controller;

import com.hendzior.core.dao.CustomerDataAccess;
import com.hendzior.core.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/customers")
@Controller
public class CustomerController {


    private CustomerDataAccess customerDataAccess;

    public CustomerController(CustomerDataAccess customerDataAccess) {
        this.customerDataAccess = customerDataAccess;
    }

    @GetMapping
    public String getCustomers(ModelMap modelMap) {
        modelMap.addAttribute("message", "Customers List");
        modelMap.addAttribute("customers", customerDataAccess.findAll());
        return "show";
    }

    @GetMapping("/add")
    public String createCustomer(ModelMap modelMap) {
        if (!modelMap.containsAttribute("customer")) {
            modelMap.addAttribute("customer", new Customer());
        }
        return "add";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute @Valid Customer customer, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("customer", customer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.customer", bindingResult);
            return "redirect:/customers/add";
        }
        modelMap.addAttribute("message", "Customer" + customer.getName() + " " + customer.getLastName() + " added!");
        customerDataAccess.save(customer);
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id, ModelMap modelMap) {
        if (customerDataAccess.findById(id).isPresent()) {
            Customer customer = customerDataAccess.findById(id).get();
            modelMap.addAttribute("message", "Customer " + customer.getName() + " " + customer.getLastName() + " deleted!");
            customerDataAccess.delete(customer);
        } else {
            modelMap.addAttribute("message", "Customer with id: " + id + " not found!");
        }
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String editCustomer(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("customer", customerDataAccess.findById(id));
        return "add";
    }
}
