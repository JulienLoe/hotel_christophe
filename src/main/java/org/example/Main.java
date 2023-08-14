package org.example;

import java.util.Scanner;

import static org.example.Hotel.*;
import static org.example.Reservation.listesDesReservations;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int number = -1;
        System.out.println("Quel est le nom de l'hôtel ?");
        String nomHotel = scanner.nextLine();
        Hotel h1 = new Hotel(nomHotel);
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
            if (number == 1){
                String nom;
                String prenom;
                String numeroTelephone;
                int tailleListe =  clients.size();

                System.out.println("Quel est le nom du client ?");
                nom = scanner.nextLine();
                System.out.println("Quel est le prénom du client ?");
                prenom = scanner.nextLine();
                System.out.println("Quel est le téléphone du client ?");
                numeroTelephone = scanner.nextLine();
                Client newClient  = new Client(tabClient.length + 1,nom, prenom,numeroTelephone);
                clients.add(newClient);
                if (tailleListe != clients.size()) {
                    System.out.println("\nClient ajouté avec succés !\n");
                }
                else {
                    System.out.println("\nÉchec de l'ajout du client !\n");
                }
            }
            if (number == 2){
                System.out.println(h1.toString());
            }
            if (number == 3){
                String nomClient;
                System.out.println("Quel est le nom du client ?");
                nomClient = scanner.nextLine();
                for (Reservation listesDesReservation : listesDesReservations) {
                    if (listesDesReservation.getClient().getNom().equalsIgnoreCase(nomClient)) {
                        System.out.println(listesDesReservation);
                        break;
                    }
                }
            }
            if (number == 4){
                String nomClient;
                int numeroChambre;
                int nombreDeLits = 0;
                System.out.println("Nom du client : ");
                nomClient = scanner.nextLine();
                System.out.println("Numéro de chambre : ");
                numeroChambre = scanner.nextInt();
                System.out.println("Nombre de lits : ");
                nombreDeLits = scanner.nextInt();

                for (int i = 0; i < clients.size(); i++){

                    if (clients.get(i).getNom().equalsIgnoreCase(nomClient)){

                        for (int j = 0; j < listesDeChambres.size(); j++){
                            if (listesDeChambres.get(j).getNumero() == numeroChambre && !listesDeChambres.get(j).isStatut()){
                                if (nombreDeLits == listesDeChambres.get(j).getNombreLits()) {
                                    Reservation r1 = new Reservation(1, h1.getListesDeChambres().get(numeroChambre), h1.getClients().get(i), true);
                                    listesDesReservations.add(r1);
                                    listesDeChambres.get(numeroChambre).setStatut(true);
                                    System.out.println("Réservation ajoutée !\n");
                                    break;
                                }
                                System.out.println("\nLe nombre de lits ne correspond pas à la chambre !\n");
                                break;
                            }

                            if (listesDeChambres.get(j).isStatut() && listesDeChambres.get(j).getNumero() == numeroChambre){
                                System.out.println("\nLa chambre est déjà occupée!\n");
                                break;
                            }

                            if (listesDeChambres.size() <= j + 1){
                                    System.out.println(listesDeChambres.get(j).isStatut());
                                    System.out.println("\nLe numéro de chambre n'existe pas !\n");
                            }
                    }
                    break;
                }
                    if (clients.size() <= i + 1){
                        System.out.println("\nLe nom n'existe pas !\n");
                    }
                }

            }
            if (number == 5){
                String nomClient;
                int numeroChambre;
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
            }
            if (number == 6){
                for (Reservation listesDesReservation : listesDesReservations) {
                    System.out.println(listesDesReservation.toString());
                }
            }
            if (number == 7){
                for (Chambre listesDeChambre : listesDeChambres) {
                    System.out.println(listesDeChambre.toString());
                }
            }
        }while (number != 0);
    }
}