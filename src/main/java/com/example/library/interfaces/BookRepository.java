package com.example.library.interfaces;


import com.example.library.models.Book;

import java.util.List;

// Слой доступа к данным (Data Access Layer)
public interface BookRepository {
    void addBook(Book book);
    List<Book> findAllBooks();
}

