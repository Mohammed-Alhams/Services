package com.example.services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {

    private static final String TAG = "ForegroundService";
    MediaPlayer mp;

    public ForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "my_id");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 3,
                new Intent(getApplicationContext(), MainActivity.class), 0);

        builder.setContentTitle("fore ground")
                .setContentText("Sound is running")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent);

//        NotificationCompat.Action action
//                = new NotificationCompat.Action();

        startForeground(1, builder.build());

        mp = MediaPlayer.create(ForegroundService.this, Settings.System.DEFAULT_RINGTONE_URI);
        Log.d(TAG, "onStartCommand: ");
        if (mp != null) {
            mp.start();
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        mp.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}