package com.example.booking.service.flight;

import com.example.booking.model.Flight;
import com.example.booking.repository.flight.FlightRepository;

import java.util.Collections;
import java.util.List;

/**
 * Реализация интерфейса {@link FlightService}.
 * <p>
 * Отвечает за бизнес-логику, связанную с рейсами: регистрацию, удаление,
 * поиск по маршруту и обновление количества мест.
 */
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    /**
     * Конструктор принимает зависимость — репозиторий рейсов.
     *
     * @param flightRepository репозиторий рейсов
     */
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> findAvailableFlights(String departureCity, String arrivalCity) {
        if (departureCity == null || arrivalCity == null
                || departureCity.isBlank() || arrivalCity.isBlank()) {
            return Collections.emptyList();
        }
        return flightRepository.findAvailableFlights(departureCity, arrivalCity);
    }

    @Override
    public void updateAvailableSeats(String flightNumber, int newAvailableSeats) {
        if (flightNumber == null || flightNumber.isBlank() || newAvailableSeats < 0) {
            return;
        }
        flightRepository.updateAvailableSeats(flightNumber, newAvailableSeats);
    }

    @Override
    public boolean registerFlight(Flight flight) {
        if (flight == null || flight.getFlightNumber() == null || flight.getFlightNumber().isBlank()) {
            return false;
        }
        Flight existing = flightRepository.findByFlightNumber(flight.getFlightNumber());
        if (existing != null) {
            return false; // рейс с таким номером уже есть
        }
        return flightRepository.addFlight(flight);
    }

    @Override
    public boolean deleteFlight(String flightNumber) {
        if (flightNumber == null || flightNumber.isBlank()) {
            return false;
        }
        Flight existing = flightRepository.findByFlightNumber(flightNumber);
        if (existing == null) {
            return false;
        }
        return flightRepository.deleteFlight(existing);
    }

    @Override
    public Flight findByFlightNumber(String flightNumber) {
        if (flightNumber == null || flightNumber.isBlank()) {
            return null;
        }
        return flightRepository.findByFlightNumber(flightNumber);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAvailableFlights("", ""); // если нужно — заменить на отдельный метод в репозитории
    }
}
