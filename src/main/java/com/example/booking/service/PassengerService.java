package com.example.booking.service;

import com.example.booking.model.Passenger;

public interface PassengerService {
    void registerPassenger(Passenger passenger);
    Passenger findPassengerByPassport(String passportNumber);
}
