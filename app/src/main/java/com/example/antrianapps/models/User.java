package com.example.antrianapps.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id_user;

    private String nama;
    private String alamat;
    private String no_hp;

    public User(String nama, String alamat, String no_hp) {
        this.nama = nama;
        this.alamat = alamat;
        this.no_hp = no_hp;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }


}
