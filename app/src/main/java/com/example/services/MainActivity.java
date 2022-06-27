package com.example.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop, btnSend, btnStartForeground;
    EditText etText, etTitle;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnSend = findViewById(R.id.btnSend);
        etText = findViewById(R.id.etText);
        etTitle = findViewById(R.id.etTitle);
        btnStartForeground = findViewById(R.id.btnStartForeground);

        btnStart.setOnClickListener((v) -> {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        });


        btnStop.setOnClickListener((v) -> {
            Intent intent = new Intent(this, ForegroundService.class);
            stopService(intent);
        });

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, "my_id");

        builder.setContentTitle(etTitle.getText().toString())
                .setContentText(etText.getText().toString())
                .setSmallIcon(R.drawable.ic_launcher_foreground);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getBaseContext());

        btnSend.setOnClickListener(v->{
            notificationManager.notify(1, builder.build());
        });

        btnStartForeground.setOnClickListener(v->{
            Intent i = new Intent(this, ForegroundService.class);
            ContextCompat.startForegroundService(this, i);
        });
        


    }
}