package fr.eni.projetlokacar.bll;

import android.content.Context;
import android.util.Log;
import android.content.Intent;
import java.util.List;
import java.util.concurrent.Executors;
import fr.eni.projetlokacar.bo.Vehicule;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.services.VehiculeIntentService;


public class VehiculeManager {


    private static final String TAG = "MANAGER_VEHICULE";
    private Context context;

    public VehiculeManager(Context context) {
        this.context = context;
    }

    public void selectAll() {
        Log.i(TAG, "selectAll");
        Intent intent = new Intent(context, VehiculeIntentService.class);
        context.startService(intent);
    }

    public void insert(Vehicule vehicule) {
        Log.i(TAG, "insert");
        Executors.newSingleThreadExecutor().execute(() -> DbHelper.getDataBase(context).getVehiculeDAO().insert(vehicule));
        Log.i(TAG, "insert bien effectué");
    }
}
