package com.example.booking.service;

import com.example.booking.model.Flight;
import java.util.List;

public interface FlightService {
    List<Flight> findAvailableFlights(String departureCity, String arrivalCity);
    void updateAvailableSeats(String flightNumber, int newAvailableSeats);
}
