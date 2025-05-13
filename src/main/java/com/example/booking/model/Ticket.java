package com.example.booking.model;

import java.util.Objects;

public class Ticket {
    private String ticketId;          // номер билета
    private Passenger passenger;
    private Flight flight;
    private TicketStatus status;      // статус: ACTIVE/CANCELLED
    private static int ticketCounter = 0;

    public Ticket(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
        this.status = TicketStatus.ACTIVE;
        this.ticketId = String.valueOf(ticketCounter);
        ticketCounter ++;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getTicketId() {
        return ticketId;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(passenger, ticket.passenger) && Objects.equals(flight, ticket.flight) && status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, passenger, flight, status);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", status=" + status +
                '}';
    }
}
