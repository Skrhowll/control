package com.dev.model;

import java.time.LocalDate;

public class Vehicule {
    private String registrationNumber;
    private String brand;
    private String model;
    private LocalDate dateOfFirstRegistration;
    private double price;

    public Vehicule(String registrationNumber, String brand, String model, LocalDate dateOfFirstRegistration, double price) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.dateOfFirstRegistration = dateOfFirstRegistration;
        this.price = price;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getDateOfFirstRegistration() {
        return dateOfFirstRegistration;
    }

    public void setDateOfFirstRegistration(LocalDate dateOfFirstRegistration) {
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Véhicule : [" +
                "numéro d'immatriculation='" + registrationNumber + '\'' +
                ", marque='" + brand + '\'' +
                ", modèle='" + model + '\'' +
                ", date de première immatriculation=" + dateOfFirstRegistration +
                ", prix=" + price +
                ']';
    }
}
