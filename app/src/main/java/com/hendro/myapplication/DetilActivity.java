package com.hendro.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hendro.myapplication.databinding.ActivityDetilBinding;
import com.hendro.myapplication.databinding.ActivityMainBinding;

public class DetilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetilBinding binding = ActivityDetilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // tombol back
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}