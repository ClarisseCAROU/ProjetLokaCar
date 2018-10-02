package fr.eni.projetlokacar.bll;

import android.content.Context;
import android.content.Intent;

import java.util.List;

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
//        return DbHelper.getDataBase(context).getClientDao().selectAll();
        Intent intent = new Intent(context, ClientIntentService.class);
        context.startService(intent);
    }
}
