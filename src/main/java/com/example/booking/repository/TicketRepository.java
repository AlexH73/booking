package com.example.booking.repository;

import com.example.booking.model.Ticket;
import com.example.booking.model.Passenger;
import com.example.booking.model.TicketStatus;

import java.util.List;

/**
 * Интерфейс для работы с билетами.
 *
 * На текущий момент содержит методы для:
 * - поиска билета по идентификатору
 * - поиска всех билетов пассажира
 * - сохранения нового билета
 * - удаления билета
 *
 * Студентам необходимо **дополнить интерфейс другими методами**, чтобы реализовать расширенные возможности:
 *
 * 1. {@code update(Ticket ticket)} — для изменения статуса билета (например, отмена или повторная активация).
 * 2. {@code findAll()} — для получения списка всех билетов, что может быть полезно для административных целей.
 * 3. {@code findByStatus(String status)} — для получения списка активных или отмененных билетов.
 *
 * Такие методы помогают реализовать более гибкую бизнес-логику и покрыть все возможные операции над сущностью Ticket.
 */
public interface TicketRepository {

    /**
     * Находит билет по его уникальному идентификатору.
     *
     * @param ticketId уникальный ID билета
     * @return объект Ticket, если найден, иначе null
     */
    String findById(String ticketId);

    /**
     * Находит список билетов, принадлежащих указанному пассажиру.
     *
     * @param passenger объект Passenger, чьи билеты нужно найти
     * @return список билетов, может быть пустым
     */
    List<Ticket> findByPassenger(Passenger passenger);

    /**
     * Возвращает список всех билетов в системе.
     *
     * @return список билетов, может быть пустым
     */
    List<Ticket> findAll();

    /**
     * Находит билеты по заданному статусу (например, "ACTIVE" или "CANCELLED").
     *
     * @param status статус билетов, которые нужно найти
     * @return список билетов с указанным статусом
     */
    List<Ticket> findByStatus(TicketStatus status);
    /**
     * Сохраняет новый билет в систему.
     * Обычно используется при бронировании нового билета.
     *
     * @param ticket объект Ticket, который нужно сохранить
     */
    boolean addTicket(Ticket ticket);

    /**
     * Удаляет билет по его идентификатору.
     * Может использоваться, если билет был ошибочно оформлен.
     *
     * @param ticketId уникальный ID билета, который необходимо удалить
     */
    boolean delete(String ticketId);
    /**
     * Обновляет билет, например, изменяет его статус.
            *
            * @param ticket объект Ticket с обновленными данными
 * @return true, если обновление прошло успешно, иначе false
     **/
    boolean update(Ticket ticket);
}