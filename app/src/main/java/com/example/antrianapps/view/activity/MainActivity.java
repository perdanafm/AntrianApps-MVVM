package com.example.antrianapps.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.antrianapps.R;
import com.example.antrianapps.models.Antrian;
import com.example.antrianapps.models.User;
import com.example.antrianapps.utils.SaveSharedPreferences;
import com.example.antrianapps.view.AppsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "mainActivity";
    private AppsViewModel appsViewModel;
    private int status = 0;
    private TextView no_antrian, curren_antrian;
    private Button nextButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        no_antrian = findViewById(R.id.no_antrian);
        curren_antrian = findViewById(R.id.current_antrian);

        nextButton = findViewById(R.id.next);
        clearButton = findViewById(R.id.clear);

        appsViewModel = ViewModelProviders.of(this).get(AppsViewModel.class);
        insertData();
        loadChanges();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status++;
                SaveSharedPreferences.setCurrentAntrian(getApplicationContext(),status);
                loadChanges();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreferences.setCurrentAntrian(getApplicationContext(),0);
                status = 0;
                loadChanges();
                nextButton.setEnabled(true);
            }
        });
    }

    private void loadChanges(){
        appsViewModel.getListPendaftar().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.size() == 0) {
                    no_antrian.setText("0");
                } else {
                    int lastData = users.size() - 1;
                    no_antrian.setText(String.valueOf(users.get(lastData).getId_user()));
                }
            }
        });
        appsViewModel.getListAntrian().observe(this, new Observer<List<Antrian>>() {
            @Override
            public void onChanged(List<Antrian> antrians) {
                if (antrians.size() == 0) {
                    curren_antrian.setText("0");
                } else {
                    int statusShared = SaveSharedPreferences.getCurrentAntrian(getApplicationContext());
                    if (statusShared+1 == antrians.size()){
                        nextButton.setEnabled(false);
                    }
                    Log.d(TAG, "indexForCurrent: " + statusShared);
                    curren_antrian.setText(String.valueOf(antrians.get(statusShared).getNo_antrian()));
                }
            }
        });
    }

    private void insertData(){
        if (getIntent().hasExtra(AddAntrianActivity.EXTRA_NAMA)){
            String nama = getIntent().getStringExtra(AddAntrianActivity.EXTRA_NAMA);
            Log.d(TAG, "insertData: "+nama);
            String alamat = getIntent().getStringExtra(AddAntrianActivity.EXTRA_ALAMAT);
            String noHp = getIntent().getStringExtra(AddAntrianActivity.EXTRA_NOHP);

            User user = new User(nama,alamat,noHp);
            appsViewModel.insertPendaftar(user);
        }else return;
    }
}
