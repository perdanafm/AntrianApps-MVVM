package com.example.antrianapps.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.antrianapps.models.Antrian;
import com.example.antrianapps.models.User;
import com.example.antrianapps.repository.AntrianRepository;
import com.example.antrianapps.repository.UserRepository;

import java.util.List;

public class AppsViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private AntrianRepository antrianRepository;

    private LiveData<List<User>> listPendaftar;
    private LiveData<List<Antrian>> listAntrian;

    public AppsViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        antrianRepository = new AntrianRepository(application);
        listPendaftar = userRepository.getListPendaftar();
        listAntrian = antrianRepository.getListAntrian();
    }

    public void insertPendaftar(User user){
        userRepository.insert(user);
        antrianRepository.insert(new Antrian(user.getNama()));
    }

    public LiveData<List<User>> getListPendaftar(){
        return listPendaftar;
    }

    public LiveData<List<Antrian>> getListAntrian(){
        return listAntrian;
    }
}
