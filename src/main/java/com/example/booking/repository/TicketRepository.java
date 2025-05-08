package com.example.booking.repository;

import com.example.booking.model.Ticket;
import com.example.booking.model.Passenger;
import java.util.List;

public interface TicketRepository {
    Ticket findById(String ticketId);
    List<Ticket> findByPassenger(Passenger passenger);
    void save(Ticket ticket);
    void delete(String ticketId);
}
