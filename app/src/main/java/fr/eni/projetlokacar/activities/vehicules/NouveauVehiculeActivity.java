package fr.eni.projetlokacar.activities.vehicules;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;

public class NouveauVehiculeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_vehicule);
    }
}
