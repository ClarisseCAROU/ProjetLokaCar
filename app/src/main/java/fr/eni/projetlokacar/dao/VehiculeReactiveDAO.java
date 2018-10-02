package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.projetlokacar.bo.Vehicule;
import io.reactivex.Single;

@Dao
public interface VehiculeReactiveDAO {

    @Query("SELECT * FROM VEHICULES")
    Single<List<Vehicule>> selectAll();

}
