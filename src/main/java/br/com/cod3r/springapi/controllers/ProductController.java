package br.com.cod3r.springapi.controllers;

import br.com.cod3r.springapi.models.entities.Product;
import br.com.cod3r.springapi.models.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    private Product store(
            @Valid @RequestBody Product product
    ) {
        return this.productRepository.save(product);
    }

    @GetMapping
    public Iterable<Product> getAll(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer quantity
    ) {
        if(offset == null) offset = 0;

        if(quantity == null) quantity = 25;
        else if(quantity < 1) quantity = 1;
        else if(quantity > 50) quantity = 50;

        PageRequest page = PageRequest.of(offset, quantity);
        return this.productRepository.findAll(page);
    }

    @GetMapping(params = {"name"})
    public Iterable<Product> getAll(@RequestParam(required = false) String name) {
        return this.productRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable UUID id) {
        return this.productRepository.findById(id);
    }

    @PatchMapping("/{ìd}")
    public Product update(@PathVariable UUID id, @RequestBody Product product) {
        // why .save() is used: https://stackoverflow.com/a/43720154/14229372
        return this.productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable UUID id) {
        this.productRepository.deleteById(id);
    }
}
