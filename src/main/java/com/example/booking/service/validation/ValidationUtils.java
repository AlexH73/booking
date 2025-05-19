package com.example.booking.service.validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\p{L} \\-'’]+$", Pattern.UNICODE_CHARACTER_CLASS);
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9\\s-()]{7,}$");
    private static final int MIN_AGE = 18;

    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidBirthDate(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now().minusYears(MIN_AGE));
    }
}
