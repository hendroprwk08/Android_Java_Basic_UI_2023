package com.hendro.myapplication.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.hendro.myapplication.R;
import com.hendro.myapplication.databinding.ActivityFragmentBinding;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ActivityFragmentBinding binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //set sebagai fragment yang pertama muncul
        gantiFragment(R.id.frame_layout, new SatuFragment());

        // kontrol navigasi
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.m_bulat) {
                gantiFragment(R.id.frame_layout, new SatuFragment());
            } else if (item.getItemId() == R.id.m_awan) {
                gantiFragment(R.id.frame_layout, new DuaFragment());
            } else {
                gantiFragment(R.id.frame_layout, new TigaFragment());
            }

            return true;
        });
    }

    void gantiFragment(int containerId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .commit();

        getSupportFragmentManager().executePendingTransactions();
    }
}