package com.example.booking.views.console;

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

/**
 * The type Main menu test.
 */
@ExtendWith(MockitoExtension.class)
class MainMenuTest {

    @Mock
    private PassengerMenu passengerMenu;

    @Mock
    private FlightMenu flightMenu;

    @Mock
    private TicketMenu ticketMenu;

    private MainMenu mainMenu;

    /**
     * Start should invoke passenger menu on input 1.
     */
    @Test
    void start_shouldInvokePassengerMenuOnInput1() {
        // Arrange
        provideInput("1\n0\n");
        mainMenu = new MainMenu(passengerMenu, flightMenu, ticketMenu, new Scanner(System.in));

        // Act
        mainMenu.start();

        // Assert
        verify(passengerMenu).show();
        verify(flightMenu, never()).show();
        verify(ticketMenu, never()).show();
    }

    /**
     * Start should invoke flight menu on input 2.
     */
    @Test
    void start_shouldInvokeFlightMenuOnInput2() {
        provideInput("2\n0\n");
        mainMenu = new MainMenu(passengerMenu, flightMenu, ticketMenu, new Scanner(System.in));

        mainMenu.start();

        verify(flightMenu).show();
        verify(passengerMenu, never()).show();
    }

    /**
     * Start should invoke ticket menu on input 3.
     */
    @Test
    void start_shouldInvokeTicketMenuOnInput3() {
        provideInput("3\n0\n");
        mainMenu = new MainMenu(passengerMenu, flightMenu, ticketMenu, new Scanner(System.in));

        mainMenu.start();

        verify(ticketMenu).show();
    }

    /**
     * Start should exit on input 0.
     */
    @Test
    void start_shouldExitOnInput0() {
        provideInput("0\n");
        mainMenu = new MainMenu(passengerMenu, flightMenu, ticketMenu, new Scanner(System.in));

        mainMenu.start();

        verifyNoInteractions(passengerMenu, flightMenu, ticketMenu);
    }

    /**
     * Start should handle invalid input.
     */
    @Test
    void start_shouldHandleInvalidInput() {
        provideInput("invalid\n9\n0\n");
        mainMenu = new MainMenu(passengerMenu, flightMenu, ticketMenu, new Scanner(System.in));

        mainMenu.start();

        verify(passengerMenu, never()).show();
        verify(flightMenu, never()).show();
        verify(ticketMenu, never()).show();
    }

    /**
     * Main menu flow should print correct structure.
     */
    @Test
    void mainMenuFlow_shouldPrintCorrectStructure() {
        provideInput("0\n");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        mainMenu.start();

        String result = outContent.toString();
        assertAll(
                () -> assertTrue(result.contains("=== ГЛАВНОЕ МЕНЮ ===")),
                () -> assertTrue(result.contains("2. Управление рейсами")),
                () -> assertTrue(result.contains("Выберите раздел:"))
        );

        System.setOut(System.out);
    }

    private void provideInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}