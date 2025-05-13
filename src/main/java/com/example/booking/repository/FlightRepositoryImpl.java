package com.example.booking.repository;

import com.example.booking.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {
    private List<Flight> flights = new ArrayList<>();

    @Override
    public Flight findByFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    @Override
    public List<Flight> findAvailableFlights(String departureCity, String arrivalCity) {
        List<Flight> availableFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDepartureCity().equals(departureCity) && flight.getArrivalCity().equals(arrivalCity)) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }

    @Override
    public void updateAvailableSeats(String flightNumber, int newAvailableSeats) {
        Flight fli = findByFlightNumber(flightNumber);
        fli.setAvailableSeats(newAvailableSeats);

    }

    @Override
    public boolean addFlight(Flight flight) {
        return flights.add(flight);
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return flights.remove(flight);
    }
}
