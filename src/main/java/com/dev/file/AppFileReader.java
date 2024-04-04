package com.dev.file;

import com.dev.model.Vehicule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppFileReader {
    public static List<Vehicule> lireVehiculesDuFichier(String filePath) {
        List<Vehicule> vehicules = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Vehicule vehicule = new Vehicule(
                        parts[0], // registrationNumber
                        parts[1], // brand
                        parts[2], // model
                        LocalDate.parse(parts[3], formatter), // dateOfFirstRegistration
                        Double.parseDouble(parts[4]) // price
                );
                vehicules.add(vehicule);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicules;
    }
}
