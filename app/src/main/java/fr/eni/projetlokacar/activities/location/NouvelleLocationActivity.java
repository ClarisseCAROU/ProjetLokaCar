package fr.eni.projetlokacar.activities.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.activities.clients.GestionClientsActivity;
import fr.eni.projetlokacar.adapters.VehiculeAdapter;
import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.bo.Location;
import fr.eni.projetlokacar.bo.Vehicule;
import fr.eni.projetlokacar.dao.ClientRxDAO;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.LocationDAO;
import fr.eni.projetlokacar.dao.VehiculeRxDAO;
import fr.eni.projetlokacar.fragments.DatePickerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NouvelleLocationActivity extends BaseActivity {

    public static final int CHOIX_VEHICULE = 1;
    private static final int CHOIX_CLIENT = 2;

    private LocationDAO locationDAO;
    private VehiculeRxDAO vehiculeDAO;
    private ClientRxDAO clientDAO;

    private Vehicule vehicule;
    private Client client;

    private RecyclerView recyclerView;
    private VehiculeAdapter vehiculeAdapter;
    private CompositeDisposable subscriptions;
    private TextView tvPrix;
    private TextView tvDateDebut;
    private TextView tvDateFin;
    private Date debut;
    private Date fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_location);

        subscriptions = new CompositeDisposable();
        locationDAO = DbHelper.getDataBase(getApplication()).getLocationDAO();
        vehiculeDAO = DbHelper.getDataBase(getApplication()).getVehiculeRxDAO();
        clientDAO = DbHelper.getDataBase(getApplication()).getClientRxDAO();

        tvDateDebut = findViewById(R.id.date_debut);
        tvDateDebut.setShowSoftInputOnFocus(false);

        tvDateFin = findViewById(R.id.date_fin);
        tvDateFin.setShowSoftInputOnFocus(false);

        tvPrix = findViewById(R.id.tv_prix);

        initRecyclerView();

        // On charge les vehicules en asynchrone
        subscriptions.add(
                vehiculeDAO.selectDsiponibles()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        // On les ajoute à l'adapter quand ils sont chargés
                        .subscribe(v -> vehiculeAdapter.addVehicules(v))
        );

        startActivityForResult(new Intent(this, GestionClientsActivity.class), CHOIX_CLIENT);
    }

    private void initRecyclerView() {

        recyclerView = findViewById(R.id.rv_choix_vehicule);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vehiculeAdapter = new VehiculeAdapter(v -> {
            vehicule = v;
            updatePrix();
        });

        recyclerView.setAdapter(vehiculeAdapter);
    }

    private void updatePrix() {

        if (!tvDateDebut.getText().toString().isEmpty() && !tvDateFin.getText().toString().isEmpty() && vehicule != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            double prix = vehicule.getTarifJournalier();
            long duree = 0;

            try {
                debut = simpleDateFormat.parse(tvDateDebut.getText().toString());
                fin = simpleDateFormat.parse(tvDateFin.getText().toString());
                long diffInMillies = Math.abs(fin.getTime() - debut.getTime());

                duree = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                prix *= duree;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            tvPrix.setText(String.format("%s €", String.valueOf(prix)));
        } else {
            tvPrix.setText("");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CHOIX_CLIENT && resultCode == RESULT_OK) {
            client = data.getParcelableExtra("client");
        }

    }

    public void showDatePickerDialog(View view) {

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setListener((year, month, day) -> {
            ((EditText) view).setText(day + "/" + month + "/" + year);
            updatePrix();
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void faireEtatDesLieux(View view) {

        Location location = new Location(client.getId(), vehicule.getId(), debut, fin);

        startActivity(new Intent(this, EtatLieuxDepartActivity.class)
                .putExtra("location", location));
    }
}
