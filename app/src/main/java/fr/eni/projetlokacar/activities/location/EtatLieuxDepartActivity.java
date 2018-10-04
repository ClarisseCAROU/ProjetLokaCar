package fr.eni.projetlokacar.activities.location;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.activities.AccueilActivity;
import fr.eni.projetlokacar.activities.BaseActivity;
import fr.eni.projetlokacar.adapters.ImageAdapter;
import fr.eni.projetlokacar.bo.Location;
import fr.eni.projetlokacar.dao.DbHelper;
import fr.eni.projetlokacar.dao.LocationDAO;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class EtatLieuxDepartActivity extends BaseActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    private String mCurrentPhotoPath;

    private CompositeDisposable subscriptions;
    private LocationDAO locationDAO;

    private Location location;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat_lieux_depart);

        subscriptions = new CompositeDisposable();
        locationDAO = DbHelper.getDataBase(getApplication()).getLocationDAO();

        location = getIntent().getParcelableExtra("location");
        Log.d(TAG, "onCreate: " + location);

        GridView gridview = findViewById(R.id.gv_images);
        imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);
    }

    public void prendreDesPhotos(View view) {

        dispatchTakePictureIntent();

    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e(TAG, "dispatchTakePictureIntent: " + ex.getMessage());
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "fr.eni.projetlokacar.fileprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }

    }

    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(getFilesDir(), "images");
        storageDir.mkdirs();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, options);
            imageAdapter.addBitmap(bitmap);
            imageAdapter.notifyDataSetChanged();
        }
    }

    public void validerLocation(View view) {

        subscriptions.add(
                Completable.fromAction(() -> locationDAO.insert(location))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.computation())
                        .unsubscribeOn(Schedulers.computation())
                        .subscribe(
                                () -> {
                                    Toast.makeText(this, "Location validée, envoi du message au client.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),AccueilActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                },
                                e -> Log.e(TAG, "validerLocation: " + e.getMessage(), e)
                        )
        );

    }
}
