package com.example.booking.service;

import com.example.booking.model.Ticket;
import com.example.booking.model.Passenger;
import com.example.booking.model.Flight;
import java.util.List;

public interface BookingService {
    Ticket bookTicket(Passenger passenger, Flight flight);
    void cancelTicket(String ticketId);
    List<Ticket> getTicketsByPassenger(Passenger passenger);
}
