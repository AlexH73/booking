package com.example.booking.model;

import java.time.LocalDate;
import java.util.Objects;

public class Passenger {
    private String name;
    private String passportNumber;    // паспортный номер
    private LocalDate dateOfBirth;// дата рождения
    private String email;
    private String phoneNumber;

    public Passenger(String name, String passportNumber, LocalDate dateOfBirth , String email , String phoneNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(name, passenger.name) && Objects.equals(passportNumber, passenger.passportNumber) && Objects.equals(dateOfBirth, passenger.dateOfBirth) && Objects.equals(email, passenger.email) && Objects.equals(phoneNumber, passenger.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passportNumber, dateOfBirth, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
