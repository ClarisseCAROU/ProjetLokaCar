package fr.eni.projetlokacar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.eni.projetlokacar.R;

public class LoginActivity extends AppCompatActivity {

    private final String LOGIN = "chef";
    private final String MOT_DE_PASSE = "pwd";

    private EditText etLogin;
    private EditText etMotDePasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Récupération id de la vue
        this.etLogin = this.findViewById(R.id.etLogin);
        this.etMotDePasse = this.findViewById(R.id.etMotDePasse);
    }

    public void verificationUtilisateur(View view) {
        Log.i("CLARISSE", "Login");

        if (etLogin.getText().toString().equals(LOGIN) && etMotDePasse.getText().toString().equals(MOT_DE_PASSE)) {
            Intent intent = new Intent(this, AccueilActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Login ou mot de passe incorrects.", Toast.LENGTH_SHORT).show();
        }
    }
}
