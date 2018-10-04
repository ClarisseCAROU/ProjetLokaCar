package fr.eni.projetlokacar.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import fr.eni.projetlokacar.activities.AccueilActivity;
import fr.eni.projetlokacar.bo.Categorie;
import fr.eni.projetlokacar.bo.Client;
import fr.eni.projetlokacar.bo.Location;
import fr.eni.projetlokacar.bo.Vehicule;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public abstract class DbHelper {

    public static AppDatabase getDataBase(Context context){

        return Room
                .databaseBuilder(context, AppDatabase.class, "LokaCar")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        insertData(context);
                    }
                })
                .build();
    }

    private static void insertData(Context context) {

        Client[] clients = {
                new Client("Ladbrooke", "Berti", 225098417, "00 Hollow Ridge Plaza", "32154", "Kroya", "2433704333", "bladbrooke0@slate.com"),
                new Client("Dalley", "Gilli", 1449591337, "53066 Washington Place", "46406", "Gary", "2193286898", "gdalley1@smugmug.com"),
                new Client("Mordacai", "Debby", 236963684, "207 Northfield Circle", "60224", "Norrköping", "9471564650", "dmordacai2@ebay.co.uk"),
                new Client("Boate", "Lark", 429322185, "9314 Banding Street", "65478", "Pulosari", "2785782406", "lboate3@youku.com"),
                new Client("Hallett", "Violet", 16904252, "23 Merrick Junction", "89-340", "Białośliwie", "2133512175", "vhallett4@mozilla.org"),
                new Client("Bessant", "Christian", 615383442, "42994 Scott Junction", "8400", "San Carlos de Bariloche", "8776705471", "cbessant5@dagondesign.com"),
                new Client("Swinerd", "Addie", 421731226, "758 Loomis Court", "80893", "Prażmów", "1653773414", "aswinerd6@webeden.co.uk"),
                new Client("Russon", "Zea", 255654279, "555 Mandrake Park", "62636", "Silvares", "4934209320", "zrusson7@wikipedia.org"),
                new Client("Dobbins", "Griffin", 604075751, "0005 American Trail", "69871", "Egindiköl", "4115112141", "gdobbins8@altervista.org")
        };

        Categorie[] categories = {
                new Categorie("Citadine"),
                new Categorie("Routière"),
                new Categorie("Berline"),
                new Categorie("Utilitaire"),
                new Categorie("Monospace")
        };

        Vehicule[] vehicules = {
                new Vehicule("DP-391-GH", "Yellow", 126.6, "300SL", "Mercedes-Benz", 2),
                new Vehicule("FB-635-KE", "Blue", 79.24, "X6 M", "BMW", 2),
                new Vehicule("DG-596-AW", "Purple", 82.54, "Cayenne", "Porsche", 2),
                new Vehicule("YH-563-HK", "Turquoise", 79.41, "Ram 3500", "Dodge", 4),
                new Vehicule("UW-355-ID", "Mauv", 80.56, "Savana 1500", "GMC", 3),
                new Vehicule("CD-975-CF", "Blue", 65.54, "Sebring", "Chrysler", 2),
                new Vehicule("TS-981-UC", "Blue", 82.55, "Cayman", "Porsche", 3),
                new Vehicule("DN-218-OH", "Violet", 20.26, "CLK-Class", "Mercedes-Benz", 3),
                new Vehicule("YS-726-ON", "Red", 30.26, "S-Class", "Mercedes-Benz", 1),
                new Vehicule("AX-569-WO", "Indigo", 50.52, "Sunfire", "Pontiac", 1),
                new Vehicule("JJ-264-PF", "Aquamarine", 34.47, "Montero", "Mitsubishi", 4),
                new Vehicule("NE-177-WI", "Pink", 136.11, "LS", "Lexus", 2),
                new Vehicule("QC-852-WR", "Indigo", 41.31, "Bonneville", "Pontiac", 3),
                new Vehicule("AG-436-ZK", "Khaki", 68.3, "Odyssey", "Honda", 4),
                new Vehicule("EO-943-TR", "Turquoise", 75.95, "Grand Am", "Pontiac", 4),
                new Vehicule("ZU-420-UB", "Orange", 128.29, "Century", "Buick", 1),
                new Vehicule("SU-389-CN", "Crimson", 43.68, "Ram 2500 Club", "Dodge", 2),
                new Vehicule("JV-291-ZH", "Puce", 42.22, "Tahoe", "Chevrolet", 1),
                new Vehicule("AE-728-WI", "Red", 110.49, "E150", "Ford", 2),
                new Vehicule("NZ-972-TV", "Pink", 91.32, "Bonneville", "Pontiac", 1)
        };

        Location[] locations = {
                new Location(1, 1, new Date(), new Date(1539591293	)),
                new Location(2, 2, new Date(), new Date(1539591293	)),
//DG-596-AW
                //DP-391-GH  1539170962
                new Location(3, 3, new Date(), new Date(1539591293)),
                new Location(5, 3, new Date(), new Date(1539591293), new Date(1539591293), "commentaire avant état des lieux"),
                new Location(4, 4, new Date(), new Date(1539591293	)),

                new Location(5, 3, new Date(), new Date(1539591293), null, "commentaire avant état des lieux"),
        };

        Executors.newSingleThreadExecutor().execute(() -> {
            DbHelper.getDataBase(context).getClientDao().insertAll(clients);
            DbHelper.getDataBase(context).getCategorieDAO().insertAll(categories);
            DbHelper.getDataBase(context).getVehiculeDAO().insertAll(vehicules);
            DbHelper.getDataBase(context).getLocationDAO().insertAll(locations);
        });
       /* Executors.newSingleThreadExecutor().execute(() -> DbHelper.getDataBase(context).getCategorieDAO().insertAll(categories));
        Executors.newSingleThreadExecutor().execute(() -> DbHelper.getDataBase(context).getVehiculeDAO().insertAll(vehicules));
        Executors.newSingleThreadExecutor().execute(() -> DbHelper.getDataBase(context).getLocationDAO().insertAll(locations));*/


    }

}
