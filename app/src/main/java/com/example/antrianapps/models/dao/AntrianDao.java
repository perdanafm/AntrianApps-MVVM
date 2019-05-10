package com.example.antrianapps.models.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.antrianapps.models.Antrian;

import java.util.List;

@Dao
public interface AntrianDao {
    @Insert
    void insertNewAntrian(Antrian antrian);

    @Query("SELECT * FROM antrian")
    LiveData<List<Antrian>> getAntrian();
}
