package fr.eni.projetlokacar.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.idescout.sql.SqlScoutServer;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "LokaCarApp";

    private SqlScoutServer sqlScoutServer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqlScoutServer = SqlScoutServer.create(this, getPackageName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlScoutServer.destroy();
    }

}
