package br.com.cod3r.springapi.controllers;

import br.com.cod3r.springapi.models.entities.Customer;
import br.com.cod3r.springapi.models.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public Iterable<Customer> getAll(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer quantity
    ) {
        if(offset == null) offset = 0;

        if(quantity == null) quantity = 25;
        else if(quantity < 1) quantity = 1;
        else if(quantity > 50) quantity = 50;

        PageRequest page = PageRequest.of(offset, quantity);
        return this.customerRepository.findAll(page);
    }

    @GetMapping(params = {"name"})
    public Iterable<Customer> getAll(@RequestParam(required = false) String name) {
        return this.customerRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getById(@PathVariable UUID id) {
        return this.customerRepository.findById(id);
    }

    @PostMapping
    public Customer store(
            @Valid @RequestBody Customer customer
    ) {
        return this.customerRepository.save(customer);
    }
}
