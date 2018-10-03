package fr.eni.projetlokacar.activities.location;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.activities.vehicules.ListeVehiculesActivity;
import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.bo.Vehicule;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.LocationDAO;
import fr.eni.projetlokacar.fragments.DatePickerFragment;

public class NouvelleLocationActivity extends BaseActivity implements DatePickerFragment.DateSetListener {

    public static final int CHOIX_VEHICULE = 666;

    private LocationDAO locationDAO;
    private Vehicule vehicule;
    private Client client;

    private TextView dateDebut;
    private TextView dateFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_location);

        locationDAO = DbHelper.getDataBase(getApplication()).getLocationDAO();

        dateDebut = findViewById(R.id.date_debut);
        dateFin = findViewById(R.id.date_fin);
        dateDebut.setShowSoftInputOnFocus(false);
        dateFin.setShowSoftInputOnFocus(false);

        startActivityForResult(new Intent(this, ListeVehiculesActivity.class), CHOIX_VEHICULE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CHOIX_VEHICULE) {
            if (resultCode == RESULT_OK) {
                vehicule = data.getParcelableExtra("vehicule");
                Log.d(TAG, "onActivityResult: " + vehicule.getModele());
            }
        }

    }

    public void showDatePickerDialog(View view) {

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setListener((year, month, day) -> ((EditText)view).setText(day + "/" + month + "/" + year));
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    @Override
    public void onDateSet(int year, int month, int day) {
        dateDebut.setText(day + "/" + month + "/" + year);
    }
}
