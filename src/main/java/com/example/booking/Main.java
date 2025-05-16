package com.example.booking;

import com.example.booking.repository.*;
import com.example.booking.service.*;
import com.example.booking.view.*;
import com.example.booking.view.console.*;

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