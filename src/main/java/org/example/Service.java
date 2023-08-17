package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.Hotel.clients;
import static org.example.Hotel.listesDeChambres;
import static org.example.IHM.connection;
import static org.example.Reservation.listesDesReservations;

public class Service {


//    public static void createHotel(String nomHotel){
//
//        Hotel h1 = new Hotel(nomHotel);
//
//    }

    public static void createClient (String lastname, String firstname, String nbPhone) throws SQLException, ParseException {




        String request = "INSERT INTO client (first_name, last_name, nb_phone) VALUES (?, ?, ?)";

        Client client = new Client(1, lastname, firstname, nbPhone);
        PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, client.getNom());
        preparedStatement.setString(2, client.getPrenom());
        preparedStatement.setString(3, client.getNumeroTelephone());

        int nbRows = preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            System.out.println("ID de l'étudiant est :" + resultSet.getInt(1));
            client.setId(resultSet.getInt(1));
        }


        if (nbRows > 0) {
            System.out.println("Des données renvoyées par la requête");
        } else {
            System.out.println("Aucune données renvoyées par la requête");
        }
    }

        public static void createChambre(int id, Boolean statut, int nbLits, int tarif) throws SQLException {
            Chambre ch1 = new Chambre(1, statut, nbLits, tarif);

            String request = "INSERT INTO chambre (statut, nb_lits, tarif) VALUES (?, ?, ?)";


            PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setBoolean(1, ch1.isStatut());
            preparedStatement.setInt(2, ch1.getNombreLits());
            preparedStatement.setDouble(3, ch1.getTarif());

            int nbRows = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("ID de l'étudiant est :" + resultSet.getInt(1));
                ch1.setNumero(resultSet.getInt(1));
            }
            if (nbRows > 0) {
                System.out.println("Des données renvoyées par la requête");
            } else {
                System.out.println("Aucune données renvoyées par la requête");
            }
        }

        public static void createReservation(Hotel h1, String nomClient, int numeroChambre, int nombreDeLits, int nb_reservation ) throws SQLException {
            listesDeChambres = viewChambres();
            clients = viewClient();
            for (int i = 0; i < clients.size(); i++) {

                if (clients.get(i).getNom().equalsIgnoreCase(nomClient)) {

                    for (int j = 0; j < listesDeChambres.size(); j++) {
                        if (listesDeChambres.get(j).getNumero() == numeroChambre && !listesDeChambres.get(j).isStatut()) {
                            if (nombreDeLits == listesDeChambres.get(j).getNombreLits()) {

                                String request = "INSERT INTO reservation (nb_reservation, ch_id, cl_id, statut) VALUES (?, ?, ?, ?)";


                                PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
                                Reservation r1 = new Reservation(1, nb_reservation, h1.getListesDeChambres().get(numeroChambre),  h1.getClients().get(i), true);
                                preparedStatement.setInt(1, r1.getNb_reservation());
                                preparedStatement.setInt(2, r1.getChambre().getNumero());
                                preparedStatement.setDouble(3, r1.getClient().getId());
                                preparedStatement.setBoolean(4, true);
                                preparedStatement.executeUpdate();



                                listesDesReservations.add(r1);
                                listesDeChambres.get(numeroChambre).setStatut(true);
                                System.out.println("Réservation ajoutée !\n");
                                break;
                            }
                            System.out.println("\nLe nombre de lits ne correspond pas à la chambre !\n");
                            break;
                        }

                        if (listesDeChambres.get(j).isStatut() && listesDeChambres.get(j).getNumero() == numeroChambre) {
                            System.out.println("\nLa chambre est déjà occupée!\n");
                            break;
                        }

                        if (listesDeChambres.size() <= j + 1) {
                            System.out.println(listesDeChambres.get(j).isStatut());
                            System.out.println("\nLe numéro de chambre n'existe pas !\n");
                        }
                    }
                    break;
                }
                if (clients.size() <= i + 1) {
                    System.out.println("\nLe nom n'existe pas !\n");
                }
            }

        }
        public static ArrayList<Chambre> viewChambres() throws SQLException {
            ArrayList<Chambre> chambreListe = new ArrayList<>();
            String requestStudent = "SELECT * FROM chambre ";
            Statement statementStudent = connection.createStatement();
            ResultSet resultSetChambre = statementStudent.executeQuery(requestStudent);
            while (resultSetChambre.next()) {
                Chambre chambre = new Chambre(resultSetChambre.getInt("id"), resultSetChambre.getBoolean("statut"), resultSetChambre.getInt("nb_lits"), resultSetChambre.getInt("tarif") );
                System.out.println(chambre.getNumero() + " , " + chambre.isStatut() +
                        " , " + chambre.getNombreLits() + " , " + chambre.getTarif());
                System.out.println(resultSetChambre.getInt("id") + " , " + resultSetChambre.getBoolean("statut") +
                        " , " + resultSetChambre.getInt("nb_lits") +
                        " , " + resultSetChambre.getInt("tarif"));
                chambreListe.add(chambre);
            }
            return chambreListe;
        }

    public static ArrayList<Client> viewClient() throws SQLException {
        ArrayList<Client> chambreClient = new ArrayList<>();
        String requestStudent = "SELECT * FROM client ";
        Statement statementStudent = connection.createStatement();
        ResultSet resultSetClient = statementStudent.executeQuery(requestStudent);
        while (resultSetClient.next()) {
            Client client = new Client(resultSetClient.getInt("id"), resultSetClient.getString("first_name"), resultSetClient.getString("last_name"), resultSetClient.getString("nb_phone") );
            System.out.println(client.getId() + " , " + client.getNom() +
                    " , " + client.getPrenom() + " , " + client.getNumeroTelephone());
            System.out.println(resultSetClient.getInt("id") + " , " + resultSetClient.getString("first_name") +
                    " , " + resultSetClient.getString("last_name") +
                    " , " + resultSetClient.getInt("nb_phone"));
            chambreClient.add(client);
        }
        return chambreClient;
    }

    public static ArrayList<Reservation> viewReservation() throws SQLException {
        listesDeChambres = viewChambres();
        clients = viewClient();
        ArrayList<Reservation> reservationTab = new ArrayList<>();
        String requestStudent = "SELECT * FROM reservation ";
        Statement statementStudent = connection.createStatement();
        ResultSet resultSetReservation = statementStudent.executeQuery(requestStudent);
        while (resultSetReservation.next()) {
            Reservation reservation = new Reservation (resultSetReservation.getInt("id"), resultSetReservation.getInt("nb_reservation"), listesDeChambres.get(resultSetReservation.getInt("ch_id")), clients.get(resultSetReservation.getInt("cl_id")), resultSetReservation.getBoolean("statut") );
            System.out.println(reservation.getId() + " , " + reservation.getNb_reservation() +
                    " , " + reservation.getChambre().getNumero() + " , " + reservation.getClient().getNom());
            System.out.println(resultSetReservation.getInt("id") + " , " + resultSetReservation.getInt("nb_reservation") +
                    " , " + resultSetReservation.getInt("ch_id") +
                    " , " + resultSetReservation.getInt("cl_id") +
                            " , " + resultSetReservation.getBoolean("statut")
                    );
            reservationTab.add(reservation);
        }
        return reservationTab;
    }

}
