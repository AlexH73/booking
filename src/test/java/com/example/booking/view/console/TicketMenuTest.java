package com.example.booking.view.console;

import com.example.booking.view.TicketView;
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
class TicketMenuTest {

    @Mock
    private TicketView ticketView;

    private TicketMenu ticketMenu;

    private void provideInput(String input) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ticketMenu = new TicketMenu(ticketView, scanner);
    }

    @Test
    void show_shouldCallBookTicketOnInput1() {
        // Arrange
        provideInput("1\n0\n");

        // Act
        ticketMenu.show();

        // Assert
        verify(ticketView).bookTicketInput();
        verify(ticketView, never()).cancelTicketInput();
    }

    @Test
    void show_shouldCallCancelTicketOnInput2() {
        provideInput("2\n0\n");
        ticketMenu.show();
        verify(ticketView).cancelTicketInput();
    }

    @Test
    void show_shouldCallShowTicketsByPassengerOnInput3() {
        provideInput("3\n0\n");
        ticketMenu.show();
        verify(ticketView).showTicketsByPassenger();
    }

    @Test
    void show_shouldCallShowTicketsByStatusOnInput4() {
        provideInput("4\n0\n");
        ticketMenu.show();
        verify(ticketView).showTicketsByStatus();
    }

    @Test
    void show_shouldCallShowAllTicketsOnInput5() {
        provideInput("5\n0\n");
        ticketMenu.show();
        verify(ticketView).showAllTickets();
    }

    @Test
    void show_shouldExitImmediatelyOnInput0() {
        provideInput("0\n");
        ticketMenu.show();
        verifyNoInteractions(ticketView);
    }

    @Test
    void show_shouldHandleInvalidInput() {
        // Arrange
        provideInput("invalid\n99\n0\n");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        ticketMenu.show();

        // Assert
        assertTrue(outContent.toString().contains("Неверный ввод!"));
        verify(ticketView, never()).bookTicketInput();
        System.setOut(System.out);
    }

    @Test
    void menu_shouldDisplayCorrectOptions() {
        // Arrange
        provideInput("0\n");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        ticketMenu.show();

        // Assert
        String output = outContent.toString();
        assertAll(
                () -> assertTrue(output.contains("УПРАВЛЕНИЕ БИЛЕТАМИ")),
                () -> assertTrue(output.contains("1. Оформить билет")),
                () -> assertTrue(output.contains("4. Билеты по статусу")),
                () -> assertTrue(output.contains("0. Назад"))
        );
        System.setOut(System.out);
    }

    @Test
    void shouldProcessMultipleOperations() {
        // Arrange
        provideInput("1\n2\n3\n4\n5\n0\n");

        // Act
        ticketMenu.show();

        // Assert
        verify(ticketView, times(1)).bookTicketInput();
        verify(ticketView, times(1)).cancelTicketInput();
        verify(ticketView, times(1)).showTicketsByPassenger();
        verify(ticketView, times(1)).showTicketsByStatus();
        verify(ticketView, times(1)).showAllTickets();
    }
}