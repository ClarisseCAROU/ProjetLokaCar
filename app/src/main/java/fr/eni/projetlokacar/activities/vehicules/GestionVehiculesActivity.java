package fr.eni.projetlokacar.activities.vehicules;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.AccueilActivity;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.activities.StatistiquesActivity;
import fr.eni.projetlokacar.activities.clients.GestionClientsActivity;
import fr.eni.projetlokacar.activities.clients.NouveauClientActivity;
import fr.eni.projetlokacar.activities.location.NouvelleLocationActivity;
import fr.eni.projetlokacar.activities.location.RetourLocationActivity;

public class GestionVehiculesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_vehicules);


/*
        Intent intent = new Intent(this, NouveauVehiculeActivity.class);
        startActivity(intent);
        */


        }

    public void onClickMenu(View view) {

        Intent intent = null;

        intent = new Intent(this, NouveauVehiculeActivity.class);
        startActivity(intent);

    }



    }

