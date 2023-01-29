package br.com.cod3r.springapi.models.repositories;

import br.com.cod3r.springapi.models.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID>, PagingAndSortingRepository<Product, UUID> {
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    public Iterable<Product> findByNameContainingIgnoreCase(String name);
}
