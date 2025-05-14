package com.example.booking.service;

import com.example.booking.model.Passenger;
import com.example.booking.repository.PassengerRepository;

/**
 * Реализация бизнес-логики работы с пассажирами.
 *
 * <p>Отвечает за:
 * <ul>
 *     <li>Проверку корректности входных данных (валидация)</li>
 *     <li>Проверку уникальности пассажира по паспорту</li>
 *     <li>Создание и сохранение объекта в хранилище</li>
 * </ul>
 *
 * <p><b>Важно:</b> Вся логика обработки данных должна быть здесь.
 * Репозиторий должен использоваться только для хранения и извлечения данных, без принятия решений.
 */
public class PassengerServiceImpl implements PassengerService {

    // Репозиторий, через который происходит работа с данными пассажиров
    private final PassengerRepository passengerRepository;

    /**
     * Конструктор с внедрением зависимости.
     *
     * <p>При создании объекта сервис должен получить экземпляр {@code PassengerRepository}.
     * Это позволяет:
     * <ul>
     *     <li>Изолировать сервис от конкретной реализации хранилища</li>
     *     <li>Легко писать тесты (можно подменить репозиторий на заглушку)</li>
     * </ul>
     *
     * @param passengerRepository репозиторий для доступа к данным пассажиров
     */
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    /**
     * Регистрирует нового пассажира.
     *
     * <p><b>Шаги реализации:</b>
     * <ol>
     *     <li>Проверить, что объект {@code passenger} не {@code null}.</li>
     *     <li>Проверить, что у него заполнены ключевые поля:
     *         <ul>
     *             <li>{@code name} — имя</li>
     *             <li>{@code passport} — номер паспорта</li>
     *             <li>{@code dateOfBirth} — дата рождения</li>
     *         </ul>
     *         Если что-то не указано — выбросить исключение или прервать выполнение.
     *     </li>
     *     <li>Проверить, существует ли уже пассажир с таким паспортом:
     *         <ul>
     *             <li>Вызвать {@code passengerRepository.findByPassport(passport)}</li>
     *             <li>Если такой пассажир уже есть — регистрация невозможна, вернуть ошибку или прервать выполнение</li>
     *         </ul>
     *     </li>
     *     <li>Если всё в порядке — вызвать {@code passengerRepository.save(passenger)}</li>
     * </ol>
     *
     * <p>Вся логика должна быть реализована в этом методе. Репозиторий просто сохраняет объект без проверок.
     *
     * @param passenger объект нового пассажира
     */
    @Override
    public void registerPassenger(Passenger passenger) {
        // реализация пишется по шагам выше
    }

    /**
     * Находит пассажира по номеру паспорта.
     *
     * <p><b>Шаги реализации:</b>
     * <ol>
     *     <li>Проверить, что {@code passportNumber} не {@code null} и не пустой.</li>
     *     <li>Вызвать метод {@code passengerRepository.findByPassport(passportNumber)}.</li>
     *     <li>Вернуть результат:
     *         <ul>
     *             <li>{@code Passenger} — если найден</li>
     *             <li>{@code null} — если не найден</li>
     *         </ul>
     *     </li>
     * </ol>
     *
     * <p>Метод не должен бросать исключения, если пассажир не найден — это нормальная ситуация.
     *
     * @param passportNumber номер паспорта пассажира
     * @return объект Passenger или null
     */
    @Override
    public Passenger findPassengerByPassport(String passportNumber) {
        return null; // реализацию студент должен написать сам
    }
}