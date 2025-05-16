package com.example.booking.views.console;

import com.example.booking.views.TicketView;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketMenuTest {

    @Test
    void show_shouldCallBookTicket() {
        FakeTicketView ticketView = new FakeTicketView();

        String input = "1\n0\n"; // Выбор "Оформить билет", затем выход
        Scanner scanner = new Scanner(input);

        TicketMenu ticketMenu = new TicketMenu(ticketView, scanner);
        ticketMenu.show();

        assertTrue(ticketView.isBookTicketCalled());
    }

    private static class FakeTicketView extends TicketView {
        private boolean bookTicketCalled = false;

        public FakeTicketView() {
            super(null, null, null, new Scanner(System.in));
        }

        @Override
        public void bookTicketInput() {
            bookTicketCalled = true;
        }

        public boolean isBookTicketCalled() {
            return bookTicketCalled;
        }
    }
}