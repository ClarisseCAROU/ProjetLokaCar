package fr.eni.projetlokacar.bo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

@Entity(tableName = "vehicules",
        foreignKeys = {
                @ForeignKey(entity = Modele.class,
                            parentColumns = "id",
                            childColumns = "modeleId"),
                @ForeignKey(entity = Categorie.class,
                            parentColumns = "id",
                            childColumns = "categorieId")
        }
)
public class Vehicule {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String immatriculation;
    private String couleur;
    private double tarifJournalier;
    private String modele;
    private String marque;
    private int categorieId;

    @Ignore
    private Categorie categorie;
    @Relation(entity = PhotoVehicule.class, parentColumn = "id", entityColumn = "vehiculeId")
    private List<PhotoVehicule> photoVehicules;

    public Vehicule() {
    }

    public Vehicule(String immatriculation, String couleur, double tarifJournalier, String modele, Categorie categorie, List<PhotoVehicule> photoVehicules) {
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.tarifJournalier = tarifJournalier;
        this.modele = modele;
        this.categorie = categorie;
        this.photoVehicules = photoVehicules;
    }

    public Vehicule(int id, String immatriculation, String couleur, double tarifJournalier, String modele, Categorie categorie, List<PhotoVehicule> photoVehicules) {
        this(immatriculation, couleur, tarifJournalier, modele, categorie, photoVehicules);
        this.id = id;
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

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
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
                "}\n";
    }
}


