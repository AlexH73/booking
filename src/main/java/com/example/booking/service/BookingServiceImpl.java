package com.example.booking.service;

import com.example.booking.model.Flight;
import com.example.booking.model.Passenger;
import com.example.booking.model.Ticket;
import com.example.booking.model.TicketStatus;
import com.example.booking.repository.FlightRepository;
import com.example.booking.repository.TicketRepository;

import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса {@link BookingService}.
 * <p>
 * Управляет бронированием, отменой и поиском билетов.
 * Осуществляет валидацию и координацию между {@code TicketRepository} и {@code FlightRepository}.
 */
public class BookingServiceImpl implements BookingService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    /**
     * Конструктор принимает зависимости — репозитории билетов и рейсов.
     *
     * @param ticketRepository хранилище билетов
     * @param flightRepository хранилище рейсов
     */
    public BookingServiceImpl(TicketRepository ticketRepository, FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public Ticket bookTicket(Passenger passenger, Flight flight) {
        if (passenger == null || flight == null) return null;

        if (!isFlightAvailable(flight)) return null;

        Ticket ticket = new Ticket(flight, passenger);
        boolean added = ticketRepository.addTicket(ticket);
        if (added) {
            int updatedSeats = flight.getAvailableSeats() - 1;
            flightRepository.updateAvailableSeats(flight.getFlightNumber(), updatedSeats);
            return ticket;
        }
        return null;
    }

    @Override
    public void cancelTicket(String ticketId) {
        Ticket ticket = findTicketById(ticketId);
        if (ticket == null || ticket.getStatus() == TicketStatus.CANCELLED) return;

        ticket.setStatus(TicketStatus.CANCELLED);
        ticketRepository.update(ticket);

        Flight flight = ticket.getFlight();
        flightRepository.updateAvailableSeats(flight.getFlightNumber(), flight.getAvailableSeats() + 1);
    }

    @Override
    public List<Ticket> getTicketsByPassenger(Passenger passenger) {
        return ticketRepository.findByPassenger(passenger);
    }

    @Override
    public boolean isFlightAvailable(Flight flight) {
        return flight.getAvailableSeats() > 0;
    }

    @Override
    public Ticket findTicketById(String ticketId) {
        return ticketRepository.findAll().stream()
                .filter(t -> Objects.equals(t.getTicketId(), ticketId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status);
    }
}