package com.example.antrianapps.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.antrianapps.models.Antrian;
import com.example.antrianapps.models.dao.AntrianDao;
import com.example.antrianapps.models.database.AppsDatabase;

import java.util.List;

public class AntrianRepository {
    private AntrianDao antrianDao;
    private LiveData<List<Antrian>> listAntrian;

    public AntrianRepository(Application application) {
        AppsDatabase database = AppsDatabase.getInstance(application);
        antrianDao = database.antrianDao();
        listAntrian = antrianDao.getAntrian();
    }

    public void insert(Antrian antrian) {
        new InsertUserAsyncTask(antrianDao).execute(antrian);
    }

    public LiveData<List<Antrian>> getListAntrian() {
        return listAntrian;
    }

    private static class InsertUserAsyncTask extends AsyncTask<Antrian, Void, Void> {
        private AntrianDao antrianDao;

        private InsertUserAsyncTask(AntrianDao antrianDao) {
            this.antrianDao = antrianDao;
        }

        @Override
        protected Void doInBackground(Antrian... antrians) {
            antrianDao.insertNewAntrian(antrians[0]);
            return null;
        }
    }
}
