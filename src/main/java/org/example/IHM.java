package org.example;

import org.example.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import static org.example.Hotel.*;
import static org.example.Hotel.listesDeChambres;
import static org.example.Reservation.listesDesReservations;

public class IHM {
    static Connection connection;

    static {
        try {
            connection = DatabaseManager.getPostgreSQLConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public IHM() throws SQLException, ParseException {
        Scanner scanner = new Scanner( System.in );

        int number = -1;
        System.out.println("Quel est le nom de l'hôtel ?");
        String nomHotel = scanner.nextLine();
        Hotel h1 = new Hotel(nomHotel, Service.viewChambres());
        Service.createChambre(1, false, 2, 16);
//        Service.createHotel(nomHotel);
        System.out.println(nomHotel + " créé avec succés !\n");
        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Ajouter un client");
            System.out.println("2. la liste des clients");
            System.out.println("3. Afficher les réservations d'un client");
            System.out.println("4. Ajouter une réservation");
            System.out.println("5. Annuler une réservation");
            System.out.println("6. Afficher la liste des réservations");
            System.out.println("7. Afficher la liste des chambres");
            System.out.println("0. Quitter");
            number = scanner.nextInt();
            scanner.nextLine();
            String nomClient;
            int numeroChambre;
            switch (number) {
                case 1:
                    String nom;
                    String prenom;
                    String numeroTelephone;
                    int tailleListe = clients.size();

                    System.out.println("Quel est le nom du client ?");
                    nom = scanner.nextLine();
                    System.out.println("Quel est le prénom du client ?");
                    prenom = scanner.nextLine();
                    System.out.println("Quel est le téléphone du client ?");
                    numeroTelephone = scanner.nextLine();
                    Service.createClient(nom, prenom, numeroTelephone);

                    if (tailleListe != clients.size()) {
                        System.out.println("\nClient ajouté avec succés !\n");
                    } else {
                        System.out.println("\nÉchec de l'ajout du client !\n");
                    }

                break;
                case 2:
                    System.out.println(h1.toString());
                    break;

                case 3:


                    System.out.println("Quel est le nom du client ?");
                    nomClient = scanner.nextLine();
                    for (Reservation listesDesReservation : listesDesReservations) {
                        if (listesDesReservation.getClient().getNom().equalsIgnoreCase(nomClient)) {
                            System.out.println(listesDesReservation);
                            break;
                        }
                    }
                    break;

                case 4:


                    int nombreDeLits = 0;
                    System.out.println("ID du client : ");
                    String numeroClient= scanner.nextLine();
                    System.out.println("Numéro de chambre : ");
                    numeroChambre = scanner.nextInt();
                    System.out.println("Nombre de lits : ");
                    nombreDeLits = scanner.nextInt();
                    System.out.println("Numéro de réservation :");
                    int nb_reservation = scanner.nextInt();
                    Service.createReservation(h1, numeroClient,numeroChambre, nombreDeLits, nb_reservation);

                    break;

                case 5:

                    System.out.println("Quel est le nom du client ?");
                    nomClient = scanner.nextLine();
                    System.out.println("Quel est le numéro de chambre ?");
                    numeroChambre = scanner.nextInt();
                    for (Reservation listesDesReservation : listesDesReservations) {
                        System.out.println(listesDesReservation.getId());
                        if (listesDesReservation.getClient().getNom().equalsIgnoreCase(nomClient) && listesDesReservation.getChambre().getNumero() == numeroChambre) {
                            listesDesReservation.getChambre().setStatut(false);
                            listesDeChambres.get(numeroChambre).setStatut(false);
                            System.out.println("\nRéservation annulée !\n");
                        }
                    }
                    break;

                case 6:
                    for (Reservation listesDesReservation : listesDesReservations) {
                        System.out.println(listesDesReservation.toString());
                    }
                    break;

                case 7:
                   Service.viewChambres();
            }
        }while (number != 0);
    }
}
