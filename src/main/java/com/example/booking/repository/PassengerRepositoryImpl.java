package com.example.booking.repository;

import com.example.booking.exceptions.IncorrectPassengerDataException;
import com.example.booking.model.Passenger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Реализация интерфейса {@link PassengerRepository}.
 *
 * <p>Этот класс управляет хранилищем пассажиров. Для хранения используется коллекция:
 * {@code Map<String, Passenger>} — где ключом является {@code passportNumber}, а значением — объект {@code Passenger}.
 *
 * <p>Методы реализуются вручную, без подключения к базе данных.
 * Все операции происходят внутри обычной коллекции Java.
 */
public class PassengerRepositoryImpl implements PassengerRepository {

    // Внутреннее хранилище пассажиров (паспорт -> объект Passenger)
    private final Map<String, Passenger> storage = new HashMap<>();

    /**
     * Находит пассажира по номеру паспорта.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Проверить, что {@code passportNumber} не null и не пустой.</li>
     *     <li>В хранилище {@code storage} вызвать метод {@code get(passportNumber)}.</li>
     *     <li>Если пассажир найден — вернуть его. Если не найден — вернуть {@code null}.</li>
     * </ol>
     *
     * @param passportNumber номер паспорта
     * @return объект {@code Passenger} или {@code null}, если не найден
     */
    @Override
    public Passenger findByPassport(String passportNumber) throws IncorrectPassengerDataException {
        if (passportNumber == null || passportNumber.isBlank()) {
            throw new IncorrectPassengerDataException("Ошибка! Номер паспорта не может быть пустым.");
        }
        return storage.get(passportNumber);
    }

    /**
     * Сохраняет нового пассажира в хранилище.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Убедиться, что объект {@code passenger} не равен {@code null}.</li>
     *     <li>Получить у него значение {@code passportNumber} (уникальный ключ).</li>
     *     <li>Если поле паспорта не пустое — положить объект в хранилище через {@code put()}.</li>
     *     <li>Если пассажир с таким паспортом уже есть — он будет заменён (это допустимо).</li>
     * </ol>
     *
     * @param passenger объект, который нужно сохранить
     */
    @Override
    public boolean addPassenger(Passenger passenger) throws IncorrectPassengerDataException {
        if (passenger == null) {
            throw new IncorrectPassengerDataException("Ошибка! Объект passenger не может быть равен null");
        }
        storage.put(passenger.getPassportNumber(), passenger);
        return findByPassport(passenger.getPassportNumber()) != null;
    }

    /**
     * Обновляет данные существующего пассажира.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Проверить, что {@code passenger} и его паспорт не {@code null}.</li>
     *     <li>Проверить, есть ли в хранилище запись с таким паспортом.</li>
     *     <li>Если запись есть — заменить старую новой (через {@code put()}).</li>
     *     <li>Если нет — можно ничего не делать или вывести сообщение (на выбор).</li>
     * </ol>
     *
     * @param passenger обновлённый объект {@code Passenger}
     */
    @Override
    public boolean update(Passenger passenger) throws IncorrectPassengerDataException {
        if (passenger == null) {
            throw new IncorrectPassengerDataException("Ошибка! Объект passenger не может быть равен null");
        }
        if (findByPassport(passenger.getPassportNumber()) == null) {
            return false;
        }
        storage.put(passenger.getPassportNumber(), passenger);

        Passenger passengerFromStorage = storage.get(passenger.getPassportNumber());

        return passenger.equals(passengerFromStorage);
    }

    /**
     * Удаляет пассажира из хранилища по номеру паспорта.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Проверить, что параметр {@code passportNumber} не {@code null}.</li>
     *     <li>Вызвать метод {@code remove(passportNumber)} у хранилища.</li>
     *     <li>Если пассажира с таким паспортом нет — ничего страшного, это допустимо.</li>
     * </ol>
     *
     * @param passportNumber номер паспорта удаляемого пассажира
     */
    @Override
    public boolean deleteByPassport(String passportNumber) throws IncorrectPassengerDataException {
        if (passportNumber == null || passportNumber.isBlank()) {
            throw new IncorrectPassengerDataException("Ошибка! Номер паспорта не может быть пустым.");
        }
        storage.remove(passportNumber);

        return true;
    }

    /**
     * Возвращает список всех пассажиров.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Получить все значения из {@code storage} с помощью {@code values()}.</li>
     *     <li>Создать новый {@code ArrayList} и положить туда все значения.</li>
     *     <li>Вернуть этот список.</li>
     * </ol>
     *
     * <p>Даже если в хранилище нет ни одного пассажира — метод должен вернуть пустой список, а не {@code null}.
     *
     * @return список объектов {@code Passenger}
     */
    @Override
    public List<Passenger> findAll() {
        return new ArrayList<>(storage.values());
    }
}