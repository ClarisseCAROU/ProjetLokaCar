package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fr.eni.projetlokacar.bo.Client;

@Database(entities = {Client.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ClientDAO getClientDao();
    public abstract VehiculeDAO getVehiculeDAO();



}
