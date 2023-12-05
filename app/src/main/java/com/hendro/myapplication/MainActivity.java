package com.hendro.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.hendro.myapplication.databinding.ActivityMainBinding;
import com.hendro.myapplication.fragment.FragmentActivity;
import com.hendro.myapplication.recyclerview.RecyclerViewActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // menu topappbar
        binding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.m_fragment) {
                Intent i = new Intent(MainActivity.this, FragmentActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
                return true;
            } else if (item.getItemId() == R.id.m_recyclerView) {
                Intent i = new Intent(MainActivity.this, RecyclerViewActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
                return true;
            }

            return false;
        });

        //memberikan fungsi pada tombol
        binding.btToast.setOnClickListener(v ->
                Toast.makeText(getApplicationContext(), R.string.how_are_you, Toast.LENGTH_SHORT).show()
        );

        binding.btDialog.setOnClickListener(v -> {
            //menampilkan dialog (Material3)
            new MaterialAlertDialogBuilder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.warn)
                .setMessage(R.string.how_are_you)
                .setCancelable(true)
                .setMessage(R.string.how_are_you)
                .setPositiveButton(R.string.ok, (dialog, which) -> Toast.makeText(getApplicationContext(),
                            R.string.how_are_you,
                            Toast.LENGTH_SHORT).show())
                .setNegativeButton(R.string.cancel, (dialog, which) -> Toast.makeText(getApplicationContext(),
                            R.string.close,
                            Toast.LENGTH_SHORT).show())
                .setNeutralButton(R.string.neutral, (dialogInterface, i) -> Toast.makeText(getApplicationContext(),
                            R.string.hello,
                            Toast.LENGTH_SHORT).show())
                .show();
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
            // apakah permission sudah diaktifkan pada ponsel?
            if(NotificationManagerCompat.from(this).areNotificationsEnabled())
            {
                //notifikasi
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i, PendingIntent.FLAG_IMMUTABLE);

                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle(getApplicationContext().getResources().getString(R.string.app_name));
                bigText.setSummaryText(getApplicationContext().getResources().getString(R.string.hello));

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setContentTitle(getApplicationContext().getResources().getString(R.string.warn));
                mBuilder.setContentText(getApplicationContext().getResources().getString(R.string.how_are_you));
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
            }
            else
            {
                new MaterialAlertDialogBuilder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.warn)
                        .setMessage(R.string.permission)
                        .setCancelable(true)
                        .setPositiveButton(R.string.ok, (dialog, which) -> {
                            // buka notification setting
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        })
                        .setNegativeButton(R.string.cancel, (dialog, which) -> {

                                }
                                )
                        .show();

            }
        });

        binding.btDetil.setOnClickListener(v -> {
            String nama, alamat, prodi, domisili;
            Boolean teknologi, kuliner;

            //ambil nilai
            nama = Objects.requireNonNull(binding.etNama.getText()).toString();
            alamat = Objects.requireNonNull(binding.etAlamat.getText()).toString();
            prodi = binding.spProdi.getSelectedItem().toString();
            teknologi = binding.cbTeknologi.isChecked();
            kuliner = binding.cbKuliner.isChecked();
            domisili = binding.rgDomisili.toString();

            /*
            Selipkan data yang ingin dikirim ke detail activity
            dengan putExtra
            */
            Intent i = new Intent(MainActivity.this, DetilActivity.class);
            i.putExtra("x_nama", nama);
            i.putExtra("x_alamat", alamat);
            i.putExtra("x_prodi", prodi);
            i.putExtra("x_teknologi", teknologi); //boolean
            i.putExtra("x_kuliner", kuliner); //boolean
            i.putExtra("x_domisili", domisili);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(i);
        });


    }

}