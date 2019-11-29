package com.example.hw5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SecondActivity extends AppCompatActivity {
    private static final int up = 101;
    private static final int play = 102;
    boolean yesOrNo = false;
    private MediaPlayer mp;
    private SeekBar sb;
    int psyLike;
    int psyPlay;
    int time;
    TextView textView5, textView6, textView9;
    TextView currentTime;
    TextView endTime;
    ImageButton play_pause, stop;
    MainHandler handler;
    ImageButton imageButton3;
    private SharedPreferences prefs;
    SharedPreferences.Editor ed;

    class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("key");
            currentTime.setText(df.format(new Date(value)));
            endTime.setText(df.format(new Date(mp.getDuration() - value)));
        }
    }

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat df = new SimpleDateFormat("mm:ss");

    class MyThread extends Thread{
        @Override
        public void run() {
            if(mp != null){
                try{
                    while(mp.isPlaying()){
                        try{
                            Thread.sleep(1000);
                            sb.setProgress(mp.getCurrentPosition());
                            Message message = handler.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putInt("key", mp.getCurrentPosition());
                            message.setData(bundle);

                            handler.sendMessage(message);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    new MyThread().interrupt();
                }

            }

        }

        @Override
        public void interrupt() {
            super.interrupt();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView9 = findViewById(R.id.textView9);

        prefs = getSharedPreferences("MUSIC",MODE_PRIVATE);

        Bundle bundle =getIntent().getExtras();
        if(bundle != null){
            String artist = bundle.getString("artist");
            String title = bundle.getString("title");
            textView5.setText("Artist: " + artist);
            textView6.setText("Title: " + title);
        }

        if(prefs.contains("psyLike")){
            psyLike = prefs.getInt("psyLike", 0);
            textView9.setText("Likes : " + psyLike);
        }
        if(prefs.contains("psyPlay")){
            psyPlay = prefs.getInt("psyPlay", 0);
        }

        currentTime = findViewById(R.id.currentTime); // 현재 위치
        endTime = findViewById(R.id.endTime);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.napal_baji);

        sb = findViewById(R.id.seekBar);
        sb.setMax(mp.getDuration());

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { // i는 progress
                if(b && (mp != null)){
                    mp.seekTo(i);
                    currentTime.setText(df.format(new Date(i)));
                    endTime.setText(df.format(new Date(mp.getDuration() - i)));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SecondActivity.this.runOnUiThread(new Runnable(){
            @Override
            public void run() {
                if(mp != null && mp.isPlaying()){
                    sb.setProgress(mp.getCurrentPosition());
                }
            }
        });

        if(prefs.contains("time")){
            time = prefs.getInt("time", 0);
            mp.seekTo(time);
            sb.setProgress(time);
            currentTime.setText(df.format(new Date(time)));
            endTime.setText(df.format(new Date(mp.getDuration() - time)));
        }else{
            currentTime.setText(df.format(new Date(0)));
            endTime.setText(df.format(new Date(mp.getDuration() - time)));
        }

        play_pause = findViewById(R.id.play_pause);
        play_pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!yesOrNo){
                    yesOrNo = true;
                    ed = prefs.edit();
                    ed.putInt("psyPlay", psyPlay + 1);
                    ed.apply();
                }
                if(mp == null){
                    return;
                }
                if(mp.isPlaying()){
                    mp.pause();
                    play_pause.setImageResource(android.R.drawable.ic_media_play);
                    // 여기서 image 바꿔주면 됨
                }else{
                    mp.start();
                    new MyThread().start();
                    handler = new MainHandler();
                    play_pause.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });

        stop = findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                mp.seekTo(0);
                play_pause.setImageResource(android.R.drawable.ic_media_play);
            }
        });

        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Up();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        new Thread().interrupt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp.isPlaying()){
            mp.pause();
        }
        ed = prefs.edit();
        ed.putInt("time", mp.getCurrentPosition());
        ed.apply();

        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        setResult(play, intent);
        finish();
    }

    public void Up(){
        ed = prefs.edit();
        ed.putInt("psyLike", psyLike + 1);
        ed.apply();
        Intent intent = getIntent();
        setResult(up, intent);
        finish();
    }
}
