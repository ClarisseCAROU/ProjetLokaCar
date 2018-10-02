package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BaseDAO<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(T ... entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T entity);

}
