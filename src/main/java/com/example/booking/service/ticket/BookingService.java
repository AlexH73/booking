package com.example.booking.service.ticket;

import com.example.booking.model.Flight;
import com.example.booking.model.Passenger;
import com.example.booking.model.Ticket;
import com.example.booking.model.TicketStatus;

import java.util.List;

/**
 * Интерфейс бизнес-логики бронирования билетов.
 *
 * <p>Сервис управляет процессами бронирования, отмены, поиска и фильтрации билетов.
 * Основная цель — проверка условий и передача команд в репозитории.
 *
 * <p>Всё принятие решений о статусах, доступности и корректности должно происходить здесь,
 * а не в репозиториях или слоях представления.
 *
 * <p><b>Сценарии использования:</b>
 * <ul>
 *     <li>Покупка билета — проверка доступности, создание объекта, сохранение.</li>
 *     <li>Отмена билета — поиск, смена статуса, возврат места.</li>
 *     <li>Поиск по пассажиру, по статусу, по ID.</li>
 * </ul>
 */
public interface BookingService {

    /**
     * Бронирует билет для указанного пассажира на заданный рейс.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что входные объекты не {@code null}.</li>
     *     <li>Убедиться, что на рейсе есть свободные места.</li>
     *     <li>Создать объект {@code Ticket}, сохранить его через репозиторий.</li>
     *     <li>Обновить количество мест в рейсе через {@code FlightService}.</li>
     * </ol>
     *
     * @param passenger объект пассажира
     * @param flight объект рейса
     * @return объект билета или {@code null}, если операция невозможна
     */
    Ticket bookTicket(Passenger passenger, Flight flight);

    /**
     * Отменяет билет.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Найти билет по ID через репозиторий.</li>
     *     <li>Убедиться, что он в статусе ACTIVE.</li>
     *     <li>Сменить статус на CANCELLED, сохранить обновление.</li>
     *     <li>Увеличить количество доступных мест в рейсе.</li>
     * </ol>
     *
     * @param ticketId ID билета
     */
    void cancelTicket(String ticketId);

    /**
     * Возвращает все билеты, оформленные на пассажира.
     *
     * @param passenger объект пассажира
     * @return список билетов (возможно пустой)
     */
    List<Ticket> getTicketsByPassenger(Passenger passenger);

    /**
     * Проверяет, есть ли на рейсе свободные места.
     *
     * @param flight объект рейса
     * @return true, если бронирование возможно
     */
    boolean isFlightAvailable(Flight flight);

    /**
     * Возвращает билет по его идентификатору.
     *
     * @param ticketId ID билета
     * @return объект {@code Ticket} или {@code null}, если не найден
     */
    Ticket findTicketById(String ticketId);

    /**
     * Возвращает все билеты в системе.
     *
     * @return список билетов (может быть пустым)
     */
    List<Ticket> getAllTickets();

    /**
     * Находит билеты по их статусу.
     *
     * @param status статус (например, ACTIVE или CANCELLED)
     * @return список билетов с данным статусом
     */
    List<Ticket> findTicketsByStatus(TicketStatus status);
}