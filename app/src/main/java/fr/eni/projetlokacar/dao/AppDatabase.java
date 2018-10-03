package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fr.eni.projetlokacar.bo.Categorie;
import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.bo.Location;
import fr.eni.projetlokacar.bo.Vehicule;

@Database(entities = {Client.class, Vehicule.class, Categorie.class, Location.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ClientDAO getClientDao();
    public abstract VehiculeDAO getVehiculeDAO();
    public abstract CategorieDAO getCategorieDAO();
    public abstract LocationDAO getLocationDAO();

    public abstract VehiculeReactiveDAO getVehiculeReactiveDAO();

}
