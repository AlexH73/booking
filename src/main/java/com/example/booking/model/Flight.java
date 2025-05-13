package com.example.booking.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private String flightNumber;      // номер рейса
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime; // дата и время вылета
    private int durationInMinutes;
    private int totalSeats;
    private int availableSeats;       // доступные места

    public Flight(String arrivalCity, String departureCity, LocalDateTime departureTime, int durationInMinutes) {
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.departureTime = departureTime;
        this.durationInMinutes = durationInMinutes;
        this.flightNumber = generateFlightNumber();
    }

    private final String generateFlightNumber() {

        return this.departureCity.substring(0, 2)
                + this.arrivalCity.substring(0, 2)
                + this.durationInMinutes;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return durationInMinutes == flight.durationInMinutes && totalSeats == flight.totalSeats && availableSeats == flight.availableSeats && Objects.equals(flightNumber, flight.flightNumber) && Objects.equals(departureCity, flight.departureCity) && Objects.equals(arrivalCity, flight.arrivalCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, departureCity, arrivalCity, durationInMinutes, totalSeats, availableSeats);
    }
}
