package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.projetlokacar.bo.Client;

@Dao
public interface ClientDAO extends BaseDAO<Client> {

    @Query("SELECT * FROM CLIENTS")
    List<Client> selectAll();

    @Query("SELECT * FROM CLIENTS WHERE nom=:nom")
    List<Client> selectByNom(String nom);

}
