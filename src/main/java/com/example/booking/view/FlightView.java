package com.example.booking.view;

import com.example.booking.model.Flight;
import com.example.booking.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Класс представления для взаимодействия пользователя с данными о рейсах через консоль.
 * <p>
 * Использует {@link FlightService} для выполнения бизнес-операций и {@link Scanner} для чтения ввода пользователя.
 */
public class FlightView {

    private final FlightService flightService;
    private final Scanner scanner;

    public FlightView(FlightService flightService, Scanner scanner) {
        this.flightService = flightService;
        this.scanner = scanner;
    }

    /**
     * Обработка сценария "поиск доступных рейсов".
     * Пользователь вводит города отправления и назначения, после чего отображаются найденные рейсы.
     */
    public void findAvailableFlightsInput() {
        System.out.print("Введите город отправления: ");
        String from = scanner.nextLine();

        System.out.print("Введите город прибытия: ");
        String to = scanner.nextLine();

        List<Flight> flights = flightService.findAvailableFlights(from, to);
        if (flights.isEmpty()) {
            System.out.println("Рейсы не найдены.");
        } else {
            System.out.println("Найденные рейсы:");
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        }
    }

    /**
     * Обработка сценария "обновление количества доступных мест на рейсе".
     * Пользователь вводит номер рейса и новое количество мест.
     */
    public void updateAvailableSeatsInput() {
        System.out.print("Введите номер рейса: ");
        String flightNumber = scanner.nextLine();

        System.out.print("Введите новое количество доступных мест: ");
        int newSeats = Integer.parseInt(scanner.nextLine());

        flightService.updateAvailableSeats(flightNumber, newSeats);
        System.out.println("Места обновлены.");
    }

    /**
     * Обработка сценария "регистрация нового рейса".
     * Пользователь вводит все необходимые параметры.
     */
    public void registerFlightInput() {
        System.out.print("Город отправления: ");
        String from = scanner.nextLine();

        System.out.print("Город прибытия: ");
        String to = scanner.nextLine();

        System.out.print("Дата и время отправления (пример: 2025-12-01T15:30): ");
        String dateStr = scanner.nextLine();
        LocalDateTime departureTime = LocalDateTime.parse(dateStr);

        System.out.print("Продолжительность полёта (в минутах): ");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.print("Общее количество мест: ");
        int total = Integer.parseInt(scanner.nextLine());

        Flight flight = new Flight(to, from, departureTime, duration);
        flight.setTotalSeats(total);
        flight.setAvailableSeats(total);

        boolean result = flightService.registerFlight(flight);
        if (result) {
            System.out.println("Рейс успешно добавлен.");
        } else {
            System.out.println("Не удалось добавить рейс (возможно, он уже существует).");
        }
    }

    /**
     * Обработка сценария "удаление рейса по номеру".
     */
    public void deleteFlightInput() {
        System.out.print("Введите номер рейса для удаления: ");
        String flightNumber = scanner.nextLine();

        boolean deleted = flightService.deleteFlight(flightNumber);
        if (deleted) {
            System.out.println("Рейс успешно удалён.");
        } else {
            System.out.println("Рейс не найден или не удалось удалить.");
        }
    }

    /**
     * Показать все рейсы.
     */
    public void showAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("Нет зарегистрированных рейсов.");
        } else {
            for (Flight f : flights) {
                System.out.println(f);
            }
        }
    }
}
