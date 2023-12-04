package com.hendro.myapplication.recyclerview;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hendro.myapplication.R;
import com.hendro.myapplication.databinding.ActivityRecyclerViewBinding;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    boolean grid = false;

    ArrayList<Presiden> list;

    // data yang dimasukkan
    public static String[][] data = new String[][]{
            {"Soekarno", "Presiden Pertama RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Presiden_Sukarno.jpg/418px-Presiden_Sukarno.jpg"},
            {"Soeharto", "Presiden Kedua RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/President_Suharto%2C_1993.jpg/468px-President_Suharto%2C_1993.jpg"},
            {"Bacharuddin Jusuf Habibie", "Presiden Ketiga RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Bacharuddin_Jusuf_Habibie_official_portrait.jpg/520px-Bacharuddin_Jusuf_Habibie_official_portrait.jpg"},
            {"Abdurrahman Wahid", "Presiden Keempat RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/President_Abdurrahman_Wahid_-_Indonesia.jpg/486px-President_Abdurrahman_Wahid_-_Indonesia.jpg"},
            {"Megawati Soekarnoputri", "Presiden Kelima RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/President_Megawati_Sukarnoputri_-_Indonesia.jpg/540px-President_Megawati_Sukarnoputri_-_Indonesia.jpg"},
            {"Susilo Bambang Yudhoyono", "Presiden Keenam RI", "https://upload.wikimedia.org/wikipedia/commons/5/58/Presiden_Susilo_Bambang_Yudhoyono.png"},
            {"Joko Widodo", "Presiden Ketujuh RI", "https://upload.wikimedia.org/wikipedia/commons/1/1c/Joko_Widodo_2014_official_portrait.jpg"}
    };

    ActivityRecyclerViewBinding binding;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        showListRecyclerView();

        binding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.m_model) {
                // ubah model
                if (grid) {
                    grid = false;
                    binding.topAppBar.getMenu().findItem(R.id.m_model).setIcon(getDrawable(R.drawable.baseline_grid_view_white_24));
                    showListRecyclerView();
                } else {
                    grid = true;
                    binding.topAppBar.getMenu().findItem(R.id.m_model).setIcon(getDrawable(R.drawable.baseline_view_list_white_24));
                    showGridRecyclerView();
                }

                return true;
            }

            return false;
        });

        binding.topAppBar.setNavigationOnClickListener(v -> finish());
    }

    private void showListRecyclerView() {
        Presiden presiden;

        list = new ArrayList<>();

        for (String[] datum : data) {
            presiden = new Presiden();
            presiden.setName(datum[0]);
            presiden.setRemarks(datum[1]);
            presiden.setPhoto(datum[2]);

            Log.i("TAG", "showListRecyclerView: " + presiden);

            list.add(presiden);
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }

    private void showGridRecyclerView() {
        Presiden presiden;

        list = new ArrayList<>();

        for (String[] datum : data) {
            presiden = new Presiden();
            presiden.setName(datum[0]);
            presiden.setRemarks(datum[1]);
            presiden.setPhoto(datum[2]);

            list.add(presiden);
        }

        //deteksi orientasi apakah landscape atau potrait
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        RecyclerViewGridAdapter adapter = new RecyclerViewGridAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }
}