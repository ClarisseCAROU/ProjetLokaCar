package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.projetlokacar.bo.Vehicule;

public interface VehiculeDAO extends BaseDAO<Vehicule>{

    @Query("SELECT * FROM VEHICULES")
    List<Vehicule> selectAll();

    @Query("SELECT * FROM VEHICULES WHERE immatriculation=:immatriculation")
    List<Vehicule> selectByImmatriculation(String immatriculation);

}
