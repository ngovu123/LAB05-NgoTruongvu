package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnCong,btnTru,btnNhan,btnChia,btnDelete,btnCham,btnBang;
    EditText EditTextKQ,EditTextNumber;
    Float NumTamThoi;
    private final char cong = '+';
    private final char tru = '-';
    private final char nhan = '*';
    private final char chia = '/';
    private final char bang = '0';
    private double gtri1 = Double.NaN;
    private double gtri2 = Double.NaN;
    private char thucthi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        AllBtn();
        BtnPress();
    }

    public void AllBtn()
    {
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnCong = (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCham = (Button) findViewById(R.id.btnCham);
        btnBang = (Button) findViewById(R.id.btnBang);
        EditTextNumber = (EditText) findViewById(R.id.EditTextNumber);
        EditTextKQ= (EditText) findViewById(R.id.EditTextKQ);
    }

    public void BtnPress()
    {
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + "9");
            }
        });
        btnCham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextNumber.setText(EditTextNumber.getText().toString() + ".");
            }
        });
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhtoan();
                thucthi = cong;
                EditTextKQ.setText(String.valueOf(gtri1) + "+");
                EditTextNumber.setText(null);
            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhtoan();
                thucthi = chia;
                EditTextKQ.setText(String.valueOf(gtri1) + "/");
                EditTextNumber.setText(null);
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhtoan();
                thucthi = tru;
                EditTextKQ.setText(String.valueOf(gtri1) + "-");
                EditTextNumber.setText(null);
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhtoan();
                thucthi = nhan;
                EditTextKQ.setText(String.valueOf(gtri1) + "*");
                EditTextNumber.setText(null);
            }
        });
        btnBang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhtoan();
                thucthi = bang;
                EditTextNumber.setText(String.valueOf(gtri1));
                EditTextKQ.setText(null);
                //EditTextNumber.setText(EditTextNumber.getText().toString() + String.valueOf(gtri2) + "=" + String.valueOf(gtri1));
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditTextNumber.setText("");
                EditTextKQ.setText("");
                gtri1=Double.NaN;
            }
        });
    }
    public void tinhtoan()
    {
        if(!Double.isNaN(gtri1)){
            gtri2 =  Double.parseDouble(EditTextNumber.getText().toString());
            switch (thucthi){
                case cong:
                    gtri1 = gtri1 + gtri2;
                    break;
                case tru:
                    gtri1 = gtri1 - gtri2;
                    break;
                case nhan:
                    gtri1 = gtri1 * gtri2;
                    break;
                case chia:
                    gtri1 = gtri1 / gtri2;
                    break;
                case bang:
                    break;

            }
        }else{
            gtri1 = Double.parseDouble(EditTextNumber.getText().toString());
        }
    }
}