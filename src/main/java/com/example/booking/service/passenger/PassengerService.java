package com.example.booking.service.passenger;

import com.example.booking.model.Passenger;

import java.util.List;

/**
 * Интерфейс бизнес-логики работы с пассажирами.
 *
 * <p>Пассажир — это ключевая сущность системы бронирования.
 * Сервис отвечает за регистрацию новых пассажиров, поиск по паспорту и другие операции,
 * связанные с логикой валидации и управления.
 *
 * <p>Реализация методов должна учитывать:
 * <ul>
 *     <li>Проверку корректности входных данных (валидацию).</li>
 *     <li>Проверку уникальности по номеру паспорта (регистрация одного и того же пассажира невозможна).</li>
 *     <li>Выдачу понятных сообщений об ошибках при недопустимых действиях.</li>
 * </ul>
 *
 * <p>Репозиторий — это просто хранилище. Только сервис отвечает за то, <b>как</b> и <b>при каких условиях</b> туда что-то передаётся.
 *
 * <p><b>Дополнительные методы, которые можно добавить:</b>
 * <ul>
 *     <li>{@code void updatePassenger(Passenger passenger)} — чтобы позволить пассажиру изменить имя или дату рождения.</li>
 *     <li>{@code void deletePassenger(String passportNumber)} — например, для удаления ошибочных или неактуальных данных.</li>
 *     <li>{@code List<Passenger> getAllPassengers()} — для отображения всех зарегистрированных клиентов (в админ-интерфейсе).</li>
 * </ul>
 */
public interface PassengerService {

    /**
     * Регистрирует нового пассажира.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что объект {@code passenger} не {@code null} и содержит обязательные данные:
     *         <ul>
     *             <li>Имя</li>
     *             <li>Номер паспорта</li>
     *             <li>Дата рождения</li>
     *         </ul>
     *     </li>
     *     <li>Проверить, что пассажир с таким паспортом ещё не существует в системе.</li>
     *     <li>Если всё корректно — сохранить пассажира через {@code PassengerRepository}.</li>
     *     <li>Если такой пассажир уже есть — выбросить исключение или вернуть ошибку (на усмотрение).</li>
     * </ol>
     *
     * @param passenger объект с данными пассажира
     * @return true, если регистрация прошла успешно, иначе false
     */
    boolean registerPassenger(Passenger passenger);

    /**
     * Ищет пассажира по номеру паспорта.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что {@code passportNumber} не {@code null} и не пустой.</li>
     *     <li>Вызвать метод {@code findByPassport()} у {@code PassengerRepository}.</li>
     *     <li>Если пассажир найден — вернуть его. Если не найден — вернуть {@code null} или выбросить исключение.</li>
     * </ol>
     *
     * @param passportNumber уникальный номер паспорта
     * @return объект {@code Passenger} или {@code null}, если не найден
     */
    Passenger findPassengerByPassport(String passportNumber);

    /**
     * Удаляет пассажира по номеру паспорта.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что {@code passportNumber} не {@code null} и не пустой.</li>
     *     <li>Вызвать {@code deleteByPassport()} у репозитория.</li>
     *     <li>Вернуть true, если удаление успешно, иначе false.</li>
     * </ol>
     *
     * @param passportNumber номер паспорта пассажира
     * @return true, если удаление прошло успешно
     */
    boolean deletePassenger(String passportNumber);

    /**
     * Обновляет данные о пассажире.
     *
     * <p><b>Ожидаемая логика:</b>
     * <ol>
     *     <li>Проверить, что объект {@code passenger} не {@code null} и содержит валидный паспорт.</li>
     *     <li>Убедиться, что такой пассажир существует в системе.</li>
     *     <li>Вызвать метод {@code update()} у {@code PassengerRepository}.</li>
     *     <li>Вернуть true, если обновление успешно.</li>
     * </ol>
     *
     * @param passenger обновлённый объект пассажира
     * @return true, если обновление прошло успешно
     */
    boolean updatePassenger(Passenger passenger);

    /**
     * Возвращает список всех зарегистрированных пассажиров.
     *
     * <p>Может использоваться для отображения всех записей в административном интерфейсе.</p>
     *
     * @return список пассажиров, может быть пустым
     */
    List<Passenger> getAllPassengers();
}