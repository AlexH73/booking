package com.example.booking.service.flight;

import com.example.booking.model.Flight;

import java.util.List;

/**
 * Интерфейс бизнес-логики для управления рейсами.
 *
 * <p>Отвечает за добавление, удаление, поиск и редактирование рейсов.
 * Все операции содержат проверку валидности данных, чтобы избежать ошибок и дублирования.
 *
 * <p>Реализация сервисов должна:
 * <ul>
 *     <li>Проверять корректность входных данных (непустые строки, положительные числа и т.д.).</li>
 *     <li>Работать с репозиторием только после валидации и логических проверок.</li>
 *     <li>Контролировать наличие рейсов при обновлении и удалении.</li>
 * </ul>
 *
 * <p><b>Дополнительные сценарии использования:</b>
 * <ul>
 *     <li>Добавление рейса при запуске системы или из консоли (админский интерфейс).</li>
 *     <li>Удаление рейса, если он отменён или более не используется.</li>
 *     <li>Обновление количества мест при бронировании или отмене билета.</li>
 *     <li>Поиск по маршруту и отображение всех рейсов для клиента.</li>
 * </ul>
 */
public interface FlightService {

    /**
     * Ищет все доступные рейсы по заданному маршруту.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что оба города не {@code null} и не пустые.</li>
     *     <li>Передать данные в {@code FlightRepository.findAvailableFlights()}.</li>
     *     <li>Вернуть список подходящих рейсов (или пустой список, если ничего не найдено).</li>
     * </ol>
     *
     * @param departureCity город отправления
     * @param arrivalCity город прибытия
     * @return список подходящих рейсов
     */
    List<Flight> findAvailableFlights(String departureCity, String arrivalCity);

    /**
     * Обновляет количество доступных мест на рейсе.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что номер рейса не пустой и мест больше или равно нулю.</li>
     *     <li>Найти рейс через репозиторий. Если рейс не найден — прервать выполнение или выбросить исключение.</li>
     *     <li>Обновить поле {@code availableSeats} и сохранить изменения.</li>
     * </ol>
     *
     * @param flightNumber номер рейса
     * @param newAvailableSeats новое количество мест
     */
    void updateAvailableSeats(String flightNumber, int newAvailableSeats);

    /**
     * Регистрирует новый рейс в системе.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что объект рейса не {@code null} и имеет валидные данные.</li>
     *     <li>Проверить, что такого рейса ещё нет в системе (по номеру рейса).</li>
     *     <li>Добавить рейс в {@code FlightRepository} и вернуть true, если успешно.</li>
     * </ol>
     *
     * @param flight объект нового рейса
     * @return true, если регистрация успешна
     */
    boolean registerFlight(Flight flight);

    /**
     * Удаляет рейс по номеру.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что строка не пуста.</li>
     *     <li>Найти рейс в репозитории и вызвать метод удаления.</li>
     * </ol>
     *
     * @param flightNumber номер рейса
     * @return true, если удаление прошло успешно
     */
    boolean deleteFlight(String flightNumber);

    /**
     * Возвращает рейс по номеру.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что номер рейса валиден.</li>
     *     <li>Вызвать {@code findByFlightNumber} в репозитории.</li>
     *     <li>Вернуть найденный объект или {@code null}.</li>
     * </ol>
     *
     * @param flightNumber строка — номер рейса
     * @return объект {@code Flight} или {@code null}
     */
    Flight findByFlightNumber(String flightNumber);

    /**
     * Возвращает список всех рейсов.
     *
     * @return список рейсов (возможно пустой, но не {@code null})
     */
    List<Flight> getAllFlights();
}