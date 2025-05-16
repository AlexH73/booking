package com.example.booking;

import com.example.booking.repository.*;
import com.example.booking.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Mock
    private PassengerRepository passengerRepo;
    @Mock
    private FlightRepository flightRepo;
    @Mock
    private TicketRepository ticketRepo;

    @Test
    void testMainExecutionFlow() {
        // Arrange
        String input = "0\n"; // Пользователь сразу выбирает выход
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Сохраняем оригинальный System.out для восстановления
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        Main.main(new String[]{});

        // Assert
        String result = outputStream.toString();
        Assertions.assertTrue(result.contains("Работа системы завершена"));

        // Восстанавливаем потоки
        System.setIn(System.in);
        System.setOut(originalOut);
    }

    @Test
    void testDependencyInitialization() {
        // Arrange
        PassengerServiceImpl passengerService = new PassengerServiceImpl(passengerRepo);
        FlightServiceImpl flightService = new FlightServiceImpl(flightRepo);
        BookingServiceImpl bookingService = new BookingServiceImpl(ticketRepo, flightRepo);

        // Assert
        Assertions.assertNotNull(passengerService);
        Assertions.assertNotNull(flightService);
        Assertions.assertNotNull(bookingService);
    }
}