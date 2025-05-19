package com.example.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    private Flight flight;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private int duration;

    @BeforeEach
    void setUp() {
        departureCity = "Paris";
        arrivalCity = "London";
        departureTime = LocalDateTime.of(2025, 6, 1, 10, 30);
        duration = 120;
        flight = new Flight(arrivalCity, departureCity, departureTime, duration);
        flight.setTotalSeats(200);
        flight.setAvailableSeats(150);
    }

    @Test
    void testFlightNumberGeneration() {
        // Expected: first 2 letters of departure + first 2 letters of arrival + duration
        String expectedCode = departureCity.substring(0, 2) + arrivalCity.substring(0, 2) + duration;
        assertEquals(expectedCode, flight.getFlightNumber(), "Flight number should be generated correctly");
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(arrivalCity, flight.getArrivalCity());
        assertEquals(departureCity, flight.getDepartureCity());
        assertEquals(departureTime, flight.getDepartureTime());
        assertEquals(duration, flight.getDurationInMinutes());
        assertEquals(200, flight.getTotalSeats());
        assertEquals(150, flight.getAvailableSeats());
    }

    @Test
    void testEqualsAndHashCode() {
        Flight sameFlight = new Flight(arrivalCity, departureCity, departureTime, duration);
        sameFlight.setTotalSeats(200);
        sameFlight.setAvailableSeats(150);

        assertEquals(flight, sameFlight, "Flights with same properties should be equal");
        assertEquals(flight.hashCode(), sameFlight.hashCode(), "Hash codes should match for equal flights");

        // Modify one property and ensure inequality
        sameFlight.setAvailableSeats(149);
        assertNotEquals(flight, sameFlight, "Flights should not be equal when a property differs");
    }

    @Test
    void testToString() {
        String str = flight.toString();
        assertTrue(str.contains("arrivalCity='" + arrivalCity + "'"));
        assertTrue(str.contains("departureCity='" + departureCity + "'"));
        assertTrue(str.contains("durationInMinutes=" + duration));
        assertTrue(str.contains("totalSeats=200"));
        assertTrue(str.contains("availableSeats=150"));
    }
}

