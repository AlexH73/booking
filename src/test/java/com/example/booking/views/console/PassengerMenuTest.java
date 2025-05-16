package com.example.booking.views.console;

import com.example.booking.views.PassengerView;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PassengerMenuTest {

    @Test
    void show_shouldCallRegisterPassenger() {
        FakePassengerView passengerView = new FakePassengerView();

        String input = "1\n0\n"; // Выбор "Регистрация нового пассажира", затем выход
        Scanner scanner = new Scanner(input);

        PassengerMenu passengerMenu = new PassengerMenu(passengerView, scanner);
        passengerMenu.show();

        assertTrue(passengerView.isRegisterPassengerCalled());
    }

    private static class FakePassengerView extends PassengerView {
        private boolean registerPassengerCalled = false;

        public FakePassengerView() {
            super(null, new Scanner(System.in));
        }

        @Override
        public void registerPassengerFromInput() {
            registerPassengerCalled = true;
        }

        public boolean isRegisterPassengerCalled() {
            return registerPassengerCalled;
        }
    }
}