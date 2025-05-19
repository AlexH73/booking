package com.example.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    private Ticket ticket;
    private Passenger passenger;
    private Flight flight;

    @BeforeEach
    void setUp() {
        // Используем конструкторы с обязательными параметрами
        passenger = new Passenger(
                "John Doe",
                LocalDate.of(1990, 1, 15),
                "john.doe@example.com",
                "+1234567890"
        );

        flight = new Flight(
                "Dublin",                   // arrivalCity
                "London",                   // departureCity
                LocalDateTime.of(2025, 7, 20, 14, 0),
                120                          // durationInMinutes
        );
        flight.setTotalSeats(180);
        flight.setAvailableSeats(50);

        ticket = new Ticket(flight, passenger);
        // статус внутри конструктора устанавливается как ACTIVE
    }

    @Test
    void testDefaultGeneratedIds() {
        assertNotNull(ticket.getTicketId(), "ticketId должен генерироваться автоматически");
        assertNotNull(passenger.getPassportNumber(), "passportNumber должен генерироваться автоматически");
        assertNotNull(flight.getFlightNumber(), "flightNumber должен генерироваться автоматически");
    }

    @Test
    void testGetters() {
        assertEquals(passenger, ticket.getPassenger());
        assertEquals(flight, ticket.getFlight());
        assertEquals(TicketStatus.ACTIVE, ticket.getStatus());
    }

    @Test
    void testEqualsAndHashCode() throws Exception {
        // Создаём новый билет и приводим его ticketId к тому же значению для корректного сравнения
        Ticket sameTicket = new Ticket(flight, passenger);

        // Синхронизируем ticketId через рефлексию
        java.lang.reflect.Field idField = Ticket.class.getDeclaredField("ticketId");
        idField.setAccessible(true);
        idField.set(sameTicket, ticket.getTicketId());

        assertEquals(ticket, sameTicket, "Tickets с одинаковыми свойствами и ticketId должны быть равны");
        assertEquals(ticket.hashCode(), sameTicket.hashCode(), "HashCode должны совпадать для равных объектов");

        // Проверяем, что другой пассажир приводит к неравенству
        Passenger otherPassenger = new Passenger(
                "Jane Roe",
                LocalDate.of(1992, 5, 10),
                "jane.roe@example.com",
                "+0987654321"
        );
        Ticket diffTicket = new Ticket(flight, otherPassenger);
        assertNotEquals(ticket, diffTicket, "Tickets не должны быть равны при разных пассажирах");
    }

    @Test
    void testToString() {
        String str = ticket.toString();
        assertTrue(str.contains("Ticket{"), "toString должна содержать имя класса");
        assertTrue(str.contains("status=ACTIVE"), "toString должна показывать текущий статус");
        assertTrue(str.contains("passenger=Passenger{name='John Doe'"), "toString должна включать данные пассажира");
        assertTrue(str.contains("flight=Flight{arrivalCity='Dublin'"), "toString должна включать данные рейса");
    }
}
