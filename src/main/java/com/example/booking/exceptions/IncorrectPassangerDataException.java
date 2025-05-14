package com.example.booking.exceptions;

public class IncorrectPassangerDataException extends Exception{
    public IncorrectPassangerDataException(String message) {
        System.err.println(message);
    }
}
