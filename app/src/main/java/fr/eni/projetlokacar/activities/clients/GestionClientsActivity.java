package fr.eni.projetlokacar.activities.clients;

import android.app.Activity;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.location.NouvelleLocationActivity;
import fr.eni.projetlokacar.adapters.ClientAdapter;
import fr.eni.projetlokacar.bll.ClientManager;
import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.activities.BaseActivity;

public class GestionClientsActivity extends BaseActivity implements ClientAdapter.ClickClientListener {

    private RecyclerView rvClients;
    private ClientAdapter clientAdapter;
    private ClientReceiver clientReceiver;

    private int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_clients);

        requestCode = getIntent().getIntExtra("requestCode", 0);

        rvClients = this.findViewById(R.id.rvListeClients);
        rvClients.setHasFixedSize(true);

        clientAdapter = new ClientAdapter(this);
        rvClients.setAdapter(clientAdapter);

        this.clientReceiver = new ClientReceiver();
        this.registerReceiver(this.clientReceiver, new IntentFilter("fr.eni.select.clients"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ClientManager clientManager = new ClientManager(this);
        clientManager.selectAll();
    }

    @Override
    public void onClickClient(Client client) {

        if(requestCode == NouvelleLocationActivity.CHOIX_CLIENT) {
            setResult(Activity.RESULT_OK, new Intent().putExtra("client", client));
            finish();
        }
    }

    public void creerClient(View view) {
        Intent intent = new Intent(this, NouveauClientActivity.class);
        startActivity(intent);
    }

    //ClientReceiver appelé quand la liste des clients est prête
    private class ClientReceiver extends BroadcastReceiver {

        private static final String TAG = "CLIENT RECEIVER_CLIENT";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive");
            List<Client> clients = intent.getParcelableArrayListExtra("clients");
            clientAdapter.addListeClients(clients);
            clientAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(clientReceiver);
    }
}
