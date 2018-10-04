package fr.eni.projetlokacar.activities.location;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.bo.Location;
import fr.eni.projetlokacar.bo.Vehicule;
import fr.eni.projetlokacar.dao.ClientRxDAO;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.LocationRxDAO;
import fr.eni.projetlokacar.dao.VehiculeRxDAO;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RetourLocationActivity extends BaseActivity {

    private static final String TAG = "RETOUR_LOCATION";
    private EditText rechercherImmatriculation;
    private LocationRxDAO locationDAO;
    private VehiculeRxDAO vehiculeDAO;
    private ClientRxDAO clientDAO;
    private CompositeDisposable subscriptions;

    private TextView tvClient;
    private TextView tvDateDebut;
    private TextView tvDateRetour;
    private TextView tvModel;
    private TextView tvMarque;
    private TextView tvImmatriculation;
    private TextView tvPrix;

    private Location location;
    private Vehicule vehicule;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retour_location);

        this.recuperationIdVue();
        this.locationDAO = DbHelper.getDataBase(this).getLocationRxDAO();
        this.vehiculeDAO = DbHelper.getDataBase(this).getVehiculeRxDAO();
        this.clientDAO = DbHelper.getDataBase(this).getClientRxDAO();
        this.subscriptions = new CompositeDisposable();

        rechercherImmatriculation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return rechercherLocation(v.getText().toString());
            }
        });

    }

    private void recuperationIdVue() {
        this.rechercherImmatriculation = this.findViewById(R.id.etRechercheImmatriculation);
        this.tvClient = this.findViewById(R.id.tvClient);
        this.tvDateDebut = this.findViewById(R.id.tvDateDebut);
        this.tvDateRetour = this.findViewById(R.id.tvDateRetour);
        this.tvModel = this.findViewById(R.id.tvModel);
        this.tvMarque = this.findViewById(R.id.tvMarque);
        this.tvImmatriculation = this.findViewById(R.id.tvImmatriculation);
        this.tvPrix = this.findViewById(R.id.tvPrix);
    }

    private boolean rechercherLocation(String recherche) {
        Log.i(TAG, recherche);

        subscriptions.add(
                locationDAO.selectByImmatriculation(recherche)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(l -> {
                            location = l;
                            rechercherVehicule(l.getVehiculeId());
                            rechercherClient(l.getClientId());
                        }));
        return true;
    }

    private boolean rechercherVehicule(int vehiculeId) {
        Log.i(TAG, "recherche véhicule");

        subscriptions.add(vehiculeDAO.selectById(vehiculeId).
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                // On applique une fonction
                .subscribe(v -> vehicule = v));

        //.subscribe(v -> rechercherVehicule(v.getVehiculeId())));

        return true;
    }

    private boolean rechercherClient(int clientId) {
        Log.i(TAG, "recherche client");
        subscriptions.add(clientDAO.selectById(clientId).
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                // On applique une fonction
                .subscribe(c -> {
                    client = c;
                    this.remplirTextView();
                }));
        //.subscribe(v -> rechercherVehicule(v.getVehiculeId())));

        return true;
    }

    private void remplirTextView() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        this.tvClient.setText(client.getPrenom() + " " + client.getNom());

        this.tvDateDebut.setText(simpleDateFormat.format(location.getDateDepart()));
        this.tvDateRetour.setText(simpleDateFormat.format(location.getDateRetour()));
        this.tvDateRetour.setText(simpleDateFormat.format(location.getDateRetour()));

        this.tvModel.setText(vehicule.getModele());
        this.tvMarque.setText(vehicule.getMarque());
        this.tvImmatriculation.setText(vehicule.getImmatriculation());
        this.tvImmatriculation.setText(vehicule.getImmatriculation());
//        this.tvPrix.setText();
    }


/*
    private double calculerPrix() {
        double prix = 0;



        return prix;
    }
*/


}
