package com.example.hw4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 2;

    int dog1Like = 3;
    int dog1Dislike = 2;
    int dog2Like = 2;
    int dog2Dislike = 4;

    TextView textView1, textView2, textView3, textView4;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView); // dog1 like
        textView2 = findViewById(R.id.textView2); // dog1 dislike
        textView3 = findViewById(R.id.textView3); // dog2 like
        textView4 = findViewById(R.id.textView4); // dog2 dislike

        textView1.setText("Likes: " + dog1Like);
        textView2.setText("Dislikes: " + dog1Dislike);
        textView3.setText("Likes: " + dog2Like);
        textView4.setText("Dislikes: " + dog2Dislike);

        ImageButton dog1 = findViewById(R.id.dog1);
        ImageButton dog2 = findViewById(R.id.dog2);

        dog1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", "Zong");
                intent.putExtra("age", 2);
                intent.putExtra("gender", "Male");
                intent.putExtra("color", "Brown");
                intent.putExtra("like", dog1Like);
                intent.putExtra("dislike", dog1Dislike);
                startActivityForResult(intent, REQUEST_CODE);
//                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        dog2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(data != null) {
                if (resultCode == 33) {
                    dog1Like = data.getIntExtra("like", 0);
                    textView1.setText("Likes: " + dog1Like);
                }
                else if(resultCode == 44){
                    dog1Dislike = data.getIntExtra("dislike", 0);
                    textView2.setText("Dislikes: " + dog1Dislike);
                }
            }

        }
    }
}
