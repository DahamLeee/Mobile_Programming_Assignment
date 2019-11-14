package com.example.hw5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SecondActivity extends AppCompatActivity {
    private MediaPlayer mp;
    private SeekBar sb;
    boolean first = false;
    TextView textView5, textView6;
    TextView currentTime;
    TextView endTime;
    ImageButton play_pause, stop;
    MainHandler handler;

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
            while(mp.isPlaying()){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                sb.setProgress(mp.getCurrentPosition());
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("key", mp.getCurrentPosition());
                message.setData(bundle);

                handler.sendMessage(message);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);

        Bundle bundle =getIntent().getExtras();
        if(bundle != null){
            String artist = bundle.getString("artist");
            String title = bundle.getString("title");
            textView5.setText("Artist: " + artist);
            textView6.setText("Title: " + title);
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

        play_pause = findViewById(R.id.play_pause);
        play_pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
