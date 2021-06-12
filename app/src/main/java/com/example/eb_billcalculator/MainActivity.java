package com.example.eb_billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button cal;
    private EditText txt1,txt2;
    RadioButton radio1,radio2;
    RadioGroup grp;
    float unitconsumed;
    float billamount=0.0f;
    int connectiontype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cal=(Button)findViewById(R.id.calculate);
        txt1=(EditText)findViewById(R.id.previous);
        txt2=(EditText)findViewById(R.id.current);
        radio1=(RadioButton)findViewById(R.id.rb1);
        radio2=(RadioButton)findViewById(R.id.rb2);
        grp=(RadioGroup)findViewById(R.id.grp);
        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    int Selected=grp.getCheckedRadioButtonId();
                    radio1=(RadioButton)findViewById(Selected);
                    connectiontype=1;
                }
            }
        });
        radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    connectiontype=2;
                    int Selected=grp.getCheckedRadioButtonId();
                    radio2=(RadioButton)findViewById(Selected);
                }
            }
        });
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt3 = txt1.getText().toString();
                float txt5 = Float.parseFloat(txt3);
                String txt4 = txt2.getText().toString();
                float txt6 = Float.parseFloat(txt4);
                if (txt5 > txt6) {
                    Toast.makeText(MainActivity.this, "Error in calculating ", Toast.LENGTH_LONG).show();
                } else {
                    unitconsumed = txt6 - txt5;

                    switch (connectiontype) {
                        case 1:
                            if (unitconsumed >= 0 && unitconsumed <= 100) {
                                billamount = unitconsumed * 1f;
                            } else if (unitconsumed > 100 && unitconsumed <= 200) {
                                billamount = unitconsumed * 2.5f;
                            } else if (unitconsumed > 200 && unitconsumed <= 500) {
                                billamount = unitconsumed * 4f;
                            } else {
                                billamount = unitconsumed * 6f;
                            }
                            break;
                        case 2:
                            if (unitconsumed >= 0 && unitconsumed <= 100) {
                                billamount = unitconsumed * 2f;
                            } else if (unitconsumed > 100 && unitconsumed <= 200) {
                                billamount = unitconsumed * 4.5f;
                            } else if (unitconsumed > 200 && unitconsumed <= 500) {
                                billamount = unitconsumed * 6f;
                            } else {
                                billamount = unitconsumed * 7f;
                            }
                            break;
                    }
                    Toast.makeText(MainActivity.this, "Unit Consumed:  " + unitconsumed + "\nBill Amount:  " + billamount, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}