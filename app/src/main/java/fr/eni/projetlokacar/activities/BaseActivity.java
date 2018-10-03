package fr.eni.projetlokacar.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import fr.eni.projetlokacar.R;

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private int currentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(int layoutResID) {
        currentLayout = layoutResID;
        RelativeLayout content = (RelativeLayout) findViewById(R.id.content);
        View wizardView = getLayoutInflater().inflate(layoutResID, content, false);
        content.addView(wizardView);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent marqueIntent = new Intent(this, AccueilActivity.class);
                startActivity(marqueIntent);
                break;
            case R.id.exit:
                Intent modeleIntent = new Intent(this, LoginActivity.class);
                startActivity(modeleIntent);
                break;
            default:
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        switch (currentLayout) {
            case R.layout.activity_accueil:
                menu.findItem(R.id.accueil).setVisible(true);
                break;
            case R.layout.activity_statistiques:
                menu.findItem(R.id.statistiques).setVisible(true);
                break;
        }
        return true;
    }
*/


