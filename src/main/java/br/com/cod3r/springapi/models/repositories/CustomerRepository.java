package br.com.cod3r.springapi.models.repositories;

import br.com.cod3r.springapi.models.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID>, PagingAndSortingRepository<Customer, UUID> {
    public Iterable<Customer> findByNameContainingIgnoreCase(String name);
}
