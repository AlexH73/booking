package com.example.booking.repository;

import com.example.booking.model.Flight;
import java.util.List;

public interface FlightRepository {
    Flight findByFlightNumber(String flightNumber);
    List<Flight> findAvailableFlights(String departureCity, String arrivalCity);
    void updateAvailableSeats(String flightNumber, int newAvailableSeats);
    boolean addFlight(Flight flight);
    boolean deleteFlight(Flight flight);
}
