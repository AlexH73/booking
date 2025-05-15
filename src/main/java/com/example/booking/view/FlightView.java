package com.example.booking.view;

import com.example.booking.model.Flight;
import com.example.booking.service.FlightService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;


/**
 * Класс представления для взаимодействия пользователя с данными о рейсах через консоль.
 * <p>
 * Использует {@link FlightService} для выполнения бизнес-операций и {@link Scanner} для чтения ввода пользователя.
 */




public class FlightView {

    private final FlightService flightService;
    private final Scanner scanner;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");

    public FlightView(FlightService flightService, Scanner scanner) {
        this.flightService = flightService;
        this.scanner = scanner;
    }

    private String getUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public String findAvailableFlightsInput() {
        String from = getUserInput("Введите город отправления: ");
        String to = getUserInput("Введите город прибытия: ");

        List<Flight> flights = flightService.findAvailableFlights(from, to);
        return flights.isEmpty() ? "Рейсы не найдены."
                : "Найденные рейсы:\n" + flights.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    public String updateAvailableSeatsInput() {
        String flightNumber = getUserInput("Введите номер рейса: ");

        try {
            int newSeats = Integer.parseInt(getUserInput("Введите новое количество доступных мест: "));
            if (newSeats < 0) {
                return "Ошибка: Количество мест не может быть отрицательным.";
            }
            flightService.updateAvailableSeats(flightNumber, newSeats);
            return "Места обновлены.";
        } catch (NumberFormatException e) {
            return "Ошибка: Введите корректное число.";
        }
    }

    public String registerFlightInput() {
        String from = getUserInput("Город отправления: ");
        String to = getUserInput("Город прибытия: ");
        String dateStr = getUserInput("Дата и время отправления (пример: 2025-12-01_15:30): ");

        try {
            LocalDateTime departureTime = LocalDateTime.parse(dateStr, FORMATTER);
            if (departureTime.isBefore(LocalDateTime.now())) {
                return "Ошибка: Дата отправления не может быть в прошлом.";
            }

            int duration = Integer.parseInt(getUserInput("Продолжительность полёта (в минутах): "));
            int total = Integer.parseInt(getUserInput("Общее количество мест: "));

            if (duration <= 0 || total <= 0) {
                return "Ошибка: Продолжительность и количество мест должны быть положительными.";
            }

            Flight flight = new Flight(to, from, departureTime, duration);
            flight.setTotalSeats(total);
            flight.setAvailableSeats(total);

            boolean result = flightService.registerFlight(flight);
            return result ? "Рейс успешно добавлен." : "Не удалось добавить рейс (возможно, он уже существует).";

        } catch (NumberFormatException | DateTimeParseException e) {
            return "Ошибка: Некорректный формат данных.";
        }
    }

    public String deleteFlightInput() {
        String flightNumber = getUserInput("Введите номер рейса для удаления: ");
        boolean deleted = flightService.deleteFlight(flightNumber);
        return deleted ? "Рейс успешно удалён." : "Рейс не найден или не удалось удалить.";
    }

    public String showAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return flights.isEmpty() ? "Нет зарегистрированных рейсов."
                : "Список рейсов:\n" + flights.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }
}
