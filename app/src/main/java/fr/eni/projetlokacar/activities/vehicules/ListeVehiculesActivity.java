package fr.eni.projetlokacar.activities.vehicules;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.activities.clients.NouveauClientActivity;
import fr.eni.projetlokacar.activities.location.NouvelleLocationActivity;
import fr.eni.projetlokacar.adapters.VehiculeAdapter;
import fr.eni.projetlokacar.bo.Vehicule;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.VehiculeRxDAO;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ListeVehiculesActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private VehiculeAdapter vehiculeAdapter;
    private CompositeDisposable subscriptions;
    private VehiculeRxDAO vehiculeDAO;
    private TextView tvFiltre;
    private FloatingActionButton btnFiltre;
    private EditText recherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicules);

        Single<List<Vehicule>> vehicules;

        subscriptions = new CompositeDisposable();
        vehiculeDAO = DbHelper.getDataBase(getApplication()).getVehiculeRxDAO();
        tvFiltre = findViewById(R.id.tv_filtre_vehicules);
        btnFiltre = findViewById(R.id.btn_filtre_vehicules);
        recherche = findViewById(R.id.recherche_vehicule);
        recherche.setOnEditorActionListener((textView, actionId, event) -> rechercherVehicule(textView.getText().toString()));

        int requestCode = getIntent().getIntExtra("requestCode", 0);

        switch (requestCode) {
            case NouvelleLocationActivity.CHOIX_VEHICULE:
                vehicules = vehiculeDAO.selectDsiponibles();
                ((ViewGroup) btnFiltre.getParent()).removeView(btnFiltre);
                ((ViewGroup) tvFiltre.getParent()).removeView(tvFiltre);
                break;
            default:
                vehicules = vehiculeDAO.selectAll();
                break;
        }

        initRecyclerView();

        subscriptions.add(
                vehicules
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(v -> vehiculeAdapter.addVehicules(v))
        );

    }

    private boolean rechercherVehicule(String s) {

        Log.d(TAG, s);

        return true;
    }

    private void initRecyclerView() {

        recyclerView = findViewById(R.id.rv_vehicules);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vehiculeAdapter = new VehiculeAdapter(
                v -> {
                    setResult(Activity.RESULT_OK, new Intent().putExtra("vehicule", v));
                    finish();
                }
        );
        recyclerView.setAdapter(vehiculeAdapter);
    }

    public void onFiltresClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.filter_vehicules)
                .setItems(R.array.status, (dialog, index) -> filtrerVehicules(index));
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void filtrerVehicules(int index) {

        String[] filtres = getResources().getStringArray(R.array.status);
        String filtre = filtres[index];
        Single<List<Vehicule>> vehicules;

        switch (index) {
            case 1:
                vehicules = vehiculeDAO.selectDsiponibles();
                break;
            case 2:
                vehicules = vehiculeDAO.selectNonDisponibles();
                break;
            default:
                vehicules = vehiculeDAO.selectAll();
                break;
        }

        tvFiltre.setText(filtre);
        subscriptions.add(
                vehicules
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(v -> vehiculeAdapter.addVehicules(v))
        );

    }

    public void creerVehicule(View view) {
        Intent intent = new Intent(this, NouveauVehiculeActivity.class);
        startActivity(intent);
    }


}
