package com.example.hw6;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

public class MyService extends IntentService {
    SeekBar sb;
    public MyService() {
        super("MyService");
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {

        if(intent == null){
            return Service.START_STICKY;
        }
        else{
            onHandleIntent(intent);
        }
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        MediaPlayer mp;
        mp = MediaPlayer.create(getApplicationContext(), R.raw.napal_baji);
        if(mp.isPlaying()){

        }else{
            mp.start();
        }
    }
}
