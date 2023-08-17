package org.example;

import java.util.ArrayList;

public class Reservation {
    private int id;

    private int nb_reservation;
    private  boolean statut = false;
    static ArrayList<Reservation> listesDesReservations = new ArrayList<Reservation>();

    private Client client;

    private Chambre chambre;

    public Reservation(int id,int nb_reservation, Chambre chambre, Client client, boolean statut ) {

        this.id = id;
        this.nb_reservation = nb_reservation;
        this.chambre = chambre;
        this.client = client;
        this.statut = statut;
    }

    @Override
    public String toString(){
            return ("\n=== Client === : \n" + getClient().getNom() + " " + getClient().getPrenom() + " " + getClient().getNumeroTelephone() + "\n" + "=== Chambre === : \n" + "Numero de chambre : " + chambre.getNumero() + " \nNombre de lit : " + chambre.getNombreLits() + "\nStatut : " + getChambre().isStatut() +  "\n");

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Reservation> getListesDeChambresReserve() {
        return listesDesReservations;
    }

    public static void setListesDeChambresReserve(ArrayList<Reservation> listesDeChambresReserve) {
        Reservation.listesDesReservations = listesDeChambresReserve;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public int getNb_reservation() {
        return nb_reservation;
    }

    public void setNb_reservation(int nb_reservation) {
        this.nb_reservation = nb_reservation;
    }
}
