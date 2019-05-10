package com.example.antrianapps.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.antrianapps.models.User;
import com.example.antrianapps.models.dao.UserDao;
import com.example.antrianapps.models.database.AppsDatabase;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> listPendaftar;

    public UserRepository(Application application){
        AppsDatabase database = AppsDatabase.getInstance(application);
        userDao = database.userDao();
        listPendaftar = userDao.getPendaftar();
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getListPendaftar(){
        return listPendaftar;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
