package fr.eni.projetlokacar.bo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import fr.eni.projetlokacar.dao.converters.CategorieConverter;

@Entity(tableName = "vehicules", indices = {@Index("immatriculation")})
public class Vehicule {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String immatriculation;
    private String couleur;
    private double tarifJournalier;
    private String modele;
    private String marque;
    @TypeConverters(CategorieConverter.class)
    private Categorie categorie;

    public Vehicule() {
    }

    public Vehicule(String immatriculation, String couleur, double tarifJournalier, String modele, String marque, Categorie categorie) {
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.tarifJournalier = tarifJournalier;
        this.modele = modele;
        this.marque = marque;
        this.categorie = categorie;
    }

    public Vehicule(String immatriculation, String couleur, double tarifJournalier, String modele, String marque, int categorie) {
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.tarifJournalier = tarifJournalier;
        this.modele = modele;
        this.marque = marque;
        this.categorie = CategorieConverter.toCategorie(categorie);
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

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public double getTarifJournalier() {
        return tarifJournalier;
    }

    public void setTarifJournalier(double tarifJournalier) {
        this.tarifJournalier = tarifJournalier;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
                "}\n";
    }

    public enum Categorie{

        Berline(1),
        Citadine(2),
        Monospace(3),
        Utilitaire(4);

        private int id;

        Categorie(int id) {
            this.id = id;
        }

        public int getId(){
            return id;
        }
    }
}


