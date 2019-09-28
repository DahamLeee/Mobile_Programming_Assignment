package com.example.hw3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg;
    private ConstraintLayout constL;
    private InputMethodManager imm;
    private EditText sales;
    private SeekBar seekBar;
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

        constL = findViewById(R.id.constL);
        Tip = findViewById(R.id.Tip);
        Total = findViewById(R.id.Total);
        sales = findViewById(R.id.editText);
        seekBar = findViewById(R.id.seekBar);
        seekRate = findViewById(R.id.seekRate);

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

        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
