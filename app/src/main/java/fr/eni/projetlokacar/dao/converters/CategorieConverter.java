package fr.eni.projetlokacar.dao.converters;

import android.arch.persistence.room.TypeConverter;

import fr.eni.projetlokacar.bo.Vehicule.Categorie;

import static fr.eni.projetlokacar.bo.Vehicule.Categorie.Berline;
import static fr.eni.projetlokacar.bo.Vehicule.Categorie.Citadine;
import static fr.eni.projetlokacar.bo.Vehicule.Categorie.Monospace;
import static fr.eni.projetlokacar.bo.Vehicule.Categorie.Utilitaire;

public class CategorieConverter {

    @TypeConverter
    public static Categorie toCategorie(int id) {
        if (id == Berline.getId()) {
            return Berline;
        } else if (id == Citadine.getId()) {
            return Citadine;
        } else if (id == Monospace.getId()) {
            return Monospace;
        } else if (id == Utilitaire.getId()) {
            return Utilitaire;
        } else {
            throw new IllegalArgumentException("Could not recognize status");
        }
    }

    @TypeConverter
    public static int toInt(Categorie categorie){
        return categorie.getId();
    }

}
