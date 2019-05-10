package com.example.antrianapps.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "antrian")
public class Antrian {
    @PrimaryKey(autoGenerate = true)
    private int no_antrian;

    private String nama_pendaftar;

    public Antrian(String nama_pendaftar) {
        this.nama_pendaftar = nama_pendaftar;
    }

    public int getNo_antrian() {
        return no_antrian;
    }

    public void setNo_antrian(int no_antrian) {
        this.no_antrian = no_antrian;
    }

    public String getNama_pendaftar() {
        return nama_pendaftar;
    }

    public void setNama_pendaftar(String nama_pendaftar) {
        this.nama_pendaftar = nama_pendaftar;
    }
}
