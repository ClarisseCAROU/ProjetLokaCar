package fr.eni.projetlokacar.bo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "categories")
public class Categorie {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String libelle;

    public Categorie() {
    }

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public Categorie(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
