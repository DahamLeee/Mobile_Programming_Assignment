package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private final int RESULT_UP = 33;
    private final int RESULT_DOWN = 44;
    String name;
    int age;
    String gender;
    String color;
    int like;
    int dislike;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView8 = findViewById(R.id.textView8);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            name = bundle.getString("name");
            age = bundle.getInt("age");
            gender = bundle.getString("gender");
            color = bundle.getString("color");
            like = bundle.getInt("like");
            dislike = bundle.getInt("dislike");

            textView5.setText("name : " + name);
            textView6.setText("age : " + age);
            textView7.setText("gender : " + gender);
            textView8.setText("color : " + color);


        }
        Toast.makeText(this, "hello" + name + " " + gender + " " + color + like + dislike, Toast.LENGTH_LONG).show();
        ImageButton up = findViewById(R.id.up);
        ImageButton down = findViewById(R.id.down);

        up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                returnIntent.putExtra("like", like + 1);
                setResult(RESULT_UP, returnIntent);
                finish();
            }
        });

        down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                returnIntent.putExtra("dislike", dislike + 1);
                setResult(RESULT_DOWN, returnIntent);
                finish();
            }
        });
    }
}
