package fr.eni.projetlokacar.dao.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(long dateLong) {
        return new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date) {

        if(date == null){
            return null;
        } else {
            return date.getTime();
        }
    }
}