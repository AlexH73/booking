package com.example.library.services;

import practice.interfaces.BookRepository;

//Слой бизнес-логики
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
