package com.example.booking.views.console;

public enum ConsoleColor {
    RED("\033[31m"),      // Красный
    GREEN("\033[32m"),    // Зеленый
    YELLOW("\033[33m"),   // Желтый
    BLUE("\033[34m"),     // Синий
    RESET("\033[0m");     // Сброс цвета

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    /**
     * Применяет цвет к тексту и автоматически сбрасывает его после.
     *
     * @param text текст для окрашивания
     * @return окрашенный текст
     */
    public String apply(String text) {
        return code + text + RESET.code;
    }

    /**
     * Возвращает ANSI-код цвета (без сброса).
     */
    public String getCode() {
        return code;
    }
}
