package com.example.booking.view;

import com.example.booking.model.Flight;
import com.example.booking.model.Passenger;
import com.example.booking.model.Ticket;
import com.example.booking.service.BookingService;

import java.util.List;
import java.util.Scanner;

/**
 * Класс, реализующий слой представления (View) для работы с билетами.
 *
 * <p>Задача этого класса — принимать ввод от пользователя, передавать его в {@link BookingService},
 * а затем отображать результат: успешно/неуспешно, информация о билете, ошибки и т.д.
 *
 * <p>Весь ввод выполняется через {@link Scanner}, вывод — через {@code System.out.println()}.
 * Бизнес-логика (проверка доступности мест, сохранение и т.д.) выполняется в {@link BookingService}.
 */
public class TicketView {

    private final BookingService bookingService;
    private final Scanner scanner;

    /**
     * Конструктор получает сервис и сканер.
     *
     * @param bookingService бизнес-логика бронирования
     * @param scanner объект Scanner для чтения пользовательского ввода
     */
    public TicketView(BookingService bookingService, Scanner scanner) {
        this.bookingService = bookingService;
        this.scanner = scanner;
    }

    /**
     * Позволяет пользователю оформить новый билет (бронирование).
     *
     * <p><b>Что реализовать (пошагово):</b>
     * <ol>
     *     <li>Спросить: "Введите имя пассажира:"</li>
     *     <li>Прочитать строку (имя)</li>
     *     <li>Спросить: "Введите номер паспорта пассажира:"</li>
     *     <li>Прочитать паспорт</li>
     *     <li>Спросить: "Введите номер рейса:"</li>
     *     <li>Прочитать номер рейса</li>
     *     <li>Создать объекты {@link Passenger} и {@link Flight} с введёнными данными</li>
     *     <li>Вызвать метод {@code bookingService.bookTicket(passenger, flight)}</li>
     *     <li>Если результат не null — вывести сообщение об успешном бронировании и данные билета</li>
     *     <li>Если null — вывести: "Не удалось забронировать билет (возможно, нет мест)"</li>
     * </ol>
     */
    public void bookTicketInput() {
        // реализация пишется по шагам выше
    }

    /**
     * Позволяет отменить ранее забронированный билет.
     *
     * <p><b>Что реализовать (пошагово):</b>
     * <ol>
     *     <li>Спросить: "Введите ID билета для отмены:"</li>
     *     <li>Прочитать строку (ticketId)</li>
     *     <li>Вызвать метод {@code bookingService.cancelTicket(ticketId)}</li>
     *     <li>Вывести: "Билет успешно отменён" — если метод не выбросил ошибку</li>
     *     <li>Или: "Ошибка: билет не найден или уже отменён" — если результат неудовлетворительный</li>
     * </ol>
     */
    public void cancelTicketInput() {
        // реализация пишется по шагам выше
    }

    /**
     * Показывает все билеты конкретного пассажира.
     *
     * <p><b>Что реализовать (пошагово):</b>
     * <ol>
     *     <li>Спросить: "Введите номер паспорта пассажира:"</li>
     *     <li>Прочитать строку (паспорт)</li>
     *     <li>Создать объект {@link Passenger} с этим паспортом (имя и дата рождения можно оставить пустыми)</li>
     *     <li>Вызвать метод {@code bookingService.getTicketsByPassenger(passenger)}</li>
     *     <li>Если список пуст — вывести: "У пассажира нет билетов."</li>
     *     <li>Если есть билеты — вывести каждый билет:
     *         <ul>
     *             <li>ID билета</li>
     *             <li>Номер рейса</li>
     *             <li>Статус (АКТИВЕН или ОТМЕНЁН)</li>
     *         </ul>
     *     </li>
     * </ol>
     */
    public void showTicketsByPassengerInput() {
        // реализация пишется по шагам выше
    }
}