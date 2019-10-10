package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BookRepository extends CrudRepository<Book, Long> {
//    ArrayList<Book> results = (ArrayList<Book>) bookRepository.findByAuthorID(id);
    //Iterable<Customer> findAllByLastNameContainingIgnoreCase(String lastName);
    Iterable<Book> findByAuthor(Author author);
}
