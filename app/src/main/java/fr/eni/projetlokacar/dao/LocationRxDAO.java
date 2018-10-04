package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import fr.eni.projetlokacar.bo.Location;
import io.reactivex.Single;

@Dao
public interface LocationRxDAO extends BaseDAO<Location> {

    @Query("SELECT l.clientId, l.vehiculeId, l.dateDepart, l.dateRetour " +
            "FROM LOCATIONS l JOIN VEHICULES v ON l.vehiculeId = v.id " +
            "WHERE v.immatriculation=:immatriculation AND l.dateCloture IS NULL")
    Single<Location> selectByImmatriculation(String immatriculation);

    //, l.dateCloture, l.commentaire


    /*
        @Query("SELECT l.clientId, l.vehiculeId, l.dateDepart, l.dateRetour " +
            "FROM LOCATIONS l JOIN VEHICULES v ON l.vehiculeId = v.id " +
            "WHERE v.immatriculation=:immatriculation")


            AND l.dateCloture IS NULL
     */


/*    @Query("SELECT * FROM LOCATIONS l JOIN VEHICULES v ON l.vehiculeId = v.id WHERE v.immatriculation=:immatriculation")
    Single<Vehicule> selectByImmatriculation(String immatriculation);*/
}
