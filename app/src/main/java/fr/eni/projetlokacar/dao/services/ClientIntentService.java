package fr.eni.projetlokacar.dao.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.dao.DbHelper;


public class ClientIntentService extends IntentService {

    private static final String TAG = "SERVICE_CLIENT";

    public ClientIntentService() {
        super("ClientIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.i(TAG,"Début traitement service client");

        List<Client> clients = DbHelper.getDataBase(ClientIntentService.this).getClientDao().selectAll();
        Log.i(TAG,clients.toString());
        Intent intentMessage = new Intent("fr.eni.select.clients");
        intentMessage.putParcelableArrayListExtra("clients", (ArrayList<? extends Parcelable>) clients);
        this.sendBroadcast(intentMessage);

        Log.i(TAG,"Fin traitement service client");
    }
}
