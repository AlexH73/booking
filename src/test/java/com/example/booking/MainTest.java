package com.example.booking;

import com.example.booking.views.console.MainMenu;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void main_shouldCallMainMenuStart() {
        // Arrange
        String input = "0\n"; // Выбор выхода из программы
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Перехват вывода в консоль
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        Main.main(new String[]{});

        // Assert
        String result = outputStream.toString();
        assertTrue(result.contains("Работа системы завершена"));

        // Восстановление потоков
        System.setIn(System.in);
        System.setOut(originalOut);
    }
}