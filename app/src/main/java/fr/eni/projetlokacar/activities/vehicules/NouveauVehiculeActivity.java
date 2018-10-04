package fr.eni.projetlokacar.activities.vehicules;

import android.arch.persistence.room.TypeConverters;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.bll.VehiculeManager;
import fr.eni.projetlokacar.bo.Vehicule;
import fr.eni.projetlokacar.dao.converters.CategorieConverter;

import android.widget.Toast;

public class NouveauVehiculeActivity extends BaseActivity {

    private EditText etImmatriculation;
    private EditText etMarque;
    private EditText etModele;
    private EditText etCategorie;
    private EditText etCouleur;
    private EditText etPrixParJour;
    private int id;
    private String immatriculation;
    private String couleur;
    private double tarifJournalier;
    private String modele;
    private String marque;
    @TypeConverters(CategorieConverter.class)
    private Vehicule.Categorie categorie;

    private Vehicule nouveauVehicule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_vehicule);


        etImmatriculation = this.findViewById(R.id.etImmatriculation);
        etMarque = this.findViewById(R.id.etMarque);
        etModele = this.findViewById(R.id.etModele);
        etCategorie = this.findViewById(R.id.etCategorie);
        etCouleur = this.findViewById(R.id.etCouleur);
        etPrixParJour = this.findViewById(R.id.etPrixParJour);


    }

    public void onCategorieClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.filter_vehicules)
                .setItems(
                        Vehicule.Categorie.getValues(),
                        (DialogInterface.OnClickListener) (dialog, index) -> etCategorie.setText(Vehicule.Categorie.values()[index].name())
                );
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void ajouterVehicule(View view) {


        if (verifierVehicule()) {
            nouveauVehicule = new Vehicule(immatriculation, couleur, tarifJournalier, modele, marque,
                    categorie);

            Log.i("Ajout véhicule", nouveauVehicule.toString());
            VehiculeManager vehiculeManager = new VehiculeManager(this);
            vehiculeManager.insert(nouveauVehicule);
            Toast.makeText(this, "Le véhicule immatriculé " + nouveauVehicule.getImmatriculation() + " a bien été créé.", Toast.LENGTH_SHORT).show();

        }
    }


        private boolean verifierVehicule(){

            boolean verifOk = true;

            immatriculation = etImmatriculation.getText().toString();
            marque = etMarque.getText().toString();
            modele = etModele.getText().toString();
            couleur = etCouleur.getText().toString();


            if (immatriculation.isEmpty()|| immatriculation == null) {
                etImmatriculation.requestFocus();
                etImmatriculation.setError(getText(R.string.errorImmatriculation));
                // duplication immatriculation à gérer ici
                verifOk = false;

            }
            if (marque.isEmpty()) {
                etMarque.requestFocus();
                etMarque.setError(getText(R.string.errorMarque));
                verifOk = false;

            }
            if (modele.isEmpty()) {
                etModele.requestFocus();
                etModele.setError(getText(R.string.errorModele));
                verifOk = false;

            }

            if (etCategorie.getText().toString().isEmpty() ) {
                etCategorie.requestFocus();
                etCategorie.setError(getText(R.string.errorCategorie));
                verifOk = false;
            }
            else{
                categorie = Vehicule.Categorie.valueOf(etCategorie.getText().toString());
            }



            if (tarifJournalier <=0) {
                etPrixParJour.requestFocus();
                etPrixParJour.setError(getText(R.string.errorPrix));
                verifOk = false;

            }
            else{

                tarifJournalier = Double.parseDouble(etPrixParJour.getText().toString());
            }


            return verifOk;
        }
    }
