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
import java.util.regex.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    private String getValidName() {
        while (true) {
            String name = getUserInput("Введите имя нового пассажира: ");
            if (name.matches("^[A-Za-zА-Яа-яЁё\\s-]+$")) { // Erlaubt Buchstaben, Leerzeichen und Bindestriche
                return name;
            }
            System.out.println("Ошибка: Некорректное имя. Используйте только буквы и пробелы.");
        }
    }

    private String getValidEmail() {
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"); // Einfache E-Mail-Validierung
        while (true) {
            String email = getUserInput("Введите E-Mail: ");
            if (emailPattern.matcher(email).matches()) {
                return email;
            }
            System.out.println("Ошибка: Некорректный адрес электронной почты. Попробуйте снова.");
        }
    }

    private String getValidPhone() {
        Pattern phonePattern = Pattern.compile("^\\+?[0-9\\s-]{7,15}$"); // Erlaubt Zahlen, Leerzeichen und Bindestriche
        while (true) {
            String phone = getUserInput("Введите номер телефона: ");
            if (phonePattern.matcher(phone).matches()) {
                return phone;
            }
            System.out.println("Ошибка: Некорректный номер телефона. Попробуйте снова.");
        }
    }

    private LocalDate getValidBirthDate() {
        while (true) {
            String birthDateStr = getUserInput("Введите дату рождения (пример: yyyy-MM-dd): ");
            try {
                LocalDate birthDate = LocalDate.parse(birthDateStr, FORMATTER);
                if (birthDate.isAfter(LocalDate.now())) {
                    System.out.println("Ошибка: Дата рождения не может быть в будущем. Попробуйте снова.");
                } else {
                    return birthDate;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: Некорректный формат даты. Попробуйте снова (пример: yyyy-MM-dd).");
            }
        }
    }

    public String registerPassengerFromInput() {
        String name = getValidName();
        String passportNumber = getUserInput("Введите номер паспорта: ");
        LocalDate birthDate = getValidBirthDate();
        String email = getValidEmail();
        String phone = getValidPhone();

        Passenger passenger = new Passenger(name, birthDate, email, phone);

        boolean result = passengerService.registerPassenger(passenger);
        return result
                ? "Пассажир с номером паспорта " + passportNumber + " успешно зарегистрирован."
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

