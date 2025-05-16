package com.example.booking.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {
    // Тестовые данные
    private static final String NAME = "John Doe";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1990, 5, 15);
    private static final String EMAIL = "john.doe@example.com";
    private static final String PHONE = "+49123456789";

    @Test
    void testConstructorAndGetters() {
        Passenger passenger = new Passenger(NAME, BIRTH_DATE, EMAIL, PHONE);

        assertAll(
                () -> assertEquals(NAME, passenger.getName()),
                () -> assertEquals(BIRTH_DATE, passenger.getDateOfBirth()),
                () -> assertEquals(EMAIL, passenger.getEmail()),
                () -> assertEquals(PHONE, passenger.getPhoneNumber()),
                () -> assertNotNull(passenger.getPassportNumber()),
                () -> assertEquals(10, passenger.getPassportNumber().length())
        );
    }

    @Test
    void testSetters() {
        Passenger passenger = new Passenger(NAME, BIRTH_DATE, EMAIL, PHONE);

        String newName = "Jane Smith";
        LocalDate newDate = LocalDate.of(2000, 1, 1);
        String newEmail = "jane@test.de";
        String newPhone = "+49876543210";
        String newPassport = "NEWPASS123";

        passenger.setName(newName);
        passenger.setDateOfBirth(newDate);
        passenger.setEmail(newEmail);
        passenger.setPhoneNumber(newPhone);
        passenger.setPassportNumber(newPassport);

        assertAll(
                () -> assertEquals(newName, passenger.getName()),
                () -> assertEquals(newDate, passenger.getDateOfBirth()),
                () -> assertEquals(newEmail, passenger.getEmail()),
                () -> assertEquals(newPhone, passenger.getPhoneNumber()),
                () -> assertEquals(newPassport, passenger.getPassportNumber())
        );
    }

    @Test
    void testPassportNumberGeneration() {
        Passenger passenger1 = new Passenger(NAME, BIRTH_DATE, EMAIL, PHONE);
        Passenger passenger2 = new Passenger(NAME, BIRTH_DATE, EMAIL, PHONE);

        assertAll(
                () -> assertTrue(passenger1.getPassportNumber().matches("[A-Z0-9]{10}")),
                () -> assertTrue(passenger2.getPassportNumber().matches("[A-Z0-9]{10}")),
                () -> assertNotEquals(passenger1.getPassportNumber(), passenger2.getPassportNumber())
        );
    }

    @Test
    void testEqualsAndHashCode() {
        Passenger passenger1 = new Passenger(NAME, BIRTH_DATE, EMAIL, PHONE);
        Passenger passenger2 = new Passenger(NAME, BIRTH_DATE, EMAIL, PHONE);
        Passenger different = new Passenger("Alice", BIRTH_DATE, EMAIL, PHONE);

        // Принудительно устанавливаем одинаковый passportNumber для проверки equals
        String passport = "ABCDE12345";
        passenger1.setPassportNumber(passport);
        passenger2.setPassportNumber(passport);
        different.setPassportNumber("DIFFERENT123");

        assertAll(
                () -> assertEquals(passenger1, passenger2),
                () -> assertNotEquals(passenger1, different),
                () -> assertEquals(passenger1.hashCode(), passenger2.hashCode()),
                () -> assertNotEquals(passenger1.hashCode(), different.hashCode())
        );
    }

    @Test
    void testToString() {
        Passenger passenger = new Passenger(NAME, BIRTH_DATE, EMAIL, PHONE);
        String result = passenger.toString();

        assertAll(
                () -> assertTrue(result.contains(NAME)),
                () -> assertTrue(result.contains(passenger.getPassportNumber())),
                () -> assertTrue(result.contains(BIRTH_DATE.toString())),
                () -> assertTrue(result.contains(EMAIL)),
                () -> assertTrue(result.contains(PHONE))
        );
    }
}
