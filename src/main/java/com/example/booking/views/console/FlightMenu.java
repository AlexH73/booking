package com.example.booking.views.console;

import com.example.booking.views.FlightView;

import java.util.Scanner;

public class FlightMenu {
    private final FlightView flightView;
    private final Scanner scanner;

    public FlightMenu(FlightView flightView, Scanner scanner) {
        this.flightView = flightView;
        this.scanner = scanner;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> flightView.registerFlightInput();
                case "2" -> flightView.findAvailableFlightsInput();
                //todo: список всех рейсов не работает
                case "3" -> flightView.showAllFlights();
                case "4" -> flightView.updateAvailableSeatsInput();
                //todo: удаление рейсов не работает
                case "5" -> flightView.deleteFlightInput();
                case "0" -> back = true;
                default -> System.out.println("Неверный ввод!\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== УПРАВЛЕНИЕ РЕЙСАМИ ===");
        System.out.println("1. Добавить рейс");
        System.out.println("2. Поиск доступных рейсов");
        System.out.println("3. Список всех рейсов");
        System.out.println("4. Обновить количество мест");
        System.out.println("5. Удалить рейс");
        System.out.println("0. Назад");
        System.out.print("Выберите действие: ");
    }
}
