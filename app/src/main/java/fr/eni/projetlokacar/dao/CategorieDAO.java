package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.projetlokacar.bo.Categorie;

@Dao
public interface CategorieDAO extends BaseDAO<Categorie> {

    @Query("SELECT * FROM categories")
    List<Categorie> selectAll();

}
