package com.example.booking.view;

import com.example.booking.model.Flight;
import com.example.booking.service.FlightService;

import java.util.List;
import java.util.Scanner;

/**
 * Класс, реализующий слой представления (View) для работы с рейсами.
 *
 * <p>Этот класс:
 * <ul>
 *     <li>Взаимодействует с пользователем через консоль (ввод/вывод)</li>
 *     <li>Передаёт запросы в {@link FlightService}</li>
 *     <li>Отображает результаты или сообщения об ошибках</li>
 * </ul>
 *
 * <p>Он не содержит бизнес-логики — только взаимодействие с пользователем.
 * Все проверки и действия происходят в сервисе.
 */
public class FlightView {

    private final FlightService flightService;
    private final Scanner scanner;

    /**
     * Конструктор получает зависимости — сервис и сканер.
     *
     * @param flightService сервис, отвечающий за бизнес-логику рейсов
     * @param scanner объект {@link Scanner} для чтения ввода пользователя
     */
    public FlightView(FlightService flightService, Scanner scanner) {
        this.flightService = flightService;
        this.scanner = scanner;
    }

    /**
     * Метод позволяет пользователю найти рейсы между двумя городами.
     *
     * <p><b>Что реализовать (пошагово):</b>
     * <ol>
     *     <li>Вывести: "Введите город отправления:"</li>
     *     <li>Прочитать строку через {@code scanner.nextLine()}</li>
     *     <li>Вывести: "Введите город прибытия:"</li>
     *     <li>Прочитать строку</li>
     *     <li>Вызвать метод {@code flightService.findAvailableFlights(departure, arrival)}</li>
     *     <li>Если список пуст — вывести: "Доступных рейсов не найдено."</li>
     *     <li>Если список не пуст — для каждого рейса вывести:
     *         <ul>
     *             <li>Номер рейса</li>
     *             <li>Города</li>
     *             <li>Дата и время</li>
     *             <li>Количество доступных мест</li>
     *         </ul>
     *     </li>
     * </ol>
     */
    public void findAvailableFlightsInput() {
        // реализация пишется по инструкции выше
    }

    /**
     * Метод позволяет администратору обновить количество доступных мест на рейсе.
     *
     * <p><b>Что реализовать (пошагово):</b>
     * <ol>
     *     <li>Вывести: "Введите номер рейса:"</li>
     *     <li>Прочитать строку (номер рейса)</li>
     *     <li>Вывести: "Введите новое количество доступных мест:"</li>
     *     <li>Прочитать строку и преобразовать её в число ({@code Integer.parseInt})</li>
     *     <li>Вызвать {@code flightService.updateAvailableSeats(flightNumber, newSeats)}</li>
     *     <li>Вывести: "Количество мест успешно обновлено." (если всё прошло без ошибок)</li>
     *     <li>Если возникла ошибка (например, отрицательное число или рейс не найден) — вывести сообщение об ошибке</li>
     * </ol>
     */
    public void updateAvailableSeatsInput() {
        // реализация пишется по инструкции выше
    }
}