package fr.eni.projetlokacar.activities.vehicules;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.adapters.VehiculeAdapter;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.VehiculeReactiveDAO;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ListeVehiculesActivity extends BaseActivity implements SearchView.OnQueryTextListener{

    private RecyclerView recyclerView;
    private VehiculeAdapter vehiculeAdapter;
    private CompositeDisposable subscriptions;
    private VehiculeReactiveDAO vehiculeDAO;
    private TextView tvFiltre;
    private EditText recherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicules);
        subscriptions = new CompositeDisposable();
        vehiculeDAO = DbHelper.getDataBase(getApplication()).getVehiculeReactiveDAO();
        tvFiltre = findViewById(R.id.tv_filtre_vehicules);


        /*Spinner status = findViewById(R.id.sp_status);


        ArrayAdapter<CharSequence> statutAdapter = ArrayAdapter.createFromResource(this,
                R.array.status, android.R.layout.simple_spinner_item);
        statutAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statutAdapter);*/


        initRecyclerView();

        subscriptions.add(
                vehiculeDAO.selectAll()
                        .observeOn(AndroidSchedulers.mainThread())
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

    public void onFiltresClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.filter_vehicules)
                .setItems(R.array.status, (dialog, index) -> tvFiltre.setText(getResources().getStringArray(R.array.status)[index]));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //Log.d(TAG, "onQueryTextSubmit: " + query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
