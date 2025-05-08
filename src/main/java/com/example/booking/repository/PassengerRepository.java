package com.example.booking.repository;

import com.example.booking.model.Passenger;

public interface PassengerRepository {
    Passenger findByPassport(String passportNumber);
    void save(Passenger passenger);
}
