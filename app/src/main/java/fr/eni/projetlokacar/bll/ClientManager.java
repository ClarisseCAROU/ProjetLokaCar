package fr.eni.projetlokacar.bll;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executors;

import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.services.ClientIntentService;

public class ClientManager {

    private static final String TAG = "MANAGER_CLIENT";
    private Context context;

    public ClientManager(Context context) {
        this.context = context;
    }

    public void selectAll() {
        Log.i(TAG, "selectAll");
        Intent intent = new Intent(context, ClientIntentService.class);
        context.startService(intent);
    }

    public void insert(Client client) {
        Log.i(TAG, "insert");
        Executors.newSingleThreadExecutor().execute(() -> DbHelper.getDataBase(context).getClientDao().insert(client));
        Log.i(TAG, "insert bien effectué");
    }
}
