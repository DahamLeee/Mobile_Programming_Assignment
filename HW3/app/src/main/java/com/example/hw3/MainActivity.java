package com.example.hw3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RadioButton R1, R2, R3, R4;
    private ConstraintLayout constL;
    private InputMethodManager imm;
    private EditText sales;
    private SeekBar seekBar;
    private TextView salesText, TipText, TotalText, seekText;
    private TextView seekRate, Tip, Total;
    private int TipRate;
    private int salesNum;
    private int TipNum;
    private int TotalNum;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        R1 = findViewById(R.id.radioButton1); // RadioButton
        R2 = findViewById(R.id.radioButton2);
        R3 = findViewById(R.id.radioButton3);
        R4 = findViewById(R.id.radioButton4);

        salesText = findViewById(R.id.textView2); // 가운데 왼쪽
        TipText = findViewById(R.id.textView3);
        TotalText = findViewById(R.id.textView4);

        constL = findViewById(R.id.constL); // Constraint Layout

        Tip = findViewById(R.id.Tip); // TextView 가운데 Tip 의 값이 나옴
        Total = findViewById(R.id.Total); // TextView 가운데 Total 의 값이 나옴
        sales = findViewById(R.id.editText); // EditText sales 파트
        seekBar = findViewById(R.id.seekBar);
        seekRate = findViewById(R.id.seekRate); // TextView
        seekText = findViewById(R.id.textView7);
        checkBox = findViewById(R.id.checkBox);

        constL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(sales.getWindowToken(), 0);
            }
        });

        sales.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                try{
                    String temp = s.toString();
                    salesNum = Integer.parseInt(temp); // sales;
                    TipNum = salesNum * TipRate / 100;
                    TotalNum = salesNum + TipNum;
                    Tip.setText(String.valueOf(TipNum));
                    Total.setText(String.valueOf(TotalNum));
                }
                catch(Exception e){
                    Total.setText("0");
                }
            }
        });

        RadioGroup rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // RadioGroup
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton1: { // NO TIP
                        TipRate = 0;
                        seekBar.setProgress(0);
                        seekBar.setEnabled(false);
                        break;
                    }
                     case R.id.radioButton2: {  // RAND TIP
                         Random rnd = new Random();
                         TipRate = rnd.nextInt(30);
                         seekBar.setProgress(TipRate);
                         seekBar.setEnabled(true);
                         break;
                    }
                    case R.id.radioButton3: { // TIP by %
                        seekBar.setEnabled(true);
                        break;
                    }
                    case R.id.radioButton4: { // MAX TIP
                        TipRate = 30;
                        seekBar.setProgress(30);
                        seekBar.setEnabled(false);
                        break;
                    }
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekRate.setText(String.valueOf(progress));
                TipRate = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                imm.hideSoftInputFromWindow(seekBar.getWindowToken(), 0);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                imm.hideSoftInputFromWindow(seekBar.getWindowToken(), 0);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    Log.d("MainActivity", "Checked");
                    constL.setBackgroundColor(Color.BLACK);
                    R1.setTextColor(Color.WHITE); // Radio Button
                    R2.setTextColor(Color.WHITE);
                    R3.setTextColor(Color.WHITE);
                    R4.setTextColor(Color.WHITE);

                    checkBox.setTextColor(Color.WHITE);

                    sales.setTextColor(Color.WHITE); // edit text

                    salesText.setTextColor(Color.WHITE); // text
                    TipText.setTextColor(Color.WHITE);
                    TotalText.setTextColor(Color.WHITE);

                    Tip.setTextColor(Color.WHITE);
                    Total.setTextColor(Color.WHITE);

                    seekText.setTextColor(Color.WHITE);
                    seekRate.setTextColor(Color.WHITE);

                }else{
                    Log.d("MainActivity", "Unchecked");
                    constL.setBackgroundColor(Color.WHITE);

                    R1.setTextColor(Color.BLACK); // Radio Button
                    R2.setTextColor(Color.BLACK);
                    R3.setTextColor(Color.BLACK);
                    R4.setTextColor(Color.BLACK);

                    checkBox.setTextColor(Color.BLACK);

                    sales.setTextColor(Color.BLACK);

                    salesText.setTextColor(Color.BLACK);
                    TipText.setTextColor(Color.BLACK);
                    TotalText.setTextColor(Color.BLACK);

                    Tip.setTextColor(Color.BLACK);
                    Total.setTextColor(Color.BLACK);

                    seekText.setTextColor(Color.BLACK);
                    seekRate.setTextColor(Color.BLACK);
                }
            }
        });

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
