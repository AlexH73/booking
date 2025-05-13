package com.example.library.services;


import com.example.library.interfaces.BookRepository;

//Слой бизнес-логики
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
