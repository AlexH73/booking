package com.example.booking.repository.flight;

import com.example.booking.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightRepositoryImpl implements FlightRepository {
    private List<Flight> flights = new ArrayList<>();

    @Override
    public Flight findByFlightNumber(String flightNumber) {
        return flights.stream()
                .filter(flight -> flight.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Flight> findAvailableFlights(String departureCity, String arrivalCity) {
        return flights.stream()
                .filter(flight -> flight.getDepartureCity().equals(departureCity)
                        && flight.getArrivalCity().equals(arrivalCity)
                        && flight.getAvailableSeats() > 0)
                .collect(Collectors.toList());
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
