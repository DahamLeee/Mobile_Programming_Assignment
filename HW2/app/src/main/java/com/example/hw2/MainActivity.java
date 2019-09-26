package com.example.hw2;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText Inch;
    EditText cm;
    String InchVal; // string InchVal
    double InchNum; // double InchNum
    String cmVal; // string cmVal
    double cmNum; // double cmNum
    TextView Error;
    Button Inch2cm;
    Button cm2Inch;
    ConstraintLayout constL;
    InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inch = findViewById(R.id.editText);
        cm = findViewById(R.id.editText2);
        Error = findViewById(R.id.textView3);
        Inch2cm = findViewById(R.id.button);
        cm2Inch = findViewById(R.id.button2);
        constL = findViewById(R.id.constL);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        constL.setOnClickListener(myClickListener);

        Inch2cm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(Inch.getWindowToken(), 0);
                InchVal = Inch.getText().toString();
                if(InchVal.getBytes().length <= 0){
                    Error.setText(R.string.InchErr);
                }
                else{
                    try{
                        InchNum = Double.parseDouble(InchVal);
                        if(InchNum < 0){
                            Error.setText(R.string.InchErr);
                        }
                        else{
                            cmVal = Double.toString(Math.round(InchNum * 2.54 * 100) / 100.0);
                            Error.setText("");
                            cm.setText(cmVal);
                        }

                    }
                    catch(Exception e){
                        Error.setText(R.string.InchErr);
                    }
                }

            }
        });

        cm2Inch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(cm.getWindowToken(), 0);
                cmVal = cm.getText().toString();
                if(cmVal.getBytes().length <= 0){
                    Error.setText(R.string.cmErr);
                }
                else{
                    try{
                        cmNum = Double.parseDouble(cmVal);
                        if(cmNum < 0){
                            Error.setText(R.string.cmErr);
                        }
                        else{
                            InchVal = Double.toString(Math.round(cmNum / 2.54 * 100) / 100.0);
                            Error.setText("");
                            Inch.setText(InchVal);
                        }
                    }
                    catch(Exception e){
                        Error.setText(R.string.cmErr);
                    }
                }
            }
        });
    }

    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard();
        }
    };

    private void hideKeyboard(){
        imm.hideSoftInputFromWindow(Inch.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(cm.getWindowToken(), 0);
    }
}

