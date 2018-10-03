package fr.eni.projetlokacar.activities.location;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.adapters.ImageAdapter;
import fr.eni.projetlokacar.bo.Location;

public class EtatLieuxDepartActivity extends BaseActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat_lieux_depart);

        location = getIntent().getParcelableExtra("location");

        GridView gridview = findViewById(R.id.gv_images);
        gridview.setAdapter(new ImageAdapter(this));
    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    public void prendreDesPhotos(View view) {

        dispatchTakePictureIntent();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");


            //mImageView.setImageBitmap(imageBitmap);
        }
    }
}
