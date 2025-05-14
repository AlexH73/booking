package com.example.booking;

import com.example.booking.repository.*;
import com.example.booking.service.*;
import com.example.booking.view.*;

import java.util.Scanner;

/**
 * Главный класс приложения.
 *
 * <p>Объединяет все слои: репозитории → сервисы → представления (View).
 * Отображает меню в консоли, в котором пользователь выбирает, с каким модулем он хочет работать:
 * <ul>
 *     <li>Работа с пассажирами</li>
 *     <li>Работа с рейсами</li>
 *     <li>Работа с билетами</li>
 *     <li>Выход</li>
 * </ul>
 *
 * <p>Каждый выбор вызывает соответствующий метод в классе-представлении.
 */
public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Создание репозиториев
        PassengerRepository passengerRepository = new PassengerRepositoryImpl();
        FlightRepository flightRepository = new FlightRepositoryImpl();
        TicketRepository ticketRepository = new TicketRepositoryImpl();

        // 2. Создание сервисов и передача им репозиториев
        PassengerService passengerService = new PassengerServiceImpl(passengerRepository);
        FlightService flightService = new FlightServiceImpl(flightRepository);
        BookingService bookingService = new BookingServiceImpl(ticketRepository, flightRepository, passengerRepository);

        // 3. Создание представлений и передача им сервисов + сканера
        PassengerView passengerView = new PassengerView(passengerService, scanner);
        FlightView flightView = new FlightView(flightService, scanner);
        TicketView ticketView = new TicketView(bookingService, scanner);

        // 4. Основной цикл меню
        while (true) {
            System.out.println("\n=== Система бронирования ===");
            System.out.println("1. Работа с пассажирами");
            System.out.println("2. Работа с рейсами");
            System.out.println("3. Работа с билетами");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    runPassengerMenu(passengerView);
                    break;
                case "2":
                    runFlightMenu(flightView);
                    break;
                case "3":
                    runTicketMenu(ticketView);
                    break;
                case "0":
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Повторите попытку.");
            }
        }
    }

    private static void runPassengerMenu(PassengerView passengerView) {
        System.out.println("\n--- Работа с пассажирами ---");
        System.out.println("1. Зарегистрировать пассажира");
        System.out.println("2. Найти пассажира по паспорту");
        System.out.print("Выберите действие: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                passengerView.registerPassengerFromInput();
                break;
            case "2":
                passengerView.findPassengerByPassportInput();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void runFlightMenu(FlightView flightView) {
        System.out.println("\n--- Работа с рейсами ---");
        System.out.println("1. Найти доступные рейсы");
        System.out.println("2. Обновить количество доступных мест");
        System.out.print("Выберите действие: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                flightView.findAvailableFlightsInput();
                break;
            case "2":
                flightView.updateAvailableSeatsInput();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void runTicketMenu(TicketView ticketView) {
        System.out.println("\n--- Работа с билетами ---");
        System.out.println("1. Забронировать билет");
        System.out.println("2. Отменить билет");
        System.out.println("3. Показать билеты пассажира");
        System.out.print("Выберите действие: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                ticketView.bookTicketInput();
                break;
            case "2":
                ticketView.cancelTicketInput();
                break;
            case "3":
                ticketView.showTicketsByPassengerInput();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }
}