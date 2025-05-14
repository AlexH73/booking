package com.example.booking.service;

import com.example.booking.model.Flight;
import com.example.booking.model.Passenger;
import com.example.booking.model.Ticket;
import com.example.booking.repository.TicketRepository;
import com.example.booking.repository.FlightRepository;
import com.example.booking.repository.PassengerRepository;

import java.util.List;

/**
 * Реализация сервиса {@link BookingService}.
 *
 * <p>Класс отвечает за основную бизнес-логику бронирования билетов: создание, отмена,
 * получение списка билетов пассажира.
 *
 * <p>Работает с данными через репозитории: {@link TicketRepository}, {@link FlightRepository}, {@link PassengerRepository}.
 * Он сам ничего не хранит — только вызывает методы нужных репозиториев, принимает решения, проверяет условия.
 *
 * <p>Подробности реализации описаны внутри каждого метода.
 */
public class BookingServiceImpl implements BookingService {

    // Репозитории, через которые происходит доступ к данным
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    /**
     * Конструктор BookingServiceImpl.
     *
     * <p>Через параметры конструктора должны передаваться все необходимые зависимости (репозитории).
     * Это называется принципом инъекции зависимостей (Dependency Injection).
     * Такой подход позволяет:
     * <ul>
     *     <li>Изолировать бизнес-логику от конкретных реализаций хранения данных.</li>
     *     <li>Упрощать тестирование — можно передать фейковые репозитории.</li>
     * </ul>
     *
     * @param ticketRepository для операций с билетами
     * @param flightRepository для операций с рейсами
     * @param passengerRepository для операций с пассажирами
     */
    public BookingServiceImpl(TicketRepository ticketRepository,
                              FlightRepository flightRepository,
                              PassengerRepository passengerRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    /**
     * Бронирует билет на рейс для указанного пассажира.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Проверить, есть ли свободные места в объекте {@code Flight}.</li>
     *     <li>Если мест нет — вернуть {@code null} или выбросить исключение.</li>
     *     <li>Создать новый объект {@code Ticket}:
     *         <ul>
     *             <li>Генерировать уникальный ID билета (можно с помощью UUID или генератора).</li>
     *             <li>Установить статус "АКТИВЕН", ссылку на пассажира и рейс.</li>
     *         </ul>
     *     </li>
     *     <li>Сохранить билет через {@code ticketRepository.save()}.</li>
     *     <li>Уменьшить количество доступных мест в рейсе и обновить его через {@code flightRepository}.</li>
     *     <li>Вернуть созданный билет.</li>
     * </ol>
     *
     * @param passenger пассажир, которому бронируется билет
     * @param flight рейс, на который бронируется билет
     * @return новый билет, если бронь прошла успешно, иначе null
     */
    @Override
    public Ticket bookTicket(Passenger passenger, Flight flight) {
        return null; // реализацию студент должен написать сам
    }

    /**
     * Отменяет билет по его идентификатору.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Найти билет по ID через {@code ticketRepository.findById()}.</li>
     *     <li>Если билет не найден — можно ничего не делать или выбросить исключение.</li>
     *     <li>Если найден и статус "АКТИВЕН":
     *         <ul>
     *             <li>Установить статус "ОТМЕНЕН".</li>
     *             <li>Сохранить обновлённый билет.</li>
     *             <li>Найти связанный рейс и увеличить количество доступных мест на 1.</li>
     *             <li>Сохранить обновлённый рейс.</li>
     *         </ul>
     *     </li>
     * </ol>
     *
     * @param ticketId уникальный идентификатор билета
     */
    @Override
    public void cancelTicket(String ticketId) {
        // реализацию студент должен написать сам
    }

    /**
     * Возвращает список всех билетов конкретного пассажира.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Передать объект {@code Passenger} в метод {@code ticketRepository.findByPassenger()}.</li>
     *     <li>Вернуть результат без изменений.</li>
     * </ol>
     *
     * <p>Метод может использоваться для отображения истории билетов, фильтрации активных, и т.д.
     *
     * @param passenger пассажир, чьи билеты нужно получить
     * @return список билетов, может быть пустым
     */
    @Override
    public List<Ticket> getTicketsByPassenger(Passenger passenger) {
        return null; // реализацию студент должен написать сам
    }
}