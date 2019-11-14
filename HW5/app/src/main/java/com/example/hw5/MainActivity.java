package com.example.hw5;

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

    public final String key01 = "psyPlay";
    public final String key02 = "psyLike";
    public final String key03 = "iuPlay";
    public final String key04 = "iuLike";

    int psyPlay = 4; //
    int psyLike = 3; //
    // 현재 재생 위치... => SecondActivity 에서 shared로 선언
    int iuPlay = 3;
    int iuLike = 2;

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
}
