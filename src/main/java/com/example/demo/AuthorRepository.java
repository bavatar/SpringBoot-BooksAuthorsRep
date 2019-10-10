package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByAuthorID(Long id);
//    long countByState(String state);
//    Iterable<Customer> findAllByLastNameContainingIgnoreCase(String lastName);
}