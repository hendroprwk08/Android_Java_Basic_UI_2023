package com.hendro.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.hendro.myapplication.databinding.ActivityDetilBinding;
import java.util.Objects;

public class DetilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetilBinding binding = ActivityDetilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // tombol back
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // terima dan tampilkan data
        Intent i = getIntent();
        binding.tvNama.setText(i.getStringExtra("x_nama"));
        binding.tvAlamat.setText(i.getStringExtra("x_alamat"));
        binding.tvProdi.setText(i.getStringExtra("x_prodi"));
        binding.cbTeknologi.setChecked(i.getBooleanExtra("x_teknologi", false));
        binding.cbKuliner.setChecked(i.getBooleanExtra("x_kuliner", false));

        String kelas = i.getStringExtra("x_domisili");
        assert kelas != null;
        if (kelas.equals("Luar Kota")) {
            binding.rbDK.setChecked(true);
        } else {
            binding.rbLK.setChecked(true);
        }
    }
}