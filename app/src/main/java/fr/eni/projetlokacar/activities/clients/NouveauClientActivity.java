package fr.eni.projetlokacar.activities.clients;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.bll.ClientManager;
import fr.eni.projetlokacar.bo.Client;


public class NouveauClientActivity extends BaseActivity {

    private EditText etPrenomClient;
    private EditText etNomClient;
    private EditText etAdresseClient;
    private EditText etVilleClient;
    private EditText etCpClient;
    private EditText etTelephoneClient;
    private EditText etEmailClient;
    private EditText etNumPermisClient;

    private String prenom;
    private String nom;
    private String adresse;
    private String ville;
    private String codePostal;
    private String telephone;
    private String email;
    private int numPermis;

    private Client nouveauClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_client);

        etPrenomClient = this.findViewById(R.id.etPrenomClient);
        etNomClient = this.findViewById(R.id.etNomClient);
        etAdresseClient = this.findViewById(R.id.etAdresseClient);
        etVilleClient = this.findViewById(R.id.etVilleClient);
        etCpClient = this.findViewById(R.id.etCpClient);
        etTelephoneClient = this.findViewById(R.id.etTelephoneClient);
        etEmailClient = this.findViewById(R.id.etEmailClient);
        etNumPermisClient = this.findViewById(R.id.etNumPermisClient);

    }

/*    public void ajouterClient(View view) {
        String prenom = etPrenomClient.getText().toString();
        String nom = etNomClient.getText().toString();
        String adresse = etAdresseClient.getText().toString();
        String ville = etVilleClient.getText().toString();
        String codePostal = etCpClient.getText().toString();
        String telephone = etTelephoneClient.getText().toString();
        String email = etEmailClient.getText().toString();
        int numPermis = Integer.valueOf(etNumPermisClient.getText().toString());

        nouveauClient = new Client(nom, prenom, numPermis, adresse, codePostal, ville, telephone, email);
        Log.i("Ajout client", nouveauClient.toString());

        ClientManager clientManager = new ClientManager(this);
        clientManager.insert(nouveauClient);
    }*/

    public void ajouterClient(View view) {

        if (verifierClient()) {
            nouveauClient = new Client(nom, prenom, numPermis, adresse, codePostal, ville, telephone, email);
            Toast.makeText(this, "Le client " + nouveauClient.getPrenom() + " " + nouveauClient.getNom() + "a bien été créé.", Toast.LENGTH_SHORT).show();
            Log.i("Ajout client", nouveauClient.toString());

            ClientManager clientManager = new ClientManager(this);
            clientManager.insert(nouveauClient);
        }
    }

    private boolean verifierClient() {

        boolean verifOk = true;

        prenom = etPrenomClient.getText().toString().trim();
        nom = etNomClient.getText().toString().trim();
        adresse = etAdresseClient.getText().toString().trim();
        ville = etVilleClient.getText().toString().trim();
        codePostal = etCpClient.getText().toString().trim();
        telephone = etTelephoneClient.getText().toString().trim();
        email = etEmailClient.getText().toString().trim();

        if (prenom.isEmpty() || prenom == null) {
            etPrenomClient.requestFocus();
            etPrenomClient.setError(getText(R.string.errorPrenomClient));
            verifOk = false;

        }
        if (nom.isEmpty() || nom == null) {
            etNomClient.requestFocus();
            etNomClient.setError(getText(R.string.errorNomClient));
            verifOk = false;

        }
        if (adresse.isEmpty() || adresse == null) {
            etAdresseClient.requestFocus();
            etAdresseClient.setError(getText(R.string.errorAdresseClient));
            verifOk = false;

        }
        if (ville.isEmpty() || ville == null) {
            etVilleClient.requestFocus();
            etVilleClient.setError(getText(R.string.errorVilleClient));
            verifOk = false;

        }
        if (codePostal.isEmpty() || codePostal == null) {
            etCpClient.requestFocus();
            etCpClient.setError(getText(R.string.errorCpClient));
            verifOk = false;

        }
        if (telephone.isEmpty() || telephone == null) {
            etTelephoneClient.requestFocus();
            etTelephoneClient.setError(getText(R.string.errorTelephoneClient));
            verifOk = false;

        }
        if (email.isEmpty() || email == null) {
            etEmailClient.requestFocus();
            etEmailClient.setError(getText(R.string.errorEmailClient));
            verifOk = false;

        }
        if (etNumPermisClient.getText().toString().trim().isEmpty() || etNumPermisClient.getText().toString().trim() == null) {
            etNumPermisClient.requestFocus();
            etNumPermisClient.setError(getText(R.string.errorNumPermisClient));
            verifOk = false;

        } else {
            numPermis = Integer.valueOf(etNumPermisClient.getText().toString().trim());
        }

        return verifOk;
    }
}
