package com.example.booking.exceptions;

public class IncorrectPassengerDataException extends Exception{
    public IncorrectPassengerDataException(String message) {
        System.err.println(message);
    }
}
