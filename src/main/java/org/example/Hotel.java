package org.example;

import java.util.ArrayList;

public class Hotel {
    private Client clients = new ArrayList<Client>();
    private  ArrayList<Chambre> listesDeChambres = new ArrayList<Chambre>();
    private ArrayList<Reservation> listeDeReservations = new ArrayList<Reservation>();


    public Hotel() {

    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(Client client) {
        this.clients = client;
    }

    public ArrayList<Chambre> getListesDeChambres() {
        return listesDeChambres;
    }

    public void setListesDeChambres(ArrayList<Chambre> listesDeChambres) {
        this.listesDeChambres = listesDeChambres;
    }

    public ArrayList<Reservation> getListeDeReservations() {
        return listeDeReservations;
    }

    public void setListeDeReservations(ArrayList<Reservation> listeDeReservations) {
        this.listeDeReservations = listeDeReservations;
    }
}
