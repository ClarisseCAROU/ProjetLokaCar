package fr.eni.projetlokacar.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import fr.eni.projetlokacar.activities.AccueilActivity;
import fr.eni.projetlokacar.bo.Client;

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

        final Client[] clients = {
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

        DbHelper.getDataBase(context).getClientDao().insertAll(clients);

    }

}
