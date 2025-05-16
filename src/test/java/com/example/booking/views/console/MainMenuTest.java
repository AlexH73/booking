package com.example.booking.views.console;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainMenuTest {

    @Test
    void start_shouldCallPassengerMenu() {
        FakePassengerMenu passengerMenu = new FakePassengerMenu();
        FakeFlightMenu flightMenu = new FakeFlightMenu();
        FakeTicketMenu ticketMenu = new FakeTicketMenu();

        String input = "1\n0\n"; // Выбор "Управление пассажирами", затем выход
        Scanner scanner = new Scanner(input);

        MainMenu mainMenu = new MainMenu(passengerMenu, flightMenu, ticketMenu, scanner);
        mainMenu.start();

        assertTrue(passengerMenu.isShowCalled());
    }

    @Test
    void start_shouldCallFlightMenu() {
        FakePassengerMenu passengerMenu = new FakePassengerMenu();
        FakeFlightMenu flightMenu = new FakeFlightMenu();
        FakeTicketMenu ticketMenu = new FakeTicketMenu();

        String input = "2\n0\n"; // Выбор "Управление рейсами", затем выход
        Scanner scanner = new Scanner(input);

        MainMenu mainMenu = new MainMenu(passengerMenu, flightMenu, ticketMenu, scanner);
        mainMenu.start();

        assertTrue(flightMenu.isShowCalled());
    }

    @Test
    void start_shouldCallTicketMenu() {
        FakePassengerMenu passengerMenu = new FakePassengerMenu();
        FakeFlightMenu flightMenu = new FakeFlightMenu();
        FakeTicketMenu ticketMenu = new FakeTicketMenu();

        String input = "3\n0\n"; // Выбор "Управление билетами", затем выход
        Scanner scanner = new Scanner(input);

        MainMenu mainMenu = new MainMenu(passengerMenu, flightMenu, ticketMenu, scanner);
        mainMenu.start();

        assertTrue(ticketMenu.isShowCalled());
    }

    private static class FakePassengerMenu extends PassengerMenu {
        private boolean showCalled = false;

        public FakePassengerMenu() {
            super(null, new Scanner(System.in));
        }

        @Override
        public void show() {
            showCalled = true;
        }

        public boolean isShowCalled() {
            return showCalled;
        }
    }

    private static class FakeFlightMenu extends FlightMenu {
        private boolean showCalled = false;

        public FakeFlightMenu() {
            super(null, new Scanner(System.in));
        }

        @Override
        public void show() {
            showCalled = true;
        }

        public boolean isShowCalled() {
            return showCalled;
        }
    }

    private static class FakeTicketMenu extends TicketMenu {
        private boolean showCalled = false;

        public FakeTicketMenu() {
            super(null, new Scanner(System.in));
        }

        @Override
        public void show() {
            showCalled = true;
        }

        public boolean isShowCalled() {
            return showCalled;
        }
    }
}