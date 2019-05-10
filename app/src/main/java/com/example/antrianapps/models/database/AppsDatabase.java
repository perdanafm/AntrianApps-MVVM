package com.example.antrianapps.models.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.antrianapps.models.Antrian;
import com.example.antrianapps.models.User;
import com.example.antrianapps.models.dao.AntrianDao;
import com.example.antrianapps.models.dao.UserDao;

@Database(entities = {User.class, Antrian.class}, version = 3, exportSchema = false)
public abstract class AppsDatabase extends RoomDatabase {

    private static AppsDatabase instance;

    public abstract UserDao userDao();
    public abstract AntrianDao antrianDao();

    public static synchronized AppsDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppsDatabase.class, "antrian_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDao userDao;
        private AntrianDao antrianDao;
        private PopulateDbAsyncTask(AppsDatabase db){
            userDao = db.userDao();
            antrianDao = db.antrianDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            User user = new User("Jokowi","Jakarta","0");
            User user2 = new User("Jokowi","Jakarta","0");
            User user3 = new User("Jokowi","Jakarta","0");

            userDao.insert(user);
            userDao.insert(user2);
            userDao.insert(user3);

            antrianDao.insertNewAntrian(new Antrian(user.getNama()));
            antrianDao.insertNewAntrian(new Antrian(user2.getNama()));
            antrianDao.insertNewAntrian(new Antrian(user3.getNama()));
            Log.d("Apss", "doInBackground: ");
            return null;
        }
    }
}
