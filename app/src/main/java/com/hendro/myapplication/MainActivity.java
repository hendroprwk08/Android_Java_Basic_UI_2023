package com.hendro.myapplication;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.snackbar.Snackbar;
import com.hendro.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // toolbar (bukan action bar) menu
        binding.toolbar.inflateMenu(R.menu.menu_item);
        binding.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.m_fragment) {
                Toast.makeText(getApplicationContext(), R.string.fragment, Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.m_recyclerView) {
                Toast.makeText(getApplicationContext(), R.string.recyclerview, Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        });

        //memberikan fungsi pada tombol
        binding.btToast.setOnClickListener(v ->
                Toast.makeText(getApplicationContext(), R.string.how_are_you, Toast.LENGTH_SHORT).show()
        );

        binding.btDialog.setOnClickListener(v -> {
            //menampilkan dialog
            new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.warn)
                .setMessage(R.string.how_are_you)
                .setCancelable(true)
                .setPositiveButton(R.string.ok, (dialog, which) -> Toast.makeText(getApplicationContext(),
                        R.string.how_are_you,
                        Toast.LENGTH_SHORT).show())
                .setNegativeButton(R.string.cancel, (dialog, which) -> Toast.makeText(getApplicationContext(),
                        R.string.close,
                        Toast.LENGTH_SHORT).show())
                .setNeutralButton(R.string.neutral, (dialogInterface, i) -> Toast.makeText(getApplicationContext(),
                        R.string.hello,
                        Toast.LENGTH_SHORT).show());
        });

        binding.btKeluar.setOnClickListener(v -> {
            finish(); //tutup aplikasi
        });

        binding.btSnack.setOnClickListener(view1 -> {
            View v = findViewById(R.id.main_layout_id);
            int duration = Snackbar.LENGTH_SHORT;
            Snackbar.make(v, R.string.hello, duration).show();
        });

        binding.btNotifikasi.setOnClickListener(v -> {
            //notifikasi
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getApplicationContext(), "notify_001");
            Intent ii = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.setBigContentTitle(getApplicationContext().getResources().getResourceName(R.string.app_name));
            bigText.setSummaryText(getApplicationContext().getResources().getResourceName(R.string.hello));

            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle(getApplicationContext().getResources().getResourceName(R.string.warn));
            mBuilder.setContentText(getApplicationContext().getResources().getResourceName(R.string.how_are_you));
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigText);
            mBuilder.setDefaults(Notification.DEFAULT_SOUND); //suara
            mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000}); //getar

            NotificationManager mNotificationManager =
                    (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("notify_001",
                        "channelku",
                        NotificationManager.IMPORTANCE_DEFAULT);
                mNotificationManager.createNotificationChannel(channel);
            }

            mNotificationManager.notify(0, mBuilder.build());
        });

        binding.btDetil.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DetilActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(i);
        });
    }

}