package com.example.booking.views.console;

import com.example.booking.views.FlightView;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightMenuTest {

    @Test
    void show_shouldCallRegisterFlight() {
        FakeFlightView flightView = new FakeFlightView();

        String input = "1\n0\n"; // Выбор "Добавить рейс", затем выход
        Scanner scanner = new Scanner(input);

        FlightMenu flightMenu = new FlightMenu(flightView, scanner);
        flightMenu.show();

        assertTrue(flightView.isRegisterFlightCalled());
    }

    private static class FakeFlightView extends FlightView {
        private boolean registerFlightCalled = false;

        public FakeFlightView() {
            super(null, new Scanner(System.in));
        }

        @Override
        public void registerFlightInput() {
            registerFlightCalled = true;
        }

        public boolean isRegisterFlightCalled() {
            return registerFlightCalled;
        }
    }
}