package com.example.booking.views.console;

import com.example.booking.views.PassengerView;

import java.util.Scanner;

public class PassengerMenu {
    private final PassengerView passengerView;
    private final Scanner scanner;

    public PassengerMenu(PassengerView passengerView, Scanner scanner) {
        this.passengerView = passengerView;
        this.scanner = scanner;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> passengerView.registerPassengerFromInput();
                case "2" -> passengerView.findPassengerByPassportInput();
                case "3" -> passengerView.updatePassengerByPassportInput();
                case "4" -> passengerView.deletePassengerByPassportInput();
                case "5" -> passengerView.displayAllPassengers();
                case "0" -> back = true;
                default -> System.out.println("Неверный ввод!\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== УПРАВЛЕНИЕ ПАССАЖИРАМИ ===");
        System.out.println("1. Регистрация нового пассажира");
        System.out.println("2. Поиск пассажира по паспорту");
        System.out.println("3. Обновление данных пассажира");
        System.out.println("4. Удаление пассажира");
        System.out.println("5. Список всех пассажиров");
        System.out.println("0. Назад");
        System.out.print("Выберите действие: ");
    }
}
