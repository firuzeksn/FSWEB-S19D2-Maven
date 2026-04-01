package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) { this.customerService = customerService; }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll().stream()
                .map(c -> new CustomerResponse(c.getId(), c.getEmail(), c.getSalary()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResponse find(@PathVariable Long id) {
        Customer c = customerService.find(id);
        return new CustomerResponse(c.getId(), c.getEmail(), c.getSalary());
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer) { // @RequestBody mutlaka olmalı!
        Customer saved = customerService.save(customer);
        return new CustomerResponse(saved.getId(), saved.getEmail(), saved.getSalary());
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        Customer updated = customerService.save(customer);
        return new CustomerResponse(updated.getId(), updated.getEmail(), updated.getSalary());
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable Long id) {
        Customer deleted = customerService.delete(id);
        return new CustomerResponse(deleted.getId(), deleted.getEmail(), deleted.getSalary());
    }
}