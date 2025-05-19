package com.example.booking.repository.passenger;

import com.example.booking.exceptions.IncorrectPassengerDataException;
import com.example.booking.model.Passenger;

import java.util.List;

/**
 * Интерфейс для работы с пассажирами.
 *
 * На текущий момент содержит два метода:
 * - поиск пассажира по номеру паспорта
 * - сохранение нового пассажира
 *
 * Студентам необходимо **дополнить интерфейс другими методами**, чтобы реализовать базовые CRUD-операции:
 *
 * 1. {@code update(Passenger passenger)} — для обновления данных существующего пассажира, например, если нужно изменить имя или дату рождения.
 * 2. {@code deleteByPassport(String passportNumber)} — для удаления пассажира из системы (например, по запросу или ошибочной записи).
 * 3. {@code findAll()} — для получения списка всех пассажиров, полезно для административных целей.
 *
 * Такие методы формируют стандартный интерфейс репозитория и используются во всех многослойных приложениях.
 */
public interface PassengerRepository {

    /**
     * Находит пассажира по номеру паспорта.
     *
     * @param passportNumber номер паспорта
     * @return объект Passenger, если найден, или null
     */
    Passenger findByPassport(String passportNumber) throws IncorrectPassengerDataException;

    /**
     * Сохраняет нового пассажира в систему.
     * Обычно вызывается при первичной регистрации.
     *
     * @param passenger объект Passenger, который нужно сохранить
     */
    boolean addPassenger(Passenger passenger) throws IncorrectPassengerDataException;

    List<Passenger> findAll();

    boolean deleteByPassport(String passportNumber) throws IncorrectPassengerDataException;

    boolean update(Passenger passenger) throws IncorrectPassengerDataException;

}
