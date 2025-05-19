package com.example.booking.service.passenger;

import com.example.booking.exceptions.IncorrectPassengerDataException;
import com.example.booking.model.Passenger;
import com.example.booking.repository.passenger.PassengerRepository;
import com.example.booking.service.validation.ValidationUtils;
import com.example.booking.views.console.ConsoleColor;


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
        if (!isValid(passenger) || passenger.getPassportNumber() == null || passenger.getPassportNumber().isBlank()) {
            return false;
        }

        try {
            Passenger existing = passengerRepository.findByPassport(passenger.getPassportNumber());
            if (existing != null) {
                return false;
            }
            return passengerRepository.addPassenger(passenger);
        } catch (IncorrectPassengerDataException e) {
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
        } catch (IncorrectPassengerDataException e) {
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
        } catch (IncorrectPassengerDataException e) {
            System.out.println(ConsoleColor.RED.apply("Ошибка удаления пассажира: " + e.getMessage()));
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
        } catch (IncorrectPassengerDataException e) {
            System.out.println(ConsoleColor.RED.apply("Ошибка обновления пассажира: " + e.getMessage()));
            return false;
        }
    }

    @Override
    public List<Passenger> getAllPassengers() {
        List<Passenger> result = passengerRepository.findAll();
        return result != null ? result : Collections.emptyList();
    }

    public boolean isValid(Passenger passenger) {
        return passenger != null &&
                ValidationUtils.isValidName(passenger.getName()) &&
                ValidationUtils.isValidEmail(passenger.getEmail()) &&
                ValidationUtils.isValidPhone(passenger.getPhoneNumber()) &&
                ValidationUtils.isValidBirthDate(passenger.getDateOfBirth()) &&
                passenger.getPassportNumber() != null &&
                !passenger.getPassportNumber().isBlank();
    }
}
