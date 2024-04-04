package com.dev;

import static org.junit.Assert.assertEquals;
import com.dev.App;
import com.dev.model.Vehicule;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppTest {

    @Test
    public void testFiltrerVehiculesMoinsDe20Ans() {
        // Création de quelques véhicules avec des dates de première immatriculation différentes
        Vehicule vehicule1 = new Vehicule("AB123", "Toyota", "", LocalDate.of(2019, 1, 1), 20000.0);
        Vehicule vehicule2 = new Vehicule("CD456", "Honda", "Accord", LocalDate.of(2000, 5, 15), 15000.0);
        Vehicule vehicule3 = new Vehicule("EF789", "Ford", "Focus", LocalDate.of(2018, 10, 20), 18000.0);

        // Création d'une liste contenant ces véhicules
        List<Vehicule> vehicules = new ArrayList<>();
        vehicules.add(vehicule1);
        vehicules.add(vehicule2);
        vehicules.add(vehicule3);

        // Appel de la méthode de filtre pour récupérer les véhicules de moins de 20 ans
        List<Vehicule> vehiculesMoinsDe20Ans = App.filtrerVehiculesMoinsDe20Ans(vehicules);

        // Vérification que le nombre de véhicules dans la liste filtrée est correct
        assertEquals(2, vehiculesMoinsDe20Ans.size());

        // Vérification que les véhicules dans la liste filtrée sont ceux attendus
        assertEquals("AB123", vehiculesMoinsDe20Ans.get(0).getRegistrationNumber());
        assertEquals("EF789", vehiculesMoinsDe20Ans.get(1).getRegistrationNumber());
    }
}
