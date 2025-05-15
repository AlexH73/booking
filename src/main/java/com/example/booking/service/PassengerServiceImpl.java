package com.example.booking.service;

import com.example.booking.exceptions.IncorrectPassangerDataException;
import com.example.booking.model.Passenger;
import com.example.booking.repository.PassengerRepository;

import java.util.Collections;
import java.util.List;

/**
 * Реализация интерфейса {@link PassengerService}.
 * <p>
 * Отвечает за регистрацию, поиск, обновление и удаление пассажиров,
 * включая базовую валидацию входных данных.
 */
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    /**
     * Конструктор принимает зависимость — репозиторий пассажиров.
     *
     * @param passengerRepository хранилище пассажиров
     */
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public boolean registerPassenger(Passenger passenger) {
        if (!isValid(passenger)) {
            return false;
        }

        if (passenger == null
                || passenger.getName() == null || passenger.getName().isBlank()
                || passenger.getPassportNumber() == null || passenger.getPassportNumber().isBlank()
                || passenger.getDateOfBirth() == null) {
            return false;
        }

        try {
            Passenger existing = passengerRepository.findByPassport(passenger.getPassportNumber());
            if (existing != null) {
                return false; // Пассажир с таким паспортом уже существует
            }
            return passengerRepository.addPassenger(passenger);
        } catch (IncorrectPassangerDataException e) {
            System.err.println("Ошибка регистрации пассажира: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Passenger findPassengerByPassport(String passportNumber) {
        if (passportNumber == null || passportNumber.isBlank()) {
            return null;
        }
        try {
            return passengerRepository.findByPassport(passportNumber);
        } catch (IncorrectPassangerDataException e) {
            System.err.println("Ошибка поиска пассажира: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deletePassenger(String passportNumber) {
        if (passportNumber == null || passportNumber.isBlank()) {
            return false;
        }
        try {
            return passengerRepository.deleteByPassport(passportNumber);
        } catch (IncorrectPassangerDataException e) {
            System.err.println("Ошибка удаления пассажира: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updatePassenger(Passenger passenger) {
        if (!isValid(passenger)) {
            return false;
        }

        if (passenger == null || passenger.getPassportNumber() == null || passenger.getPassportNumber().isBlank()) {
            return false;
        }
        try {
            return passengerRepository.update(passenger);
        } catch (IncorrectPassangerDataException e) {
            System.err.println("Ошибка обновления пассажира: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Passenger> getAllPassengers() {
        List<Passenger> result = passengerRepository.findAll();
        return result != null ? result : Collections.emptyList();
    }

    public boolean isValid(Passenger passenger) {
        // todo сделать метод валидации
        // todo провести валидацию, что имя - это имя, почта введена корректно,
        //  телефон содержит только цифры (использовать regex),
        //  обработка даты рождения (избежать DateTimeException)
        return true;
    }

}
