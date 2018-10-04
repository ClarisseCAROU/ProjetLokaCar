package fr.eni.projetlokacar.activities.location;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.bo.Location;
import fr.eni.projetlokacar.bo.Vehicule;
import fr.eni.projetlokacar.dao.ClientRxDAO;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.LocationRxDAO;
import fr.eni.projetlokacar.dao.VehiculeRxDAO;
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
                return rechercherLocation(v.getText().toString().toUpperCase());
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        subscriptions.add(
                locationDAO.selectByImmatriculation(recherche)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(l -> {
                            location = l;
                            rechercherVehicule(l.getVehiculeId());
                            rechercherClient(l.getClientId());
                            this.tvDateDebut.setText(simpleDateFormat.format(location.getDateDepart()));
                            this.tvDateRetour.setText(simpleDateFormat.format(location.getDateRetour()));
                            Log.i("test", location.getDateRetour().toString());
                        }));

        return true;
    }

    private boolean rechercherVehicule(int vehiculeId) {
        Log.i(TAG, "recherche véhicule");

        subscriptions.add(
                vehiculeDAO.selectById(vehiculeId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        // On applique une fonction
                        .subscribe(v -> {
                            vehicule = v;
                            this.tvModel.setText(vehicule.getModele());
                            this.tvMarque.setText(vehicule.getMarque());
                            this.tvImmatriculation.setText(vehicule.getImmatriculation());
                            this.calculerPrix(v.getTarifJournalier(), location.getDateDepart(), location.getDateRetour());
                        }));
        return true;
    }

    private boolean rechercherClient(int clientId) {
        Log.i(TAG, "recherche client");
        subscriptions.add(
                clientDAO.selectById(clientId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        // On applique une fonction
                        .subscribe(c -> {
                            client = c;
                            this.tvClient.setText(client.getPrenom() + " " + client.getNom());
                        }));
        return true;
    }

//    private void remplirTextView() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//
////        this.tvPrix.setText();
//    }


    private void calculerPrix(double prix, Date debut, Date retour) {
        long duree;

        long diffInMillies = Math.abs(retour.getTime() - debut.getTime());
        duree = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        prix *= duree;

        this.tvPrix.setText(String.format("%s €", String.valueOf(prix)));
    }

}
