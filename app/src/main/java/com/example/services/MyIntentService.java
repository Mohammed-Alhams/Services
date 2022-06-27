package com.example.services;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";
    MediaPlayer mp;

    public MyIntentService() {
        super("MyIntentService");

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: " + Thread.currentThread().getName());
        mp = MediaPlayer.
                create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: " + Thread.currentThread().getName());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp!= null){
            mp.stop();
        }
        Log.d(TAG, "onDestroy: " + Thread.currentThread().getName());
    }
}