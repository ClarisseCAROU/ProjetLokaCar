package fr.eni.projetlokacar.dao.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetlokacar.bo.Vehicule;
import fr.eni.projetlokacar.dao.DbHelper;

public class VehiculeIntentService extends IntentService{


    private static final String TAG = "SERVICE_VEHICULE";

    public VehiculeIntentService() {
        super("VehiculeIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.i(TAG,"Début traitement service client");

        List<Vehicule> vehicules = DbHelper.getDataBase(VehiculeIntentService.this).getVehiculeDAO().selectAll();
        Log.i(TAG,vehicules.toString());
        Intent intentMessage = new Intent("fr.eni.select.vehicules");
        intentMessage.putParcelableArrayListExtra("vehicules", (ArrayList<? extends Parcelable>) vehicules);
        this.sendBroadcast(intentMessage);

        Log.i(TAG,"Fin traitement service vehicule");
    }
}

