package org.example;

import java.util.ArrayList;

public class Hotel {

    private String nom;
    static ArrayList<Client> clients = new ArrayList<Client>();
    static ArrayList<Chambre> listesDeChambres = new ArrayList<Chambre>();
    private ArrayList<Reservation> listeDeReservations = new ArrayList<Reservation>();

    static String[][] tabClient = new String[][]{

            {"Timothy", "Henry", "05 86 41 53 55"},
            {"Blythe", "Farley", "06 32 23 18 37"},
            {"Brandon", "Mcleod", "06 30 46 38 15"},
            {"Maxwell", "Vance", "05 68 19 82 13"},
            {"Gregory", "Acosta", "04 12 08 42 62"},
            {"Inga", "Preston", "07 21 36 36 03"},
            {"Hannah", "Villarreal", "04 87 72 73 77"},
            {"Arthur", "Rosa", "04 41 21 82 95"},
            {"Abbot", "Tillman", "06 63 33 22 35"},
            {"Upton", "Holmes", "02 48 34 16 21"},
            {"Linus", "Moon", "04 22 56 45 74"}
    };

    public Hotel(String nom) {

        this.nom = nom;

        int nombreAleatoireClient = 8 + (int)(Math.random() * ((11 - 8) + 1));;
        for (int i = 0; i<nombreAleatoireClient ; i++){
        Client c1 = new Client(i+1, tabClient[i][1], tabClient[i][0], tabClient[i][2]);
        clients.add(c1);
        };

        int nombreAleatoireChambre = 8 + (int)(Math.random() * ((16 - 8) + 1));
        for (int i = 0; i<nombreAleatoireChambre; i++){
            int nombreAleatoireLits = 1 + (int)(Math.random() * ((2 - 1) + 1));
            if (nombreAleatoireLits == 2) {
                Chambre ch1 = new Chambre(i, false, nombreAleatoireLits, 16);
                listesDeChambres.add(ch1);
            }
            else {
                Chambre ch1 = new Chambre(i, false, nombreAleatoireLits, 8);
                listesDeChambres.add(ch1);
            }
        }




    }

    @Override
    public String toString(){
        String result = "";
        for (Client client : clients) {
            result += client.getId() + " " + client.getNom()+ " " + client.getPrenom() + " " + client.getNumeroTelephone() + "\n";
        }
        return result;
    };

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
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
