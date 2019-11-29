package com.example.hw5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 2;
    private final int up = 101;
    private final int play = 102;

    SharedPreferences prefs;
    SharedPreferences.Editor ed;

    int psyPlay; //
    int psyLike; //
    // 현재 재생 위치... => SecondActivity 에서 shared로 선언
    int iuPlay;
    int iuLike;

    TextView textView, textView2; // psy
    TextView textView3, textView4; // iu

    ImageButton psy;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

        prefs = getSharedPreferences("MUSIC",MODE_PRIVATE);
        saveData();

        textView.setText("(PSY)-(챔피언)");
        textView2.setText("Play : " + psyPlay + " Like : " + psyLike);
        textView3.setText("(IU)-(Love Poem)");
        textView4.setText("Play : " + iuPlay + " Like : " + iuLike);

        psy = findViewById(R.id.psy);
        psy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("artist", "PSY");
                intent.putExtra("title", "napal_baji");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }
    public void saveData(){
        ed = prefs.edit();
        if(prefs.contains("psyPlay")){
            psyPlay = prefs.getInt("psyPlay", 0);
        } else{
            ed.putInt("psyPlay", 4); // psyPlay
        }

        if(prefs.contains("psyLike")){
            psyLike = prefs.getInt("psyLike", 0);
        } else{
            ed.putInt("psyLike", 3); // psyLike
        }

        if(prefs.contains("iuPlay")){
            iuPlay = prefs.getInt("iuPlay", 0);
        }else{
            ed.putInt("iuPlay", 3); // iuPlay
        }

        if(prefs.contains("iuLike")){
            iuLike = prefs.getInt("iuLike", 0);
        }else{
            ed.putInt("iuLike", 2); // iuLike
        }
        ed.apply();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(data != null){
                if(resultCode == up){
                    if(prefs.contains("psyLike")){
                        psyLike = prefs.getInt("psyLike", 0);
                    }
                }
                if(resultCode == play){
                    if(prefs.contains("psyPlay")){
                        psyPlay = prefs.getInt("psyPlay", 0);
                    }
                }
                textView2.setText("Play : " + psyPlay + " Like : " + psyLike);
            }
        }
    }
}
