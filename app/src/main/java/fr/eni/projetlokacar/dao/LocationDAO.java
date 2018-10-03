package fr.eni.projetlokacar.dao;

import android.arch.persistence.room.Dao;

import fr.eni.projetlokacar.bo.Location;

@Dao
public interface LocationDAO extends BaseDAO<Location> {
}
