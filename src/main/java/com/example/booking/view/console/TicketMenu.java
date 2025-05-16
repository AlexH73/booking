package com.example.booking.view.console;

import com.example.booking.view.TicketView;

import java.util.Scanner;

public class TicketMenu {
    private final TicketView ticketView;
    private final Scanner scanner;

    public TicketMenu(TicketView ticketView, Scanner scanner) {
        this.ticketView = ticketView;
        this.scanner = scanner;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> ticketView.bookTicketInput();
                case "2" -> ticketView.cancelTicketInput();
                case "3" -> ticketView.showTicketsByPassenger();
                case "4" -> ticketView.showTicketsByStatus();
                case "5" -> ticketView.showAllTickets();
                case "0" -> back = true;
                default -> System.out.println("Неверный ввод!\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== УПРАВЛЕНИЕ БИЛЕТАМИ ===");
        System.out.println("1. Оформить билет");
        System.out.println("2. Отменить билет");
        System.out.println("3. Билеты пассажира");
        System.out.println("4. Билеты по статусу");
        System.out.println("5. Все билеты");
        System.out.println("0. Назад");
        System.out.print("Выберите действие: ");
    }
}
