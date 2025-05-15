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

    /**
     * Оформление нового билета: ввод паспорта и номера рейса.
     */
    public void bookTicketInput() {
        System.out.print("Введите номер паспорта пассажира: ");
        String passport = scanner.nextLine();
        Passenger passenger = passengerService.findPassengerByPassport(passport);

        if (passenger == null) {
            System.out.println("Пассажир не найден.");
            return;
        }

        System.out.print("Введите номер рейса: ");
        String flightNumber = scanner.nextLine();
        Flight flight = flightService.findByFlightNumber(flightNumber);

        if (flight == null) {
            System.out.println("Рейс не найден.");
            return;
        }

        Ticket ticket = bookingService.bookTicket(passenger, flight);
        if (ticket != null) {
            System.out.println("Билет успешно оформлен: " + ticket);
        } else {
            System.out.println("Не удалось оформить билет.");
        }
    }

    /**
     * Отмена ранее оформленного билета по ID.
     */
    public void cancelTicketInput() {
        System.out.print("Введите ID билета для отмены: ");
        String ticketId = scanner.nextLine();
        bookingService.cancelTicket(ticketId);
        System.out.println("Билет отменён (если был найден).\n");
    }

    /**
     * Показ всех билетов по номеру паспорта.
     */
    public void showTicketsByPassenger() {
        System.out.print("Введите номер паспорта: ");
        String passport = scanner.nextLine();
        Passenger passenger = passengerService.findPassengerByPassport(passport);
        if (passenger == null) {
            System.out.println("Пассажир не найден.");
            return;
        }
        List<Ticket> tickets = bookingService.getTicketsByPassenger(passenger);
        if (tickets.isEmpty()) {
            System.out.println("У пассажира нет билетов.");
        } else {
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        }
    }

    /**
     * Показ всех билетов с возможностью фильтрации по статусу.
     */
    public void showTicketsByStatus() {
        System.out.print("Введите статус билета (ACTIVE / CANCELLED): ");
        String status = scanner.nextLine();
        try {
            TicketStatus ticketStatus = TicketStatus.valueOf(status.toUpperCase());
            List<Ticket> tickets = bookingService.findTicketsByStatus(ticketStatus);
            if (tickets.isEmpty()) {
                System.out.println("Билеты со статусом " + status + " не найдены.");
            } else {
                for (Ticket ticket : tickets) {
                    System.out.println(ticket);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный статус билета.");
        }
    }

    /**
     * Показать все билеты в системе.
     */
    public void showAllTickets() {
        List<Ticket> tickets = bookingService.getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("В системе пока нет билетов.");
        } else {
            for (Ticket t : tickets) {
                System.out.println(t);
            }
        }
    }
}
