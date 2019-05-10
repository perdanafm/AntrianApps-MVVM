package com.example.antrianapps.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antrianapps.R;

public class AddAntrianActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etNohp;
    private Button saveBtn;

    public static final String EXTRA_NAMA = "com.example.antrianapps.view.activity.EXTRA_NAMA";
    public static final String EXTRA_ALAMAT = "com.example.antrianapps.view.activity.EXTRA_ALAMAT";
    public static final String EXTRA_NOHP = "com.example.antrianapps.view.activity.EXTRA_NOHP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_antrian);
        etNama = findViewById(R.id.nama);
        etAlamat = findViewById(R.id.alamat);
        etNohp = findViewById(R.id.no_hp);

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAntrian();
            }
        });

    }

    private void addAntrian(){
        String nama = etNama.getText().toString();
        String alamat = etAlamat.getText().toString();
        String noHp = etNohp.getText().toString();

        if (nama.trim().isEmpty() || alamat.trim().isEmpty() || noHp.trim().isEmpty()){
            Toast.makeText(this,"Mohon isi semua kolom",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent(AddAntrianActivity.this, MainActivity.class);
        data.putExtra(EXTRA_NAMA,nama);
        data.putExtra(EXTRA_ALAMAT,alamat);
        data.putExtra(EXTRA_NOHP,noHp);
        startActivity(data);
        finish();
    }
}
