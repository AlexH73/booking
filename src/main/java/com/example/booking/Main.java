package com.example.booking;

import com.example.booking.repository.flight.*;
import com.example.booking.repository.passenger.*;
import com.example.booking.repository.ticket.*;
import com.example.booking.service.ticket.*;
import com.example.booking.service.flight.*;
import com.example.booking.service.passenger.*;
import com.example.booking.views.*;
import com.example.booking.views.console.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Инициализация зависимостей
        PassengerRepository passengerRepo = new PassengerRepositoryImpl();
        FlightRepository flightRepo = new FlightRepositoryImpl();
        TicketRepository ticketRepo = new TicketRepositoryImpl();

        PassengerService passengerService = new PassengerServiceImpl(passengerRepo);
        FlightService flightService = new FlightServiceImpl(flightRepo);
        BookingService bookingService = new BookingServiceImpl(ticketRepo, flightRepo);

        PassengerView passengerView = new PassengerView(passengerService, scanner);
        FlightView flightView = new FlightView(flightService, scanner);
        TicketView ticketView = new TicketView(bookingService, passengerService, flightService, scanner);

        // Создание меню
        MainMenu mainMenu = new MainMenu(
                new PassengerMenu(passengerView, scanner),
                new FlightMenu(flightView, scanner),
                new TicketMenu(ticketView, scanner),
                scanner
        );

        mainMenu.start();
    }
}