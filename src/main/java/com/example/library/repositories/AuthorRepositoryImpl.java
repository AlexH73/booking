package com.example.library.repositories;

import practice.interfaces.AuthorRepository;
import practice.models.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {
    private List<Author> authors;

    public AuthorRepositoryImpl() {
        authors = new ArrayList<>();
    }

    @Override
    public void addAuthor(Author author) {
        if (authors.contains(author)) {
            System.out.println("Author has already exist");
        } else {
            authors.add(author);
            System.out.println("Author has been added");
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        return authors;
    }
}
