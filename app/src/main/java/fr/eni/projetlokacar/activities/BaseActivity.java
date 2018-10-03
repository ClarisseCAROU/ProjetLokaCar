package fr.eni.projetlokacar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.idescout.sql.SqlScoutServer;

import fr.eni.projetlokacar.R;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "LokaCarApp";

    private SqlScoutServer sqlScoutServer;
    private Toolbar toolbar;
    private int currentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sqlScoutServer = SqlScoutServer.create(this, getPackageName());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlScoutServer.destroy();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra("requestCode", requestCode);
        super.startActivityForResult(intent, requestCode);
    }

}
