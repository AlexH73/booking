package com.example.booking.view;

import com.example.booking.model.Passenger;
import com.example.booking.service.PassengerService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Класс, реализующий слой представления (View) для работы с пассажирами.
 *
 * <p>Слой представления отвечает за:
 * <ul>
 *     <li>Ввод данных с клавиатуры (через {@link Scanner})</li>
 *     <li>Вывод сообщений в консоль (через {@code System.out.println})</li>
 *     <li>Обращение к сервисам, передача им данных</li>
 *     <li>Обратную связь пользователю: успешное выполнение, ошибки, найденные или отсутствующие данные</li>
 * </ul>
 *
 * <p>Этот класс напрямую работает с пользователем, но <b>не реализует бизнес-логику</b> и <b>не хранит данные</b>.
 * Все операции передаются в {@link PassengerService}, который обрабатывает их по правилам.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PassengerView {

    private final PassengerService passengerService;
    private final Scanner scanner;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public PassengerView(PassengerService passengerService, Scanner scanner) {
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    private String getUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
    public LocalDate getValidBirthDate() {
        while (true) {
            String birthDateStr = getUserInput("Введите дату рождения (пример: yyyy-MM-dd): ");
            try {
                LocalDate birthDate = LocalDate.parse(birthDateStr, FORMATTER);
                if (birthDate.isAfter(LocalDate.now())) {
                    System.out.println("Ошибка: Дата рождения не может быть в будущем. Попробуйте снова.");
                } else {
                    return birthDate; // Korrekte Eingabe
                }
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: Некорректный формат даты. Попробуйте снова (пример: yyyy-MM-dd).");
            }
        }
    }


    public String registerPassengerFromInput() {
        String name = getUserInput("Введите имя нового пассажира: ");
        String passportNumber = getUserInput("Введите номер паспорта: ");
        LocalDate birthDate = getValidBirthDate(); // Verwendet die neue Methode

        String email = getUserInput("Введите E-Mail: ");
        String phone = getUserInput("Введите номер телефона: ");

        Passenger passenger = new Passenger(name, birthDate, email, phone);

        boolean result = passengerService.registerPassenger(passenger);
        return result
                ? "Пассажир с номером паспорта " + passenger.getPassportNumber() + " успешно зарегистрирован."
                : "Ошибка: Не удалось добавить пассажира.";
    }

    public String findPassengerByPassportInput() {
        String passNum = getUserInput("Введите номер паспорта: ");
        Passenger passenger = passengerService.findPassengerByPassport(passNum);

        return passenger != null
                ? "Пассажир: " + passenger.getName() +
                "\nНомер паспорта: " + passenger.getPassportNumber() +
                "\nДата рождения: " + passenger.getDateOfBirth()
                : "Ошибка: Пассажир не найден.";
    }
}
