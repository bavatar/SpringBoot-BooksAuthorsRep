package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class HomeController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

}
