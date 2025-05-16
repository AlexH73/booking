package com.example.booking;

import com.example.booking.repository.*;
import com.example.booking.service.*;
import com.example.booking.view.*;

import java.util.Scanner;

/**
 * Главное меню и точка входа в консольное приложение.
 * <p>
 * Инициализирует все компоненты (репозитории, сервисы, представления) и запускает цикл обработки команд.
 */
public class MainMenu {
    // todo:
    // переделать меню в подпункты с выпадашками, чтобы не было огромного меню

    private final PassengerView passengerView;
    private final FlightView flightView;
    private final TicketView ticketView;
    private final Scanner scanner;

    public MainMenu(PassengerView passengerView, FlightView flightView, TicketView ticketView, Scanner scanner) {
        this.passengerView = passengerView;
        this.flightView = flightView;
        this.ticketView = ticketView;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Репозитории
        PassengerRepository passengerRepository = new PassengerRepositoryImpl();
        FlightRepository flightRepository = new FlightRepositoryImpl();
        TicketRepository ticketRepository = new TicketRepositoryImpl();

        // Сервисы
        PassengerService passengerService = new PassengerServiceImpl(passengerRepository);
        FlightService flightService = new FlightServiceImpl(flightRepository);
        BookingService bookingService = new BookingServiceImpl(ticketRepository, flightRepository);

        // Представления
        PassengerView passengerView = new PassengerView(passengerService, scanner);
        FlightView flightView = new FlightView(flightService, scanner);
        TicketView ticketView = new TicketView(bookingService, passengerService, flightService, scanner);

        // Запуск
        MainMenu menu = new MainMenu(passengerView, flightView, ticketView, scanner);
        menu.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> passengerView.registerPassengerFromInput();
                case "2" -> passengerView.findPassengerByPassportInput();
                case "3" -> passengerView.updatePassengerByPassportInput();
                case "4" -> passengerView.deletePassengerByPassportInput();
                case "5" -> passengerView.displayAllPassengers();
                case "6" -> flightView.registerFlightInput();
                case "7" -> flightView.findAvailableFlightsInput();
                case "8" -> flightView.showAllFlights();
                case "9" -> flightView.updateAvailableSeatsInput();
                case "10" -> flightView.deleteFlightInput();
                case "11" -> ticketView.bookTicketInput();
                case "12" -> ticketView.cancelTicketInput();
                case "13" -> ticketView.showTicketsByPassenger();
                case "14" -> ticketView.showTicketsByStatus();
                case "15" -> ticketView.showAllTickets();
                case "0" -> {
                    System.out.println("Выход из системы...");
                    exit = true;
                }
                default -> System.out.println("Неверный выбор. Повторите попытку.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("=== СИСТЕМА БРОНИРОВАНИЯ БИЛЕТОВ ===");
        System.out.println("1. Зарегистрировать пассажира");
        System.out.println("2. Найти пассажира по паспорту");
        System.out.println("3. Обновить пассажира по номеру паспорта");
        System.out.println("4. Удалить пассажира по номеру паспорта");
        System.out.println("5. Показать список всех пассажиров");
        System.out.println("6. Добавить рейс");
        System.out.println("7. Найти доступные рейсы");
        System.out.println("8. Показать все рейсы");
        System.out.println("9. Обновить количество мест на рейсе");
        System.out.println("10. Удалить рейс");
        System.out.println("11. Оформить билет");
        System.out.println("12. Отменить билет");
        System.out.println("13. Показать билеты пассажира");
        System.out.println("14. Показать билеты по статусу");
        System.out.println("15. Показать все билеты");
        System.out.println("0. Выход");
    }
}