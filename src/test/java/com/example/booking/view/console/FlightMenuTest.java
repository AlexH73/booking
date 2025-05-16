package com.example.booking.view.console;

import com.example.booking.view.FlightView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightMenuTest {

    @Mock
    private FlightView flightView;

    @InjectMocks
    private FlightMenu flightMenu;

    private void provideInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        flightMenu = new FlightMenu(flightView, new Scanner(in));
    }

    @Test
    void show_shouldCallRegisterFlightInput() {
        // Arrange
        provideInput("1\n0\n"); // Выбор пункта 1 и выход

        // Act
        flightMenu.show();

        // Assert
        verify(flightView).registerFlightInput();
    }

    @Test
    void show_shouldCallFindAvailableFlightsInput() {
        // Arrange
        provideInput("2\n0\n");

        // Act
        flightMenu.show();

        // Assert
        verify(flightView).findAvailableFlightsInput();
    }

    @Test
    void show_shouldCallShowAllFlights() {
        // Arrange
        provideInput("3\n0\n");

        // Act
        flightMenu.show();

        // Assert
        verify(flightView).showAllFlights();
    }

    @Test
    void show_shouldCallUpdateAvailableSeatsInput() {
        // Arrange
        provideInput("4\n0\n");

        // Act
        flightMenu.show();

        // Assert
        verify(flightView).updateAvailableSeatsInput();
    }

    @Test
    void show_shouldCallDeleteFlightInput() {
        // Arrange
        FlightView mockView = mock(FlightView.class);
        String input = "5\n0\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        flightMenu = new FlightMenu(mockView, scanner);

        // Act
        flightMenu.show();

        // Assert
        verify(mockView).deleteFlightInput();
    }

    @Test
    void show_shouldHandleInvalidInput() {
        // Arrange
        provideInput("invalid\n0\n");

        // Act
        flightMenu.show();

        // Assert
        verify(flightView, never()).deleteFlightInput();
    }

    @Test
    void show_shouldExitOnZeroInput() {
        // Arrange
        provideInput("0\n");

        // Act
        flightMenu.show();

        // Assert
        verifyNoInteractions(flightView);
    }
}