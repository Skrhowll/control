package com.dev.repository;

import com.dev.model.Vehicule;

import java.sql.SQLException;
import java.util.List;

public interface VehiculeRepository {
    static void addVehicle(Vehicule vehicule) throws SQLException {

    }


    List<Vehicule> getAllVehicules() throws SQLException, ClassNotFoundException;

    void updateVehicule(String registrationNumber, Vehicule newVehicule) throws SQLException;

    List<Vehicule> getVehiculesByAge(int age) throws SQLException, ClassNotFoundException;

    List<Vehicule> getVehiculesByPriceRange(double minPrice, double maxPrice) throws SQLException, ClassNotFoundException;

    void addVehicule(Vehicule vehicule) throws SQLException;
}
