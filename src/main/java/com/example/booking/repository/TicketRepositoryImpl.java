package com.example.booking.repository;

import com.example.booking.model.Passenger;
import com.example.booking.model.Ticket;
import com.example.booking.model.TicketStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Реализация интерфейса {@link TicketRepository}.
 *
 * <p>Этот класс управляет хранилищем билетов. Для хранения билетов можно использовать коллекцию Map:
 * {@code Map<String, Ticket>} — ключом будет {@code ticketId}, значением — объект {@code Ticket}.
 *
 * <p>Если нужно найти билеты по пассажиру, можно просматривать все билеты и сравнивать их владельцев.
 *
 * <p>Ниже описаны шаги для реализации каждого метода. Тело методов пока пустое — реализовать их должны студенты.
 */
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository {

    // Внутреннее хранилище всех билетов: ticketId -> Ticket
    private final Map<String, Ticket> storage = new HashMap<>();

    @Override
    public Ticket findById(String ticketId) {
        if (ticketId == null) {
            throw new IllegalArgumentException("Ошибка: TicketId darf nicht null sein.");
        }
        return storage.get(ticketId);
    }


    @Override
    public List<Ticket> findByPassenger(Passenger passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Ошибка: Passenger darf nicht null sein.");
        }
        return storage.values().stream()
                .filter(ticket -> ticket.getPassenger().equals(passenger))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Ticket> findByStatus(TicketStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Ошибка: TicketStatus darf nicht null sein.");
        }
        return storage.values().stream()
                .filter(ticket -> ticket.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addTicket(Ticket ticket) {
        if (ticket == null || ticket.getTicketId() == null) {
            throw new IllegalArgumentException("Ошибка: Ticket oder TicketId darf nicht null sein.");
        }
        if (storage.containsKey(ticket.getTicketId())) {
            return false; // Ticket existiert bereits
        }
        storage.put(ticket.getTicketId(), ticket);
        return true;
    }

    @Override
    public boolean delete(String ticketId) {
        if (ticketId == null) {
            throw new IllegalArgumentException("Ошибка: TicketId darf nicht null sein.");
        }
        return storage.remove(ticketId) != null;
    }

    @Override
    public boolean update(Ticket ticket) {
        if (ticket == null || ticket.getTicketId() == null) {
            throw new IllegalArgumentException("Ошибка: Ticket oder TicketId darf nicht null sein.");
        }
        if (!storage.containsKey(ticket.getTicketId())) {
            return false; // Ticket existiert nicht
        }
        storage.put(ticket.getTicketId(), ticket);
        return true;
    }
}

