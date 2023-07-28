package org.example;

import java.util.ArrayList;

public class Reservation {
    private int id;
    private  boolean statut = false;
    private ArrayList<Chambre> chambres = new ArrayList<Chambre>();
    private Client client;

    public Reservation(int id, ArrayList<Chambre> chambres, Client client ) {
        this.id = id;
        this.chambres = chambres;
        this.client = client;
    }
}
