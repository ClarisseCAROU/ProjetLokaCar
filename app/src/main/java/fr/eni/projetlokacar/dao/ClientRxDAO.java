package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import fr.eni.projetlokacar.bo.Client;
import io.reactivex.Single;

@Dao
public interface ClientRxDAO {

    @Query("SELECT * FROM CLIENTS WHERE id=:id")
    Single<Client> selectById(int id);

}
