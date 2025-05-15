package com.example.booking.view;

import com.example.booking.model.Flight;
import com.example.booking.model.Passenger;
import com.example.booking.model.Ticket;
import com.example.booking.model.TicketStatus;
import com.example.booking.service.BookingService;
import com.example.booking.service.FlightService;
import com.example.booking.service.PassengerService;

import java.util.List;
import java.util.Scanner;

/**
 * Класс представления для взаимодействия пользователя с билетами.
 * Использует сервисы бронирования, рейсов и пассажиров.
 */
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TicketView {

    private final BookingService bookingService;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final Scanner scanner;

    public TicketView(BookingService bookingService,
                      PassengerService passengerService,
                      FlightService flightService,
                      Scanner scanner) {
        this.bookingService = bookingService;
        this.passengerService = passengerService;
        this.flightService = flightService;
        this.scanner = scanner;
    }

    private String getUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public String bookTicketInput() {
        String passport = getUserInput("Введите номер паспорта пассажира: ");
        Passenger passenger = passengerService.findPassengerByPassport(passport);

        if (passenger == null) {
            return "Ошибка: Пассажир не найден.";
        }

        String flightNumber = getUserInput("Введите номер рейса: ");
        Flight flight = flightService.findByFlightNumber(flightNumber);

        if (flight == null) {
            return "Ошибка: Рейс не найден.";
        }

        Ticket ticket = bookingService.bookTicket(passenger, flight);
        return ticket != null ? "Билет успешно оформлен: " + ticket : "Ошибка: Не удалось оформить билет.";
    }

    public String cancelTicketInput() {
        String ticketId = getUserInput("Введите ID билета для отмены: ");

        Ticket ticket = bookingService.findTicketById(ticketId); // Vorher prüfen, ob Ticket existiert
        if (ticket == null) {
            return "Ошибка: Билет не найден.";
        }

        bookingService.cancelTicket(ticketId); // Ticket wird storniert
        return "Билет успешно отменён.";
    }

    public String showTicketsByPassenger() {
        String passport = getUserInput("Введите номер паспорта: ");
        Passenger passenger = passengerService.findPassengerByPassport(passport);

        if (passenger == null) {
            return "Ошибка: Пассажир не найден.";
        }

        List<Ticket> tickets = bookingService.getTicketsByPassenger(passenger);
        return tickets.isEmpty() ? "У пассажира нет билетов."
                : "Билеты пассажира:\n" + tickets.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    public String showTicketsByStatus() {
        String status = getUserInput("Введите статус билета (ACTIVE / CANCELLED): ");

        try {
            TicketStatus ticketStatus = TicketStatus.valueOf(status.toUpperCase());
            List<Ticket> tickets = bookingService.findTicketsByStatus(ticketStatus);
            return tickets.isEmpty() ? "Билеты со статусом " + status + " не найдены."
                    : "Список билетов:\n" + tickets.stream().map(String::valueOf).collect(Collectors.joining("\n"));
        } catch (IllegalArgumentException e) {
            return "Ошибка: Некорректный статус билета.";
        }
    }

    public String showAllTickets() {
        List<Ticket> tickets = bookingService.getAllTickets();
        return tickets.isEmpty() ? "В системе пока нет билетов."
                : "Все билеты:\n" + tickets.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }
}
