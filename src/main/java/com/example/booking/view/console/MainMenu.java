package com.example.booking.view.console;

import java.util.Scanner;

public class MainMenu {
    private final PassengerMenu passengerMenu;
    private final FlightMenu flightMenu;
    private final TicketMenu ticketMenu;
    private final Scanner scanner;

    public MainMenu(PassengerMenu passengerMenu,
                    FlightMenu flightMenu,
                    TicketMenu ticketMenu,
                    Scanner scanner) {
        this.passengerMenu = passengerMenu;
        this.flightMenu = flightMenu;
        this.ticketMenu = ticketMenu;
        this.scanner = scanner;
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> passengerMenu.show();
                case "2" -> flightMenu.show();
                case "3" -> ticketMenu.show();
                case "0" -> exit = true;
                default -> System.out.println("Неверный ввод!\n");
            }
        }
        System.out.println("Работа системы завершена.");
    }

    private void printMainMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Управление пассажирами");
        System.out.println("2. Управление рейсами");
        System.out.println("3. Управление билетами");
        System.out.println("0. Выход");
        System.out.print("Выберите раздел: ");
    }
}
