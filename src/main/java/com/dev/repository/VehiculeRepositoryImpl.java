package com.dev.repository;

import com.dev.model.Vehicule;
import com.dev.database.VehiculeDBConfig;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehiculeRepositoryImpl implements VehiculeRepository {


    @Override
    public List<Vehicule> getAllVehicules() throws SQLException, ClassNotFoundException {
        List<Vehicule> vehicules = new ArrayList<>();
        String query = "SELECT * FROM car";
        try (Connection connection = VehiculeDBConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String registrationNumber = resultSet.getString("registration_number");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                LocalDate dateOfFirstRegistration = resultSet.getDate("date_of_first_registration").toLocalDate();
                double price = resultSet.getDouble("price");
                Vehicule vehicule = new Vehicule(registrationNumber, brand, model, dateOfFirstRegistration, price);
                vehicules.add(vehicule);
            }
        }
        return vehicules;
    }


    @Override
    public void updateVehicule(String registrationNumber, Vehicule newVehicule) throws SQLException {
        String query = "UPDATE car SET brand = ?, model = ?, date_of_first_registration = ?, price = ? WHERE registration_number = ?";
        try (Connection connection = VehiculeDBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newVehicule.getBrand());
            preparedStatement.setString(2, newVehicule.getModel());
            preparedStatement.setDate(3, java.sql.Date.valueOf(newVehicule.getDateOfFirstRegistration()));
            preparedStatement.setDouble(4, newVehicule.getPrice());
            preparedStatement.setString(5, registrationNumber);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicule> getVehiculesByAge(int age) throws SQLException, ClassNotFoundException {
        List<Vehicule> vehicules = new ArrayList<>();
        LocalDate limiteAge = LocalDate.now().minusYears(age);

        try (Connection connection = VehiculeDBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE date_of_first_registration <= ?")) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(limiteAge));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String registrationNumber = resultSet.getString("registration_number");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                LocalDate dateOfFirstRegistration = resultSet.getDate("date_of_first_registration").toLocalDate();
                double price = resultSet.getDouble("price");

                vehicules.add(new Vehicule(registrationNumber, brand, model, dateOfFirstRegistration, price));
            }
        }

        return vehicules;
    }

    @Override
    public List<Vehicule> getVehiculesByPriceRange(double minPrice, double maxPrice) {
        return null;
    }
    @Override
    public void addVehicule(Vehicule vehicule) throws SQLException {
        String query = "INSERT INTO car (registration_number, brand, model, date_of_first_registration, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = VehiculeDBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vehicule.getRegistrationNumber());
            preparedStatement.setString(2, vehicule.getBrand());
            preparedStatement.setString(3, vehicule.getModel());
            preparedStatement.setDate(4, java.sql.Date.valueOf(vehicule.getDateOfFirstRegistration()));
            preparedStatement.setDouble(5, vehicule.getPrice());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
