package fr.eni.projetlokacar.bo;

import java.util.List;

public class Vehicule {
    private int id;
    private String immatriculation;
    private String couleur;
    private double tarifJournalier;
    private Modele modele;
    private Categorie categorie;
    private List<PhotoVehicule> photoVehicules;

    public Vehicule() {
    }

    public Vehicule(String immatriculation, String couleur, double tarifJournalier, Modele modele, Categorie categorie, List<PhotoVehicule> photoVehicules) {
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.tarifJournalier = tarifJournalier;
        this.modele = modele;
        this.categorie = categorie;
        this.photoVehicules = photoVehicules;
    }

    public Vehicule(int id, String immatriculation, String couleur, double tarifJournalier, Modele modele, Categorie categorie, List<PhotoVehicule> photoVehicules) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.tarifJournalier = tarifJournalier;
        this.modele = modele;
        this.categorie = categorie;
        this.photoVehicules = photoVehicules;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public double getTarifJournalier() {
        return tarifJournalier;
    }

    public void setTarifJournalier(double tarifJournalier) {
        this.tarifJournalier = tarifJournalier;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<PhotoVehicule> getPhotoVehicules() {
        return photoVehicules;
    }

    public void setPhotoVehicules(List<PhotoVehicule> photoVehicules) {
        this.photoVehicules = photoVehicules;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", immatriculation='" + immatriculation + '\'' +
                ", couleur='" + couleur + '\'' +
                ", tarifJournalier=" + tarifJournalier +
                ", modele=" + modele +
                ", categorie=" + categorie +
                ", photoVehicules=" + photoVehicules +
                '}';
    }
}


