package com.example.booking.view;

import com.example.booking.model.Passenger;
import com.example.booking.service.PassengerService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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
public class PassengerView {

    private final PassengerService passengerService;
    private final Scanner scanner;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Конструктор получает сервис и сканер в качестве зависимостей.
     *
     * <p>Такой подход позволяет легко тестировать и переиспользовать класс.
     *
     * @param passengerService сервис, реализующий бизнес-логику пассажиров
     * @param scanner объект для чтения ввода из консоли
     */
    public PassengerView(PassengerService passengerService, Scanner scanner) {
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    public Passenger creatеPassenger() {

        System.out.println("Введите имя нового пассажира: ");
        String name = scanner.nextLine();

        LocalDate birthDate = null;
        while (birthDate == null) {
            try {
                System.out.println("Введите дату рождения (пример: yyyy-MM-dd): ");
                String bDate = scanner.nextLine();
                birthDate = LocalDate.parse(bDate, FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка формата даты! Используйте yyyy-MM-dd.");
            }
        }

        System.out.println("Введите E-Mail: ");
        String email = scanner.nextLine();

        System.out.println("Введите номер телефона:");
        String phone = scanner.nextLine();


        Passenger passenger = new Passenger(name, birthDate, email, phone);

        return passenger;
    }

    /**
     * Метод для регистрации нового пассажира.
     *
     * <p><b>Что сделать (пошагово):</b>
     * <ol>
     *     <li>Вывести в консоль сообщение: "Введите имя пассажира:"</li>
     *     <li>Прочитать имя через {@code scanner.nextLine()}</li>
     *     <li>Вывести: "Введите номер паспорта:"</li>
     *     <li>Прочитать номер паспорта</li>
     *     <li>Вывести: "Введите дату рождения (в формате ГГГГ-ММ-ДД):"</li>
     *     <li>Прочитать строку, преобразовать её в {@link LocalDate} через {@code LocalDate.parse()}</li>
     *     <li>Создать объект {@link Passenger} с этими данными</li>
     *     <li>Вызвать метод {@code passengerService.registerPassenger(passenger)}</li>
     *     <li>Вывести сообщение об успехе или ошибке (если бросилось исключение)</li>
     * </ol>
     */
    public void registerPassengerFromInput() {
        Passenger passenger = creatеPassenger();
        boolean result = passengerService.registerPassenger(passenger);
        if (result) {
            System.out.println("Пассажир с номером паспорта " + passenger.getPassportNumber() + " успешно зарегистрирован.");
        } else {
            System.out.println("Не удалось добавить пассажира.");
        }
    }

    /**
     * Метод для поиска пассажира по номеру паспорта.
     *
     * <p><b>Что сделать (пошагово):</b>
     * <ol>
     *     <li>Вывести в консоль: "Введите номер паспорта для поиска:"</li>
     *     <li>Прочитать номер паспорта с помощью {@code scanner.nextLine()}</li>
     *     <li>Передать его в {@code passengerService.findPassengerByPassport()}</li>
     *     <li>Если результат — не null:
     *         <ul>
     *             <li>Вывести: имя, номер паспорта и дату рождения</li>
     *         </ul>
     *     </li>
     *     <li>Если результат — null:
     *         <ul>
     *             <li>Вывести сообщение: "Пассажир не найден."</li>
     *         </ul>
     *     </li>
     * </ol>
     */
    public void findPassengerByPassportInput() {
        System.out.println("Введите номер паспорта: ");
        String passNum = scanner.nextLine();

        Passenger passenger = passengerService.findPassengerByPassport(passNum);
        if (passenger != null) {
            System.out.printf(" Пассажир: %s " +
                    "Номер паспорта: %s " +
                    "Дата рождения: %s %n",
                    passenger.getName(),
                    passenger.getPassportNumber(),
                    passenger.getDateOfBirth()
            );
        } else {
            System.out.println("Пассажир не найден!");
        }
    }

    public void deletePassengerByPassportInput() {
        System.out.println("Введите номер паспорта: ");
        String passNum = scanner.nextLine();

        boolean dellPassenger = passengerService.deletePassenger(passNum);

        if (dellPassenger) {
            System.out.println("Пассажир успешно удален.");
        }
    }

    public void updatePassengerByPassportInput() {
        System.out.println("Введите номер паспорта: ");
        String passNum = scanner.nextLine();

        Passenger passenger = creatеPassenger();
        passenger.setPassportNumber(passNum);
        boolean updatePassenger = passengerService.updatePassenger(passenger);

        if (updatePassenger) {
            System.out.println("Пассажир успешно обновлен: ");
        }
    }
    public void displayAllPassengers() {
        List<Passenger> passengers = passengerService.getAllPassengers();
        if (passengers.isEmpty()) {
            System.out.println("Список пассажиров пуст.");
            return;
        }
        System.out.println("Список всех пассажиров:");
        passengers.forEach(p -> System.out.printf(
                "Имя: %s | Паспорт: %s | Дата рождения: %s%n",
                p.getName(), p.getPassportNumber(), p.getDateOfBirth()
        ));
    }
}