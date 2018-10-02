package fr.eni.projetlokacar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.idescout.sql.SqlScoutServer;

import java.util.List;
import java.util.concurrent.Executors;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.clients.GestionClientsActivity;
import fr.eni.projetlokacar.activities.location.NouvelleLocationActivity;
import fr.eni.projetlokacar.activities.location.RetourLocationActivity;
import fr.eni.projetlokacar.activities.vehicules.GestionVehiculesActivity;
import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.dao.DbHelper;

public class AccueilActivity extends AppCompatActivity {

    private SqlScoutServer sqlScoutServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        sqlScoutServer = SqlScoutServer.create(this, getPackageName());

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Client> clients1 = DbHelper.getDataBase(AccueilActivity.this).getClientDao().selectAll();
                Log.i("DEV", "onPostExecute: " + clients1.toString());
            }
        });

    }

    public void onClickMenu(View view) {

        Intent intent = null;

        switch (view.getId()){

            case R.id.btn_nouvelle_location :
                intent = new Intent(this, NouvelleLocationActivity.class);
                break;

            case R.id.btn_retour_location :
                intent = new Intent(this, RetourLocationActivity.class);
                break;

            case R.id.btn_gestion_vehicules :
                intent = new Intent(this, GestionVehiculesActivity.class);
                break;

            case R.id.btn_gestion_clients :
                intent = new Intent(this, GestionClientsActivity.class);
                break;

            case R.id.btn_statistiques :
                intent = new Intent(this, StatistiquesActivity.class);
                break;

        }

        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlScoutServer.destroy();
    }
}
