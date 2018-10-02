package fr.eni.projetlokacar.activities.vehicules;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.adapters.VehiculeAdapter;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.VehiculeReactiveDAO;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ListeVehiculesActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private VehiculeAdapter vehiculeAdapter;
    private CompositeDisposable subscriptions;
    private VehiculeReactiveDAO vehiculeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicules);
        subscriptions = new CompositeDisposable();

        Spinner status = findViewById(R.id.sp_status);
        vehiculeDAO = DbHelper.getDataBase(getApplication()).getVehiculeReactiveDAO();

        ArrayAdapter<CharSequence> statutAdapter = ArrayAdapter.createFromResource(this,
                R.array.status, android.R.layout.simple_spinner_item);
        statutAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statutAdapter);

        initRecyclerView();

        subscriptions.add(
                vehiculeDAO.selectAll()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(vehicules -> vehiculeAdapter.addVehicules(vehicules))
        );

    }

    private void initRecyclerView() {

        recyclerView = findViewById(R.id.rv_vehicules);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vehiculeAdapter = new VehiculeAdapter(
                v -> Toast.makeText(this, v.getModele(), Toast.LENGTH_SHORT).show()
        );
        recyclerView.setAdapter(vehiculeAdapter);
    }
}
