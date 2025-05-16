package com.example.booking.views.console;

import com.example.booking.views.PassengerView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerMenuTest {

    @Mock
    private PassengerView passengerView;

    private PassengerMenu passengerMenu;
    private final Scanner scanner = new Scanner(System.in);

    private void provideInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        passengerMenu = new PassengerMenu(passengerView, new Scanner(System.in));
    }

    @Test
    void show_shouldCallRegisterPassengerOnInput1() {
        // Arrange
        provideInput("1\n0\n");

        // Act
        passengerMenu.show();

        // Assert
        verify(passengerView).registerPassengerFromInput();
        verify(passengerView, never()).deletePassengerByPassportInput();
    }

    @Test
    void show_shouldCallFindPassengerOnInput2() {
        provideInput("2\n0\n");
        passengerMenu.show();
        verify(passengerView).findPassengerByPassportInput();
    }

    @Test
    void show_shouldCallUpdatePassengerOnInput3() {
        provideInput("3\n0\n");
        passengerMenu.show();
        verify(passengerView).updatePassengerByPassportInput();
    }

    @Test
    void show_shouldCallDeletePassengerOnInput4() {
        provideInput("4\n0\n");
        passengerMenu.show();
        verify(passengerView).deletePassengerByPassportInput();
    }

    @Test
    void show_shouldCallDisplayAllPassengersOnInput5() {
        provideInput("5\n0\n");
        passengerMenu.show();
        verify(passengerView).displayAllPassengers();
    }

    @Test
    void show_shouldExitImmediatelyOnInput0() {
        provideInput("0\n");
        passengerMenu.show();
        verifyNoInteractions(passengerView);
    }

    @Test
    void show_shouldHandleInvalidInput() {
        // Arrange
        provideInput("invalid\n6\n0\n");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        passengerMenu.show();

        // Assert
        assertTrue(outContent.toString().contains("Неверный ввод!"));
        verify(passengerView, never()).registerPassengerFromInput();
        System.setOut(System.out);
    }

    @Test
    void menu_shouldDisplayCorrectOptions() {
        // Arrange
        provideInput("0\n");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        passengerMenu.show();

        // Assert
        String output = outContent.toString();
        assertAll(
                () -> assertTrue(output.contains("УПРАВЛЕНИЕ ПАССАЖИРАМИ")),
                () -> assertTrue(output.contains("1. Регистрация")),
                () -> assertTrue(output.contains("5. Список")),
                () -> assertTrue(output.contains("0. Назад"))
        );
        System.setOut(System.out);
    }

    @Test
    void shouldReturnToMainMenuAfterOperation() {
        // Arrange
        provideInput("1\n0\n");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        passengerMenu.show();

        // Assert
        assertTrue(outContent.toString().contains("УПРАВЛЕНИЕ ПАССАЖИРАМИ"));
        verify(passengerView, times(1)).registerPassengerFromInput();
        System.setOut(System.out);
    }
}