package com.dev;

import com.dev.database.VehiculeDBConfig;
import com.dev.file.AppFileReader;
import com.dev.model.Vehicule;
import com.dev.repository.VehiculeRepository;
import com.dev.repository.VehiculeRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Chargement de l'application de gestion des véhicules...");
        String filePath = "C:\\Users\\wilfr\\Downloads\\data.txt";
        List<Vehicule> vehicules = AppFileReader.lireVehiculesDuFichier(filePath);
        for (Vehicule vehicule : vehicules) {
            System.out.println(vehicule);
        }
        System.out.println("Données des véhicules chargées avec succès depuis le fichier.");
        System.out.println("Liste des véhicules récupérés depuis le fichier :");
        afficherVehicules(vehicules);
        List<Vehicule> vehiculesMoinsDe20Ans = filtrerVehiculesMoinsDe20Ans(vehicules);
        System.out.println("Filtrage des véhicules de moins de 20 ans effectué.");
        System.out.println("Liste des véhicules de moins de 20 ans :");
        afficherVehicules(vehiculesMoinsDe20Ans);
        insererVehiculesDansBaseDeDonnees(vehiculesMoinsDe20Ans);
        System.out.println("Véhicules de moins de 20 ans insérés dans la base de données.");
        afficherMenu(vehicules);
    }


    // Méthode pour afficher les véhicules
    private static void afficherVehicules(List<Vehicule> vehicules) {
        for (Vehicule vehicule : vehicules) {
            System.out.println(vehicule);
        }
    }

    // Méthode pour filtrer les véhicules de moins de 20 ans
    public static List<Vehicule> filtrerVehiculesMoinsDe20Ans(List<Vehicule> vehicules) {
        List<Vehicule> vehiculesMoinsDe20Ans = new ArrayList<>();
        LocalDate limite20Ans = LocalDate.now().minusYears(20);

        for (Vehicule vehicule : vehicules) {
            if (vehicule.getDateOfFirstRegistration().isAfter(limite20Ans)) {
                vehiculesMoinsDe20Ans.add(vehicule);
            }
        }

        return vehiculesMoinsDe20Ans;
    }
    public static void insererVehiculesDansBaseDeDonnees(List<Vehicule> vehicules) {
        VehiculeDBConfig vehiculeDBConfig = new VehiculeDBConfig();
        try (Connection connection = VehiculeDBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO car (registration_number, brand, model, date_of_first_registration, price) VALUES (?, ?, ?, ?, ?)")) {
            for (Vehicule vehicule : vehicules) {
                LocalDate limite20Ans = LocalDate.now().minusYears(20);
                if (vehicule.getDateOfFirstRegistration().isAfter(limite20Ans)) {
                    preparedStatement.setString(1, vehicule.getRegistrationNumber());
                    preparedStatement.setString(2, vehicule.getBrand());
                    preparedStatement.setString(3, vehicule.getModel());
                    preparedStatement.setDate(4, java.sql.Date.valueOf(vehicule.getDateOfFirstRegistration())); // Convertir LocalDate en java.sql.Date
                    preparedStatement.setDouble(5, vehicule.getPrice());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void afficherMenu(List<Vehicule> vehicules) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu :");
            System.out.println("a. Afficher les véhicules");
            System.out.println("b. Modifier un véhicule");
            System.out.println("c. Ajouter un véhicule");
            System.out.println("d. Supprimer un véhicule");
            System.out.println("e. Afficher les véhicules d’un âge donné");
            System.out.println("f. Afficher les véhicules dont le prix est compris entre un prix minimal et un prix maximal");
            System.out.println("q. Quitter");

            System.out.print("\nChoix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "a":
                    System.out.println("\nListe des véhicules :");
                    VehiculeRepository vehiculeRepositoryA = null;
                    try {
                        vehiculeRepositoryA = new VehiculeRepositoryImpl();
                        List<Vehicule> vehiculesFromDB = vehiculeRepositoryA.getAllVehicules();
                        afficherVehicules(vehiculesFromDB);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "b":

                case "c":
                    System.out.println("\nAjout d'un nouveau véhicule :");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.print("Numéro d'immatriculation : ");
                    String registrationNumber = scanner.nextLine();
                    System.out.print("Marque : ");
                    String brand = scanner.nextLine();
                    System.out.print("Modèle : ");
                    String model = scanner.nextLine();
                    System.out.print("Date de première immatriculation (format dd/MM/yyyy) : ");
                    String dateInput = scanner.nextLine();
                    LocalDate dateOfFirstRegistration = LocalDate.parse(dateInput, formatter);
                    System.out.print("Prix : ");
                    double price = Double.parseDouble(scanner.nextLine());
                    Vehicule newVehicule = new Vehicule(registrationNumber, brand, model, dateOfFirstRegistration, price);

                    try {

                        VehiculeRepository.addVehicle(newVehicule);
                        System.out.println("\nVéhicule ajouté avec succès !");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "d":


                case "e":
                    System.out.println("Entrez l'âge des véhicules à afficher : ");
                    int age = Integer.parseInt(scanner.nextLine());
                    VehiculeRepository vehiculeRepositoryE;
                    try {
                        vehiculeRepositoryE = new VehiculeRepositoryImpl();
                        List<Vehicule> vehiculesByAge = vehiculeRepositoryE.getVehiculesByAge(age);
                        System.out.println("\nListe des véhicules de " + age + " ans :");
                        afficherVehicules(vehiculesByAge);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "f":
                    System.out.println("\nFonctionnalité non implémentée.");
                    break;
                case "q":
                    System.out.println("\nFermeture de l'application...");
                    break;
                default:
                    System.out.println("\nChoix invalide. Veuillez réessayer.");
            }
        }
        }
    }

