package com.example.hw6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 2;

    TextView textView, textView2; // psy
    TextView textView3, textView4; // iu

    ImageButton psy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

        textView.setText("(PSY)-(챔피언)");
        textView2.setText("Play : 5 Like : 5");
        textView3.setText("(IU)-(Love Poem)");
        textView4.setText("Play : 5 Like : 5");

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
